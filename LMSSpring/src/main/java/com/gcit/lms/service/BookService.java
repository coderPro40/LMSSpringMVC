/*
 *
 *3:05:34 PM
 *Dec 31, 2017
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;

/**
 * @author ThankGod4Life
 * @date Dec 31, 2017
 *
 */
public class BookService extends BaseService {
	@Autowired
	BookDAO bDao;

	@Autowired
	AuthorDAO aDao;

	@Autowired
	GenreDAO gDao;

	@Autowired
	PublisherDAO pDao;

	public List<Book> readAllBooks()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Book> book = bDao.readAllBooks();
		for (Book book2 : book) {
			setObjectsByBookId(book2); // update attributes of book objects
		}
		return book;
	}

	public void setObjectsByBookId(Book book)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		book.setAuthors(aDao.readAllAuthorsByBookID(book.getBookId()));
		book.setGenres(gDao.readAllGenresByBookID(book.getBookId()));
	}

	public List<Book> readBooksByTitle(String title)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Book> book = bDao.readBooksByTitle(title);
		for (Book book2 : book) {
			setObjectsByBookId(book2); // update attributes of book objects
		}
		return book;
	}

	public Book readBookByPK(Integer pk)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Book book = bDao.readBookByPK(pk);
		setObjectsByBookId(book);
		return book;
	}

	@Transactional
	public void addBookWithID(String title, Integer publisherId, List<Integer> authorIds, List<Integer> genreIds)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// must have title, publisherId, list of authorIds, and list of genreIds
		Book book = new Book();
		book.setTitle(title);
		book.setPublisher(pDao.readPublisherByPK(publisherId));
		List<Author> aList = readObjectsByPk(aDao, "readAuthorsByPK", authorIds);
		List<Genre> gList = readObjectsByPk(gDao, "readGenreByPK", genreIds);
		book.setAuthors(aList);
		book.setGenres(gList);
		Integer id = bDao.addBookWithID(book);
		book.setBookId(id);
		bDao.addBookAuthors(book);
		bDao.addBookGenres(book);
	}

	@Transactional
	public void updateBook(Integer bookId, String title, Integer publisherId, List<Integer> authorIds,
			List<Integer> genreIds)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Book book = bDao.readBookByPK(bookId);
		setObjectsByBookId(book); // complete setup process
		bDao.deleteBookAuthors(book); // remove from database tables
		bDao.deleteBookGenres(book);
		book.setTitle(title);
		bDao.updateBookTitle(book); // update the database table for title
		book.setPublisher(pDao.readPublisherByPK(publisherId));
		bDao.updateBookPublisher(book); // update the database table for pub id
		List<Author> aList = readObjectsByPk(aDao, "readAuthorsByPK", authorIds);
		List<Genre> gList = readObjectsByPk(gDao, "readGenreByPK", genreIds);
		book.setAuthors(aList); // now set the new list of authors and genres
		book.setGenres(gList);
		bDao.addBookAuthors(book); // update the tables of the database
		bDao.addBookGenres(book);
	}

	@Transactional
	public void deleteBook(Integer id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Book book = bDao.readBookByPK(id);
		bDao.deleteBook(book);
	}

	public List<Book> readBooksLimitSearch(Integer pageNo, String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (searchString != null) {
			List<Book> book = bDao.readBooksByTitle(searchString);
			for (Book book2 : book) {
				setObjectsByBookId(book2); // update attributes of book objects
			}
			return book;
		}
		List<Book> book = bDao.readAllBooksWithLimit(pageNo);
		for (Book book2 : book) {
			setObjectsByBookId(book2); // update attributes of book objects
		}
		return book;
	}

	public Integer getBooksCount()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return bDao.getBooksCount();
	}

}
