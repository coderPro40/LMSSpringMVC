<%@page
	import="org.springframework.jdbc.datasource.DataSourceTransactionManager"%>
<%@page import="org.apache.commons.dbcp2.BasicDataSource"%>
<%@page import="org.springframework.jdbc.core.JdbcTemplate"%>
<%@page import="com.gcit.lms.dao.ConnectDAO"%>
<%@page import="com.gcit.lms.dao.BookDAO"%>
<%@page import="com.gcit.lms.service.AuthorService"%>
<%@page
	import="org.springframework.context.annotation.AnnotationConfigApplicationContext"%>
<%@page
	import="org.springframework.context.support.AbstractApplicationContext"%>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String[] authorColumns = {"Name", "Books"};
	Integer pageLength = 5, choice = 0;
	List<Author> authors = (List<Author>) request.getAttribute("getPageNoAu");
	Integer count = Integer.parseInt(request.getAttribute("countPgNoAu").toString());
	count = ((count - 1) * pageLength) + 1;

	Integer totalAuthors = (Integer) request.getAttribute("AuthorCount");
	Integer totalPages = 1;

	// for display
	if (totalAuthors % pageLength > 0) {
		totalPages = (totalAuthors / pageLength) + 1;
	} else {
		totalPages = totalAuthors / pageLength;
	}
%>
<%@include file="../template1.jsp"%>

<div class="container">
	<script type="text/javascript">
		function setChoice() {
			$("#exampleModal").modal();
		}
	</script>
	<%
		if (request.getParameterMap().containsKey("statusMessage")) {
			if (request.getAttribute("success").equals("true")) {
	%>
	<div class="alert alert-success" style="text-align: center;"
		role="alert">
		<%=request.getAttribute("statusMessage")%>
	</div>
	<%
		} else {
	%>
	<div class="alert alert-warning" style="text-align: center;"
		role="alert">
		<%=request.getAttribute("statusMessage")%>
	</div>
	<%
		}
		}
	%>
	<div class="row">
		<div class="col-lg-12 text-center">
			<h4 class="service-heading">List of all Author records</h4>
			<p id="demo"></p>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12 text-right">
			<select name="record" style="display: inline;"
				onchange="if(this.selectedIndex!=0) self.location=this.options[this.selectedIndex].value">
				<option value="">Choose different service</option>
				<option value="pageBook">Books</option>
				<option value="pageGenre">Genres</option>
				<option value="pagePublisher">Publishers</option>
				<option value="pageBorrower">Borrowers</option>
				<option value="pageBranch">Branches</option>
				<option value="PageBookloans">Book Loans</option>
			</select>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-lg-6 text-left" style="display: inline;">
			<nav aria-label="Page navigation example" id="paginationBar">
				<ul class="pagination">
					<li class="page-item"><a class="page-link" href="pageAuthor?sendPageNoAu=<%=1%>">first</a></li>
					<%
						for (int i = 1; i <= totalPages; i++) {
					%>
					<li class="page-item"><a class="page-link"
						href="pageAuthor?sendPageNoAu=<%=i%>"><%=i%></a></li>
					<%
						}
					%>
					<li class="page-item"><a class="page-link" href="pageAuthor?sendPageNoAu=<%=totalPages%>">last</a></li>
				</ul>
			</nav>
		</div>
		<div class="col-lg-6 text-right" style="display: inline;">
			<!-- need to make sure to link to specific author location on file #addAuthors -->
			<button onclick="window.location='addAuthor'"
				type="button" class="btn btn-secondary">Add New Author</button>
		</div>
	</div>
	<input class="form-control mr-sm-2" type="text"
		placeholder="Search by Author Name" aria-label="Search"
		id="searchString" oninput="searchAuthors()">

	<div class="row text-center">
		<div class="col-md-12">
			<table class="table table-striped" id="authorsTable">
				<tr>
					<th>#</th>
					<%
						for (String col : authorColumns) {
					%>
					<th><%=col%></th>
					<%
						}
					%>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<%
					Integer num = 0, offSet = 1;
					for (Author a : authors) {
				%>
				<tr>
					<td><%=count%></td>
					<%
						// use = or out.println() to print to html web page
					%>
					<td><%=a.getAuthorName()%></td>
					<td>
						<%
							for (Book b : a.getBook()) {
									out.println(b.getTitle() + " | ");
								}
								count++;
						%>
					</td>
					<td><button
							onclick="window.location='updateAuthor?authorId=<%=a.getAuthorId()%>&homeLink=pageAuthor'"
							type="button" class="btn btn-success">Edit</button></td>

					<td><button
							onclick="<%choice = authors.get(offSet - 1).getAuthorId();%>setChoice()"
							type="button" data-toggle="modal" data-target="#exampleModal"
							class="btn btn-danger">Delete</button>
				</tr>
				<%
					if (num > 0) {
							offSet++;
						}
						num++;
					}
				%>
			</table>
			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" style="text-align: center;"
								id="exampleModalLabel">Danger</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">You're about to delete this record.
							Proceed?</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-danger"
								onclick="window.location='deleteAuthor?authorId=<%=choice%>'">Yes</button>
							<button type="button" class="btn btn-success"
								data-dismiss="modal">No</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

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

