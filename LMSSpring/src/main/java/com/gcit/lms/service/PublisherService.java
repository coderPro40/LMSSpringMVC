/*
 *
 *3:25:35 AM
 *Jan 1, 2018
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.ConnectDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Publisher;

/**
 * @author ThankGod4Life
 * @date Jan 1, 2018
 *
 */
public class PublisherService extends BaseService{
	
	@Autowired
	PublisherDAO pDao;

	@Autowired
	BookDAO bDao;
	
	@Autowired
	ConnectDAO cDao;
	
	public List<Publisher> readAllPublishers()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Publisher> publisher = pDao.readAllPublishers();
		for (Publisher publisher2 : publisher) {
			publisher2.setBook(readBooksByPublisherId(publisher2.getPublisherId()));
		}
		return publisher;
	}

	public List<Book> readBooksByPublisherId(Integer publisherId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM tbl_book WHERE pubId IN (SELECT publisherId FROM tbl_publisher WHERE publisherId = ?)";
		return bDao.readAllBooksByOtherID(sql, publisherId);
	}

	public List<Publisher> readPublishersByName(String publisherName)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// no transaction taking place, just reading
		List<Publisher> publisher = pDao.readPublishersByName(publisherName);
		for (Publisher publisher2 : publisher) {
			publisher2.setBook(readBooksByPublisherId(publisher2.getPublisherId()));
		}
		return publisher;
	}

	public List<Publisher> readPublishersLimitSearch(Integer pageNo, String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (searchString != null) {
			List<Publisher> publisher = pDao.readPublishersByName(searchString);
			for (Publisher publisher2 : publisher) {
				publisher2.setBook(readBooksByPublisherId(publisher2.getPublisherId()));
			}
			return publisher;
		}
		List<Publisher> publisher = pDao.readAllPublishersWithLimit(pageNo);
		for (Publisher publisher2 : publisher) {
			publisher2.setBook(readBooksByPublisherId(publisher2.getPublisherId()));
		}
		return publisher;
	}

	public Integer getPublishersCount()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return pDao.getPublishersCount();
	}

	public Publisher readPublisherByPK(Integer pk)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Publisher publisher = pDao.readPublisherByPK(pk);
		publisher.setBook(readBooksByPublisherId(publisher.getPublisherId()));
		return publisher;
	}

	@Transactional
	public Integer addPublisherWithID(String name, String address, String phone, List<Integer> bookIds)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Publisher publisher = new Publisher();
		publisher.setPublisherName(name);
		publisher.setPublisherAddress(address);
		publisher.setPublisherPhone(phone);
		Integer id = pDao.addPublisherWithID(publisher);
		publisher.setPublisherId(id);
		List<Book> bList = readObjectsByPk(bDao, "readBookByPK", bookIds);
		for (Book book : bList) {
			book.setPublisher(publisher);
			bDao.updateBookPublisher(book); // update table with new publisher Id and each book Id
		}
		return id;
	}

	@Transactional
	public void updatePublisher(Integer id, String name, String address, String phone, List<Integer> bookIds)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Publisher publisher = pDao.readPublisherByPK(id);
		List<Book> bList = readBooksByPublisherId(id); // get currently related books
		cDao.deletePublisherBooks(bList, publisher); // remove link of pubId on table
		publisher.setPublisherName(name);
		publisher.setPublisherAddress(address);
		publisher.setPublisherPhone(phone);
		pDao.updatePublisher(publisher);
		List<Book> bList2 = readObjectsByPk(bDao, "readBookByPK", bookIds);
		for (Book book : bList2) {
			book.setPublisher(publisher);
			bDao.updateBookPublisher(book); // update table with new book Ids
		}
	}

	@Transactional
	public void deletePublisher(Integer id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Publisher publisher = pDao.readPublisherByPK(id);
		pDao.deletePublisher(publisher);
	}

}
