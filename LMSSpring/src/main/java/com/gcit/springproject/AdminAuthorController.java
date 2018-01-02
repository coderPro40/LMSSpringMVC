/*
 *
 *2:02:43 PM
 *Jan 1, 2018
 */
package com.gcit.springproject;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AuthorService;
import com.gcit.lms.service.BookService;
import com.gcit.lms.service.BorrowerService;
import com.gcit.lms.service.BranchService;
import com.gcit.lms.service.GenreService;
import com.gcit.lms.service.PublisherService;

/**
 * @author ThankGod4Life
 * @date Jan 1, 2018
 *
 */
@Controller
public class AdminAuthorController {

	@Autowired
	private AuthorService aService;

	@Autowired
	private BookService bService;

	@Autowired
	private BranchService brService;

	@Autowired
	private BorrowerService borService;

	@Autowired
	private PublisherService pService;

	@Autowired
	private GenreService gService;

	private final String homeUrl = "/admin/administratorauthor";

	// pageAuthor is the entry point for all things GET related to author
	@RequestMapping(value = "/pageAuthor", method = RequestMethod.GET)
	public String page(@RequestParam(required = false, value = "statusMessage") String message,
			@RequestParam(required = false, value = "success") String success,
			@RequestParam(required = false, defaultValue = "1", value = "sendPageNoAu") String pageNo, Model request) {
		try {
			request.addAttribute("getPageNoAu", aService.readAuthorsLimitSearch(Integer.parseInt(pageNo), null));
			request.addAttribute("countPgNoAu", pageNo);
			request.addAttribute("AuthorCount", aService.getAuthorsCount());
		} catch (NumberFormatException | InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
		}

		if (message != null) { // setup if existing
			request.addAttribute("success", success);
			request.addAttribute("statusMessage", message);
		}
		return homeUrl;
	}

	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.GET)
	public String delete(@RequestParam("authorId") String authorId, Model request) {
		String forwardUrl = "", success = "false", message = "";
		try {
			aService.deleteAuthor(Integer.parseInt(authorId));
			message = "Operation completed successfully!";
			success = "true";
		} catch (NumberFormatException | InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			message = "Operation failed! Contact Admin for further details.";
		}
		request.addAttribute("success", success);
		request.addAttribute("statusMessage", message);
		forwardUrl = "redirect:pageAuthor";
		return forwardUrl;
	}

	@RequestMapping(value = "/editAuthor", method = RequestMethod.POST)
	public String edit(@RequestParam("authorId") String authorId, @RequestParam("bookIds") String[] bookIds,
			@RequestParam("name") String name, @RequestParam("homeLink") String home, Model request) {
		String forwardUrl = "redirect:", success = "false", message = "";
		List<Integer> intBookIds = new ArrayList<>();
		for (String string : bookIds) {
			intBookIds.add(Integer.parseInt(string));
		}
		try {
			aService.updateAuthor(Integer.parseInt(authorId), name, intBookIds);
			message = "Operation completed successfully!";
			success = "true";
		} catch (NumberFormatException | InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			message = "Operation failed! Contact Admin for further details.";
		}
		request.addAttribute("success", success);
		request.addAttribute("statusMessage", message);
		forwardUrl += home; // return to original page
		return forwardUrl;
	}

	@RequestMapping(value = "/saveAuthor", method = RequestMethod.POST)
	public String save(@RequestParam("bookIds") String[] bookIds, @RequestParam("name") String name, Model request) {
		String forwardUrl = "redirect:", success = "false", message = "";
		List<Integer> intBookIds = new ArrayList<>();
		for (String string : bookIds) {
			intBookIds.add(Integer.parseInt(string));
		}
		try {
			aService.addAuthorWithID(name, intBookIds);
			message = "Operation completed successfully!";
			success = "true";
		} catch (NumberFormatException | InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			message = "Operation failed! Contact Admin for further details.";
		}
		request.addAttribute("success", success);
		request.addAttribute("statusMessage", message);
		forwardUrl += "pageAuthor"; // return to original page
		return forwardUrl;
	}

	@RequestMapping(value = "/updateAuthor", method = RequestMethod.GET)
	public String update(@RequestParam("authorId") String authorId, @RequestParam("homeLink") String home,
			@RequestParam(defaultValue = "/update/updateauthors", required = false, value = "awayLink") String away,
			Model request) {
		String forwardUrl = "";
		Author author = new Author();
		List<Book> book = new ArrayList<>();
		try {
			author = aService.readAuthorByPK(Integer.parseInt(authorId));
			book = bService.readAllBooks();
		} catch (NumberFormatException | InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
		}
		request.addAttribute("Author", author);
		request.addAttribute("Books", book);
		request.addAttribute("homeLink", home);
		forwardUrl = away; // set to redirect:homeLink to come back from update author
		return forwardUrl;
	}

	@RequestMapping(value = "/addAuthor", method = RequestMethod.GET)
	public String add(Model request) {
		String forwardUrl = "";
		try {
			request.addAttribute("Books", bService.readAllBooks());
			request.addAttribute("Branchs", brService.readAllBranchs());
			request.addAttribute("Borrowers", borService.readAllBorrowers());
			request.addAttribute("Genres", gService.readAllGenres());
			request.addAttribute("Authors", aService.readAllAuthors());
			request.addAttribute("Publishers", pService.readAllPublishers());
		} catch (NumberFormatException | InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
		}
		forwardUrl = "/admin/administratoradd"; // go to add authors section
		return forwardUrl;
	}
}
