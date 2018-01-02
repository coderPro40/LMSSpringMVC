<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Author author = (Author) request.getAttribute("Author");
	List<Book> books = (List<Book>) request.getAttribute("Books");
%>
<%@include file="../template1.jsp"%>

<!-- Add Books -->
<section id="addBooks">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading text-uppercase">Update Author Record</h2>
				<br> <br>
			</div>
		</div>
		<div class="row text-center">
			<div class="col-md-7">
				<form action="editAuthor" method="post">
					<h4 class="service-heading">Enter Updated Name of the Author</h4>
					<input type="text" placeholder="<%=author.getAuthorName()%>"
						autocomplete="off" style="font-size: 25px;" name="name" required> <br>
					<br> Select the books written by this author<br> <select
						multiple="multiple" size="4" name="bookIds">
						<%
							for (Book b : books) {
						%>
						<option value="<%=b.getBookId()%>"><%=b.getTitle()%></option>
						<%
							}
						%>
					</select> <br> <br> 
					<input type="hidden" name="authorId"
						value="<%=author.getAuthorId()%>" />
					<!-- Add request: to value in forwardUrl to return to original home page -->
					<input type="hidden" name="homeLink"
						value="<%=request.getAttribute("homeLink")%>" />
					<button type="submit">Save</button>
				</form>
			</div>
			<div class="col-md-5">
				<h5>Changed your mind about this record?</h5>
				<br>
				<div style="width: 250px" class="btn-group-vertical">
					<!-- This should take you directly back to original url -->
					<a
						onclick="window.location='<%=request.getAttribute("homeLink")%>'"
						type="button" class="btn btn-primary" data-toggle="button"
						aria-pressed="false">Return to Record list</a>
				</div>
				<br>
			</div>
		</div>
	</div>
</section>

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

