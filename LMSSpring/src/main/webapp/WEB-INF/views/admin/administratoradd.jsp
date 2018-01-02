<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	List<Author> authors = (List<Author>) request.getAttribute("Authors");
	List<Genre> genres = (List<Genre>) request.getAttribute("Genres");
	List<Book> books = (List<Book>) request.getAttribute("Books");
	List<Publisher> publishers = (List<Publisher>) request.getAttribute("Publishers");
	List<Borrower> borrowers = (List<Borrower>) request.getAttribute("Borrowers");
	List<Branch> branches = (List<Branch>) request.getAttribute("Branchs");
%>
<%@include file="../template1.jsp"%>

<!-- Add Books -->
<section id="addBooks">
	<br> <br> <br>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading text-uppercase">Add Books</h2>
				<br> <br>
			</div>
		</div>
		<div class="row text-center">
			<div class="col-md-7">
				<form action="saveBook" method="post">
					<h4 class="service-heading">Enter Title of Book to be added</h4>
					<input type="text" autocomplete="off" style="font-size: 25px;"
						name="title" required> <br> Select the names of the
					book authors<br> <select multiple="multiple" size="4"
						name="authorIds">
						<%
							for (Author a : authors) {
						%>
						<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName()%></option>
						<%
							}
						%>
					</select> <br> Select the genres of the book<br> <select
						multiple="multiple" size="4" name="genreIds">
						<%
							for (Genre g : genres) {
						%>
						<option value="<%=g.get_Genre_Id()%>"><%=g.get_Genre_Name()%></option>
						<%
							}
						%>
					</select> <br> Select the publisher of the book<br> <select
						name="publisherId">
						<%
							for (Publisher p : publishers) {
						%>
						<option value="<%=p.getPublisherId()%>"><%=p.getPublisherName()%></option>
						<%
							}
						%>
					</select> <br> <br>
					<button type="submit">Save</button>
				</form>
			</div>
			<div class="col-md-5">
				<h4 class="service-heading">Add other items to the library</h4>
				<br>
				<div style="width: 250px" class="btn-group-vertical">
					<a onclick="window.location='#addPublishers'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Publisher</a>
					<a onclick="window.location='#addAuthors'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Author</a>
					<a onclick="window.location='#addGenres'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Genre</a>
					<a onclick="window.location='#addBorrowers'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Borrower</a>
					<a onclick="window.location='#addBranches'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Library
						Branch</a>
				</div>
			</div>
		</div>
	</div>
</section>

<br>
<hr style="border-width: 3px; border-style: solid;">
<br>

<!-- Add Publishers -->
<section id="addPublishers">
	<br> <br> <br>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading text-uppercase">Add Publishers</h2>
				<br> <br>
			</div>
		</div>
		<div class="row text-center">
			<div class="col-md-7">
				<form action="savePublisher" method="post">
					<h4 class="service-heading">Enter Name of Publisher to be
						added</h4>
					<input type="text" autocomplete="off" style="font-size: 25px;"
						name="name" required> <br> <br> Select the
					publisher's address<br> <input type="text" autocomplete="off"
						style="font-size: 25px;" name="address" required> <br>
					<br> Select the publisher's phone #<br> <input
						type="text" autocomplete="off" style="font-size: 25px;"
						name="phone" required> <br> <br> Select the
					books published<br> <select multiple="multiple" size="4"
						name="bookIds">
						<%
							for (Book b : books) {
						%>
						<option value="<%=b.getBookId()%>"><%=b.getTitle()%></option>
						<%
							}
						%>
					</select> <br> <br>
					<button type="submit">Save</button>
				</form>
			</div>
			<div class="col-md-5">
				<h4 class="service-heading">Add other items to the library</h4>
				<br>
				<div style="width: 250px" class="btn-group-vertical">
					<a onclick="window.location='#addBooks'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Book</a>
					<a onclick="window.location='#addAuthors'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Author</a>
					<a onclick="window.location='#addGenres'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Genre</a>
					<a onclick="window.location='#addBorrowers'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Borrower</a>
					<a onclick="window.location='#addBranches'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Library
						Branch</a>
				</div>
			</div>
		</div>
	</div>
</section>

<br>
<hr style="border-width: 3px; border-style: solid;">
<br>

<!-- Add Authors-->
<section id="addAuthors">
	<br> <br> <br>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading text-uppercase">Add Authors</h2>
				<br> <br>
			</div>
		</div>
		<div class="row text-center">
			<div class="col-md-7">
				<form action="saveAuthor" method="post">
					<h4 class="service-heading">Enter Name of the Author to be
						added</h4>
					<input type="text" autocomplete="off" style="font-size: 25px;"
						name="name" required> <br> <br> Select the books
					written by this author<br> <select multiple="multiple"
						size="4" name="bookIds">
						<%
							for (Book b : books) {
						%>
						<option value="<%=b.getBookId()%>"><%=b.getTitle()%></option>
						<%
							}
						%>
					</select> <br> <br>
					<button type="submit">Save</button>
				</form>
			</div>
			<div class="col-md-5">
				<h4 class="service-heading">Add other items to the library</h4>
				<br>
				<div style="width: 250px" class="btn-group-vertical">
					<a onclick="window.location='#addBooks'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Book</a>
					<a onclick="window.location='#addPublishers'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Publisher</a>
					<a onclick="window.location='#addGenres'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Genre</a>
					<a onclick="window.location='#addBorrowers'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Borrower</a>
					<a onclick="window.location='#addBranches'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Library
						Branch</a>
				</div>
			</div>
		</div>
	</div>
