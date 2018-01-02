/*
 *
 *9:25:24 AM
 *Jan 1, 2018
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.ConnectDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Branch;

/**
 * @author ThankGod4Life
 * @date Jan 1, 2018
 *
 */
public class BranchService extends BaseService {

	@Autowired
	BranchDAO brDao;

	@Autowired
	BookDAO bDao;

	@Autowired
	BookCopiesDAO bcDao;

	@Autowired
	BookLoansDAO blDao;

	@Autowired
	BorrowerDAO borDao;

	@Autowired
	ConnectDAO cDao;

	public List<Branch> readAllBranchs()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Branch> branch = brDao.readAllBranchs();
		for (Branch branch2 : branch) {
			setObjectsByBranchId(branch2); // update attributes of branch objects
		}
		return branch;
	}

	public void setObjectsByBranchId(Branch branch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_copies WHERE branchId = ?)";
		String sql2 = "SELECT * FROM tbl_borrower WHERE cardNo IN (SELECT cardNo FROM tbl_book_loans WHERE branchId = ?)";
		branch.setBook(bDao.readAllBooksByOtherID(sql, branch.getBranchId()));
		branch.setBorrower(borDao.readAllBorrowersByOtherID(sql2, branch.getBranchId()));
	}

	public List<Branch> readBranchsByName(String name)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Branch> branch = brDao.readBranchsByName(name);
		for (Branch branch2 : branch) {
			setObjectsByBranchId(branch2); // update attributes of branch objects
		}
		return branch;
	}

	public Branch readBranchByPK(Integer pk)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Branch branch = brDao.readBranchByPK(pk);
		setObjectsByBranchId(branch);
		return branch;
	}

	@Transactional
	public void addBranchWithID(String name, String address, List<Integer> bookIds)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Branch branch = new Branch();
		branch.setBranchName(name);
		branch.setBranchAddress(address);
		Integer id = brDao.addBranchWithID(branch);
		branch.setBranchId(id);
		List<Book> bList = readObjectsByPk(bDao, "readBookByPK", bookIds);
		for (Book book : bList) {
			BookCopies bookCopies = new BookCopies();
			bookCopies.setBook(book);
			bookCopies.setBranch(branch);
			bcDao.addBookCopies(bookCopies);
		}
	}

	@Transactional
	public void updateBranch(Integer branchId, String name, String address, List<Integer> bookIds)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Branch branch = brDao.readBranchByPK(branchId);
		setObjectsByBranchId(branch); // complete setup process
		cDao.deleteBranchBooks(branch.getBook(), branch); // remove from database tables
		branch.setBranchName(name);
		branch.setBranchAddress(address);
		brDao.updateBranch(branch); // update the database table
		List<Book> bList = readObjectsByPk(bDao, "readBookByPK", bookIds);
		for (Book book : bList) {
			BookCopies bookCopies = new BookCopies();
			bookCopies.setBook(book);
			bookCopies.setBranch(branch);
			bcDao.addBookCopies(bookCopies);
		}
	}

	@Transactional
	public void deleteBranch(Integer id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Branch branch = brDao.readBranchByPK(id);
		brDao.deleteBranch(branch);
	}

	public List<Branch> readBranchsLimitSearch(Integer pageNo, String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (searchString != null) {
			List<Branch> branch = brDao.readBranchsByName(searchString);
			for (Branch branch2 : branch) {
				setObjectsByBranchId(branch2); // update attributes of branch objects
			}
			return branch;
		}
		List<Branch> branch = brDao.readAllBranchesWithLimit(pageNo);
		for (Branch branch2 : branch) {
			setObjectsByBranchId(branch2); // update attributes of branch objects
		}
		return branch;
	}

	public Integer getBranchsCount()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return brDao.getBranchesCount();
	}

}
