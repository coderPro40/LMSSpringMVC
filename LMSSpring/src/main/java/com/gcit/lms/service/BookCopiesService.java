/*
 *
 *10:32:55 AM
 *Jan 1, 2018
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Branch;

/**
 * @author ThankGod4Life
 * @date Jan 1, 2018
 *
 */
public class BookCopiesService {

	@Autowired
	BookDAO bDao;

	@Autowired
	BookCopiesDAO bcDao;

	@Autowired
	BranchDAO brDao;

	public List<BookCopies> readBookCopiesLimitSearch(Integer pageNo, String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<BookCopies> bookCopies = bcDao.readAllBookCopiesWithLimit(pageNo);
		for (BookCopies bookCopies2 : bookCopies) {
			setObjectsByBookCopiesId(bookCopies2); // update attributes of book objects
		}
		return bookCopies;
	}

	public void setObjectsByBookCopiesId(BookCopies bookCopies)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		bookCopies.setBook(bDao.readBookByPK(bookCopies.getBook().getBookId())); // for singular book copy there's
																					// singular branchId and bookId
		bookCopies.setBranch(brDao.readBranchByPK(bookCopies.getBranch().getBranchId()));
	}

	public Integer getBookCopiesCount()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return bcDao.getBookCopiesCount();
	}

	public List<BookCopies> readBookCopiesLimitSearchOverZero(Integer pageNo, String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<BookCopies> bookCopies = bcDao.readAllBookCopiesWithLimitOverZero(pageNo);
		for (BookCopies bookCopies2 : bookCopies) {
			setObjectsByBookCopiesId(bookCopies2); // update attributes of bookCopies objects
		}
		return bookCopies;
	}

	public Integer getBookCopiesCountOverZero()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return bcDao.getBookCopiesCountOverZero();
	}

	@Transactional
	public void addBookCopies(Integer bookId, Integer branchId, Integer noOfCopies)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		BookCopies bookCopies = new BookCopies();
		Book book = new Book();
		Branch branch = new Branch();
		book.setBookId(bookId);
		branch.setBranchId(branchId);
		bookCopies.setBook(book);
		bookCopies.setBranch(branch);
		bookCopies.setNoOfCopies(noOfCopies);
		bcDao.addBookCopies(bookCopies);
	}

	@Transactional
	public void updateBookCopiesByIds(Integer bookId, Integer branchId, Integer noOfCopies)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		BookCopies bookCopies = bcDao.readBookCopiesByIds(bookId, branchId);
		bookCopies.setNoOfCopies(bookCopies.getNoOfCopies() + noOfCopies);
		bcDao.updateBookCopies(bookCopies);
	}

	public BookCopies readBookCopiesByIds(Integer bookId, Integer branchId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		BookCopies bookCopies = bcDao.readBookCopiesByIds(bookId, branchId);
		setObjectsByBookCopiesId(bookCopies); // update attributes of bookCopies objects
		return bookCopies;
	}

	public List<BookCopies> readAllBookCopies()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<BookCopies> bookCopies = bcDao.readAllBookCopies();
		for (BookCopies bookCopies2 : bookCopies) {
			setObjectsByBookCopiesId(bookCopies2); // update attributes of book objects
		}
		return bookCopies;
	}
}
