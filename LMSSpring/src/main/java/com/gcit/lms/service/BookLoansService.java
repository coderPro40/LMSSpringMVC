/*
 *
 *11:02:22 AM
 *Jan 1, 2018
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;

/**
 * @author ThankGod4Life
 * @date Jan 1, 2018
 *
 */
public class BookLoansService {

	@Autowired
	BookDAO bDao;

	@Autowired
	BookLoansDAO blDao;

	@Autowired
	BranchDAO brDao;

	@Autowired
	BorrowerDAO borDao;

	public List<BookLoans> readBookLoansLimitSearch(Integer pageNo, String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<BookLoans> bookLoans = blDao.readAllBookLoansWithLimit(pageNo);
		for (BookLoans bookLoans2 : bookLoans) {
			setObjectsByBookLoansId(bookLoans2); // update attributes of book objects
		}
		return bookLoans;
	}

	public void setObjectsByBookLoansId(BookLoans bookLoans)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		bookLoans.setBook(bDao.readBookByPK(bookLoans.getBook().getBookId())); // for singular book loan there's
																				// singular branchId and bookId
		bookLoans.setBranch(brDao.readBranchByPK(bookLoans.getBranch().getBranchId()));
		bookLoans.setBorrower(borDao.readBorrowerByPK(bookLoans.getBorrower().getCardNo()));
	}

	public Integer getBookLoansCount()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return blDao.getBookLoansCount();
	}

	public List<BookLoans> readAllBookLoans()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<BookLoans> bookLoans = blDao.readAllBookLoans();
		for (BookLoans bookLoans2 : bookLoans) {
			setObjectsByBookLoansId(bookLoans2); // update attributes of book objects
		}
		return bookLoans;
	}

	@Transactional
	public void updateBookLoansByDueDate(Integer bookId, Integer branchId, Integer cardNo, String dueDate)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		BookLoans bookLoans = blDao.readBookLoansByIds(bookId, branchId, cardNo);
		bookLoans.setDueDate(dueDate);
		blDao.updateBookLoansByDueDate(bookLoans);
	}

	@Transactional
	public void addBookLoans(Book book, Branch branch, Borrower borrower, String dateOut, String dueDate)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		BookLoans bookLoans = new BookLoans();
		bookLoans.setBook(book);
		bookLoans.setBranch(branch);
		bookLoans.setBorrower(borrower);
		bookLoans.setDateOut(dateOut);
		bookLoans.setDueDate(dueDate);
		blDao.addBookLoans(bookLoans);
	}

	@Transactional
	public void updateBookLoansByDateIn(Integer bookId, Integer branchId, Integer cardNo, String dateIn)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		BookLoans bookLoans = blDao.readBookLoansByIds(bookId, branchId, cardNo);
		bookLoans.setDateIn(dateIn);
		blDao.updateBookLoansByDateIn(bookLoans);
	}

	public BookLoans readBookLoansByIds(Integer bookId, Integer branchId, Integer cardNo)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		BookLoans bookLoans = blDao.readBookLoansByIds(bookId, branchId, cardNo);
		setObjectsByBookLoansId(bookLoans); // update attributes of book loan objects
		return bookLoans;
	}
}
