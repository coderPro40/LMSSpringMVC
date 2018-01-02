/*
 *
 *11:59:06 AM
 *Jan 1, 2018
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.ConnectDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;

/**
 * @author ThankGod4Life
 * @date Jan 1, 2018
 *
 */
public class BorrowerService extends BaseService {

	@Autowired
	BorrowerDAO borDao;

	@Autowired
	BookDAO bDao;

	@Autowired
	BranchDAO brDao;

	@Autowired
	BookCopiesService bcService;

	@Autowired
	BookLoansService blService;

	@Autowired
	ConnectDAO cDao;

	public List<Borrower> readAllBorrowers()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Borrower> borrower = borDao.readAllBorrowers();
		for (Borrower borrower2 : borrower) {
			setObjectsByCardNo(borrower2); // update attributes of borrower objects
		}
		return borrower;
	}

	public void setObjectsByCardNo(Borrower borrower)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_loans WHERE cardNo = ?)";
		String sql2 = "SELECT * FROM tbl_library_branch WHERE branchId IN (SELECT branchId FROM tbl_book_loans WHERE cardNo = ?)";
		borrower.setBook(bDao.readAllBooksByOtherID(sql, borrower.getCardNo()));
		borrower.setBranch(brDao.readAllBranchByOtherID(sql2, borrower.getCardNo()));
	}

	public List<Borrower> readBorrowersByName(String name)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Borrower> borrower = borDao.readBorrowersByName(name);
		for (Borrower borrower2 : borrower) {
			setObjectsByCardNo(borrower2); // update attributes of borrower objects
		}
		return borrower;
	}

	public Borrower readBorrowerByPK(Integer pk)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Borrower borrower = borDao.readBorrowerByPK(pk);
		setObjectsByCardNo(borrower);
		return borrower;
	}

	@Transactional
	public void addBorrowerWithID(String name, String address, String phone, List<Integer> bookIds, List<Integer> branchIds,
			String dateOut, String dueDate)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Borrower borrower = new Borrower();
		borrower.setName(name);
		borrower.setAddress(address);
		borrower.setPhone(phone);
		Integer id = borDao.addBorrowerWithID(borrower);
		borrower.setCardNo(id);
		List<Book> bList = readObjectsByPk(bDao, "readBookByPK", bookIds);
		List<Branch> brList = readObjectsByPk(brDao, "readBranchByPK", branchIds);
		for (Book book : bList) {
			for (Branch branch : brList) {
				blService.addBookLoans(book, branch, borrower, dateOut, dueDate);
			}
		}
		List<BookCopies> currentCopies = bcService.readAllBookCopies();

		for (Book book : bList) {
			for (Branch branch : brList) {
				boolean newCopy = true;
				for (BookCopies bookCopies : currentCopies) {
					if ((bookCopies.getBook().getBookId() == book.getBookId())
							&& (bookCopies.getBranch().getBranchId() == branch.getBranchId())) {
						bcService.updateBookCopiesByIds(book.getBookId(), branch.getBranchId(), -1);
						newCopy = false;
					}
				}
				if (newCopy) {
					bcService.addBookCopies(book.getBookId(), branch.getBranchId(), 3);
				}
			}
		}
	}

	@Transactional
	public void updateBorrower(Integer cardNo, String name, String address, String phone, List<Integer> bookIds,
			List<Integer> branchIds, String dateOut, String dueDate)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Borrower borrower = borDao.readBorrowerByPK(cardNo);
		setObjectsByCardNo(borrower); // complete setup process
		cDao.deleteBorrowerBookLoans(borrower.getBook(), borrower.getBranch(), borrower); // just need to remove book
																							// loans table data
		borrower.setName(name);
		borrower.setAddress(address);
		borrower.setPhone(phone);
		borDao.updateBorrower(borrower); // update the database table
		List<Book> bList = readObjectsByPk(bDao, "readBookByPK", bookIds);
		List<Branch> brList = readObjectsByPk(brDao, "readBranchByPK", branchIds);
		for (Book book : bList) {
			for (Branch branch : brList) {
				blService.addBookLoans(book, branch, borrower, dateOut, dueDate);
			}
		}
		List<BookCopies> currentCopies = bcService.readAllBookCopies();

		for (Book book : bList) {
			for (Branch branch : brList) {
				boolean newCopy = true;
				for (BookCopies bookCopies : currentCopies) {
					if ((bookCopies.getBook().getBookId() == book.getBookId())
							&& (bookCopies.getBranch().getBranchId() == branch.getBranchId())) {
						bcService.updateBookCopiesByIds(book.getBookId(), branch.getBranchId(), -1);
						newCopy = false;
					}
				}
				if (newCopy) {
					bcService.addBookCopies(book.getBookId(), branch.getBranchId(), 3);
				}
			}
		}
	}

	@Transactional
	public void deleteBorrower(Integer id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Borrower borrower = borDao.readBorrowerByPK(id);
		borDao.deleteBorrower(borrower);
	}

	public List<Borrower> readBorrowersLimitSearch(Integer pageNo, String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (searchString != null) {
			List<Borrower> borrower = borDao.readBorrowersByName(searchString);
			for (Borrower borrower2 : borrower) {
				setObjectsByCardNo(borrower2); // update attributes of borrower objects
			}
			return borrower;
		}
		List<Borrower> borrower = borDao.readAllBorrowersWithLimit(pageNo);
		for (Borrower borrower2 : borrower) {
			setObjectsByCardNo(borrower2); // update attributes of borrower objects
		}
		return borrower;
	}

	public Integer getBorrowersCount()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return borDao.getBorrowersCount();
	}

}