</section>

<br>
<hr style="border-width: 3px; border-style: solid;">
<br>

<!-- Add Genres -->
<section id="addGenres">
	<br> <br> <br>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading text-uppercase">Add Genres</h2>
				<br> <br>
			</div>
		</div>
		<div class="row text-center">
			<div class="col-md-7">
				<form action="saveGenre" method="post">
					<h4 class="service-heading">Enter Genre to be added</h4>
					<input type="text" autocomplete="off" style="font-size: 25px;"
						name="name" required> <br> <br> Select the books
					that belong to this genre<br> <select multiple="multiple"
						size="4" name="bookIds">
						<%
							for (Book b : books) {
						%>
						<option value="<%=b.getBookId()%>"><%=b.getTitle()%></option>
						<%
							}
						%>
					</select> <br> <br>
					<button type="submit">Save</button>
				</form>
			</div>
			<div class="col-md-5">
				<h4 class="service-heading">Add other items to the library</h4>
				<br>
				<div style="width: 250px" class="btn-group-vertical">
					<a onclick="window.location='#addBooks'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Book</a>
					<a onclick="window.location='#addPublishers'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Publisher</a>
					<a onclick="window.location='#addAuthors'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Author</a>
					<a onclick="window.location='#addBorrowers'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Borrower</a>
					<a onclick="window.location='#addBranches'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Library
						Branch</a>
				</div>
			</div>
		</div>
	</div>
</section>

<br>
<hr style="border-width: 3px; border-style: solid;">
<br>

<!-- Add Borrowers-->
<section id="addBorrowers">
	<br> <br> <br>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading text-uppercase">Add Borrowers</h2>
				<br> <br>
			</div>
		</div>
		<div class="row text-center">
			<div class="col-md-7">
				<form action="saveBorrower" method="post">
					<h4 class="service-heading">Enter Name of the Borrower to be
						added</h4>
					<input type="text" autocomplete="off" style="font-size: 25px;"
						name="name" required> <br> <br> Select the
					borrower's address<br> <input type="text" autocomplete="off"
						style="font-size: 25px;" name="address" required> <br>
					<br> Select the borrower's phone #<br> <input type="text"
						autocomplete="off" style="font-size: 25px;" name="phone" required>
					<br> <br> Select the books borrowed <br> <select
						multiple="multiple" size="4" name="bookIds">
						<%
							for (Book b : books) {
						%>
						<option value="<%=b.getBookId()%>"><%=b.getTitle()%></option>
						<%
							}
						%>
					</select> <br> <br> Select the branches borrowed from<br> <select
						multiple="multiple" size="4" name="branchIds">
						<%
							for (Branch br : branches) {
						%>
						<option value="<%=br.getBranchId()%>"><%=br.getBranchName()%></option>
						<%
							}
						%>
					</select> <br> <br>
					<button type="submit">Save</button>
				</form>
			</div>
			<div class="col-md-5">
				<h4 class="service-heading">Add other items to the library</h4>
				<br>
				<div style="width: 250px" class="btn-group-vertical">
					<a onclick="window.location='#addBooks'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Book</a>
					<a onclick="window.location='#addPublishers'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Publisher</a>
					<a onclick="window.location='#addAuthors'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Author</a>
					<a onclick="window.location='#addGenres'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Genre</a>
					<a onclick="window.location='#addBranches'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Library
						Branch</a>
				</div>
			</div>
		</div>
	</div>
</section>

<br>
<hr style="border-width: 3px; border-style: solid;">
<br>

<!-- Add Library Branches-->
<section id="addBranches">
	<br> <br> <br>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading text-uppercase">Add Library Branches</h2>
				<br> <br>
			</div>
		</div>
		<div class="row text-center">
			<div class="col-md-7">
				<form action="saveBranch" method="post">
					<h4 class="service-heading">Enter Branch Name to be added</h4>
					<input type="text" autocomplete="off" style="font-size: 25px;"
						name="name" required> <br> <br> Select the
					branch address<br> <input type="text" autocomplete="off"
						style="font-size: 25px;" name="address" required> <br>
					<br> Select the books borrowed <br> <select
						multiple="multiple" size="4" name="bookIds">
						<%
							for (Book b : books) {
						%>
						<option value="<%=b.getBookId()%>"><%=b.getTitle()%></option>
						<%
							}
						%>
					</select> <br> <br>
					<button type="submit">Save</button>
				</form>
			</div>
			<div class="col-md-5">
				<h4 class="service-heading">Add other items to the library</h4>
				<br>
				<div style="width: 250px" class="btn-group-vertical">
					<a onclick="window.location='#addBooks'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Book</a>
					<a onclick="window.location='#addPublishers'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Publisher</a>
					<a onclick="window.location='#addAuthors'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Author</a>
					<a onclick="window.location='#addGenres'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Genre</a>
					<a onclick="window.location='#addBorrowers'" type="button"
						class="btn btn-primary" data-toggle="button" aria-pressed="false">Borrower</a>
				</div>
			</div>
		</div>
	</div>
</section>

<br>
<br>
<br>

<footer class="fixed-bottom" style="background-color: #212529;">
	<div class="container">
		<div class="row text-center">
			<div class="col-md-12">
				<p style="color: #ffffff;">Copyright © ThankGod Ofurum</p>
			</div>
		</div>
	</div>
</footer>
