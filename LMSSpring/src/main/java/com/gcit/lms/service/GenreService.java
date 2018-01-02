/*
 *
 *3:03:48 AM
 *Jan 1, 2018
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.ConnectDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;

/**
 * @author ThankGod4Life
 * @date Jan 1, 2018
 *
 */
public class GenreService extends BaseService{

	@Autowired
	GenreDAO gDao;

	@Autowired
	BookDAO bDao;
	
	@Autowired
	ConnectDAO cDao;
	
	public List<Genre> readAllGenres()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Genre> genre = gDao.readAllGenres();
		for (Genre genre2 : genre) {
			genre2.setBook(readBooksByGenreId(genre2.get_Genre_Id()));
		}
		return genre;
	}

	public List<Book> readBooksByGenreId(Integer genreId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_genres WHERE genre_id = ?)";
		return bDao.readAllBooksByOtherID(sql, genreId);
	}

	public List<Genre> readGenresByName(String genreName)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// no transaction taking place, just reading
		List<Genre> genre = gDao.readGenresByName(genreName);
		for (Genre genre2 : genre) {
			genre2.setBook(readBooksByGenreId(genre2.get_Genre_Id()));
		}
		return genre;
	}

	public List<Genre> readGenresLimitSearch(Integer pageNo, String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (searchString != null) {
			List<Genre> genre = gDao.readGenresByName(searchString);
			for (Genre genre2 : genre) {
				genre2.setBook(readBooksByGenreId(genre2.get_Genre_Id()));
			}
			return genre;
		}
		List<Genre> genre = gDao.readAllGenresWithLimit(pageNo);
		for (Genre genre2 : genre) {
			genre2.setBook(readBooksByGenreId(genre2.get_Genre_Id()));
		}
		return genre;
	}

	public Integer getGenresCount()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return gDao.getGenresCount();
	}

	public Genre readGenreByPK(Integer pk)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Genre genre = gDao.readGenreByPK(pk);
		genre.setBook(readBooksByGenreId(genre.get_Genre_Id()));
		return genre;
	}

	@Transactional
	public Integer addGenreWithID(String name, List<Integer> bookIds)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Genre genre = new Genre();
		genre.set_Genre_Name(name);
		Integer id = gDao.addGenreWithID(genre);
		genre.set_Genre_Id(id);
		List<Book> bList = readObjectsByPk(bDao, "readBookByPK", bookIds);
		List<Genre> aList = new ArrayList<>();
		aList.add(genre);
		for (Book book : bList) {
			book.setGenres(aList);
			bDao.addBookGenres(book); // update table with new genre Id and each book Id
		}
		return id;
	}

	@Transactional
	public void updateGenre(Integer id, String name, List<Integer> bookIds)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Genre genre = gDao.readGenreByPK(id);
		List<Book> bList = readBooksByGenreId(id); // get currently related books
		List<Genre> aList = new ArrayList<>();
		aList.add(genre);
		cDao.deleteBookGenres(bList, aList); // update book_genre table for related books
		genre.set_Genre_Name(name);
		gDao.updateGenre(genre);
		List<Book> bList2 = readObjectsByPk(bDao, "readBookByPK", bookIds);
		for (Book book : bList2) {
			book.setGenres(aList);
			bDao.addBookGenres(book); // update table with new book Ids
		}
	}

	@Transactional
	public void deleteGenre(Integer id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Genre genre = gDao.readGenreByPK(id);
		gDao.deleteGenre(genre);
	}

}
