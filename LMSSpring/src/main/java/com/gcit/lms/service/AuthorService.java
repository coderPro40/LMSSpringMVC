/*
 *
 *10:08:02 PM
 *Dec 21, 2017
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.ConnectDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

/**
 * @author ThankGod4Life
 * @date Dec 21, 2017
 *
 */
public class AuthorService extends BaseService {
	@Autowired
	AuthorDAO aDao;

	@Autowired
	BookDAO bDao;
	
	@Autowired
	ConnectDAO cDao;
	
	public List<Author> readAllAuthors()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Author> author = aDao.readAllAuthors();
		for (Author author2 : author) {
			author2.setBook(readBooksByAuthorId(author2.getAuthorId()));
		}
		return author;
	}

	public List<Book> readBooksByAuthorId(Integer authorId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_authors WHERE authorId = ?)";
		return bDao.readAllBooksByOtherID(sql, authorId);
	}

	public List<Author> readAuthorsByName(String authorName)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// no transaction taking place, just reading
		List<Author> author = aDao.readAuthorsByName(authorName);
		for (Author author2 : author) {
			author2.setBook(readBooksByAuthorId(author2.getAuthorId()));
		}
		return author;
	}

	public List<Author> readAuthorsLimitSearch(Integer pageNo, String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (searchString != null) {
			List<Author> author = aDao.readAuthorsByName(searchString);
			for (Author author2 : author) {
				author2.setBook(readBooksByAuthorId(author2.getAuthorId()));
			}
			return author;
		}
		List<Author> author = aDao.readAllAuthorsWithLimit(pageNo);
		for (Author author2 : author) {
			author2.setBook(readBooksByAuthorId(author2.getAuthorId()));
		}
		return author;
	}

	public Integer getAuthorsCount()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return aDao.getAuthorsCount();
	}

	public Author readAuthorByPK(Integer pk)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Author author = aDao.readAuthorsByPK(pk);
		author.setBook(readBooksByAuthorId(author.getAuthorId()));
		return author;
	}

	@Transactional
	public Integer addAuthorWithID(String name, List<Integer> bookIds)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Author author = new Author();
		author.setAuthorName(name);
		Integer id = aDao.addAuthorWithID(author);
		author.setAuthorId(id);
		List<Book> bList = readObjectsByPk(bDao, "readBookByPK", bookIds);
		List<Author> aList = new ArrayList<>();
		aList.add(author);
		for (Book book : bList) {
			book.setAuthors(aList);
			bDao.addBookAuthors(book); // update table with new author Id and each book Id
		}
		return id;
	}

	@Transactional
	public void updateAuthor(Integer id, String name, List<Integer> bookIds)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Author author = aDao.readAuthorsByPK(id);
		List<Book> bList = readBooksByAuthorId(id); // get currently related books
		List<Author> aList = new ArrayList<>();
		aList.add(author);
		cDao.deleteBookAuthors(bList, aList); // update book_author table for related books
		author.setAuthorName(name);
		aDao.updateAuthor(author);
		List<Book> bList2 = readObjectsByPk(bDao, "readBookByPK", bookIds);
		for (Book book : bList2) {
			book.setAuthors(aList);
			bDao.addBookAuthors(book); // update table with new book Ids
		}
	}

	@Transactional
	public void deleteAuthor(Integer id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Author author = aDao.readAuthorsByPK(id);
		aDao.deleteAuthor(author);
	}

}
