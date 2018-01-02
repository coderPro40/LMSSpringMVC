<%@include file="template.jsp"%>
<spring:url value="/resources/template_files/eliot-peper.jpg" var="libJpg" />
<spring:url value="/resources/template_files/clay-banks.jpg" var="lib2Jpg" />
<spring:url value="/resources/template_files/ben-white.jpg" var="authJpg" />
<spring:url value="/resources/template_files/thomas-william.jpg" var="auth2Jpg" />
<spring:url value="/resources/template_files/cesar-viteri.jpg" var="auth3Jpg" />
<spring:url value="/resources/template_files/alexandra-kirr.jpg" var="auth4Jpg" />
<spring:url value="/resources/template_files/element5-digital.jpg" var="auth5Jpg" />
<spring:url value="/resources/template_files/yaroslav-blokhin.jpg" var="borrJpg" />

<!-- Header -->
<header class="masthead">
	<div class="container">
		<div class="intro-text">
			<div class="intro-lead-in">Welcome To The Online Library
				Management System!</div>
			<div class="intro-heading text-uppercase">for the love of books</div>
			<a class="btn btn-primary btn-xl text-uppercase js-scroll-trigger"
				href="#services">Services</a>
		</div>
	</div>
</header>

<!-- Services is Librarian -->
<section id="services">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading text-uppercase">Librarian</h2>
				<br>
				<br>
			</div>
		</div>
		<div class="row text-center">
			<div class="col-md-6">
				<div class="team-member">
					<a
						href="http://localhost:8080/com.gcit.projects2web/librarianbranch.jsp">
						<img class="mx-auto rounded-circle grow"
						src="${libJpg}" alt="">
					</a>
				</div>
				<h4 class="service-heading">Modify Details</h4>
				<p class="text-muted">For Librarians seeking to view and modify
					the existing information of their library.</p>
			</div>
			<div class="col-md-6">
				<div class="team-member">
					<a
						href="http://localhost:8080/com.gcit.projects2web/librariannoofcopies.jsp">
						<img class="mx-auto rounded-circle grow"
						src="${lib2Jpg }" alt="">
					</a>
				</div>
				<h4 class="service-heading">Add books</h4>
				<p class="text-muted">Allows Librarians to add view current # of
					books owned by their library, and to add extra copies.</p>
			</div>
		</div>
	</div>
</section>

<!-- Contact is Administrator -->
<section id="contact">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading text-uppercase">Administrator</h2>
				<br>
				<br>
			</div>
		</div>
		<div class="row text-center">
			<div class="col-md-4">
				<div class="team-member">
					<a
						href="<%=request.getContextPath() %>/pageAuthor">
						<img class="mx-auto rounded-circle grow"
						src="${authJpg}" alt="">
					</a>
				</div>
				<h4 class="service-heading dark-areas">Books, authors, and
					genres</h4>
				<p class="text-muted">Administators are authorized to add,
					update, and delete books, authors, and genres in accordance with
					business requirements.</p>
			</div>
			<div class="col-md-4">
				<div class="team-member">
					<a
						href="http://localhost:8080/com.gcit.projects2web/administratorpublisher.jsp">
						<img class="mx-auto rounded-circle grow"
						src="${auth2Jpg}" alt="">
					</a>
				</div>
				<h4 class="service-heading dark-areas">Publishers</h4>
				<p class="text-muted">Administators are authorized to add,
					update, and delete book publisher in accordance with business
					requirements.</p>
			</div>
			<div class="col-md-4">
				<div class="team-member">
					<a
						href="http://localhost:8080/com.gcit.projects2web/administratorbranch.jsp">
						<img class="mx-auto rounded-circle grow"
						src="${auth3Jpg}" alt="">
					</a>
				</div>
				<h4 class="service-heading dark-areas">Library details</h4>
				<p class="text-muted">Administators are authorized to add,
					update, and delete library branch details in accordance with
					business requirements.</p>
			</div>
		</div>
		<br>
		<br>
		<div class="row text-center">
			<div class="col-md-6">
				<div class="team-member">
					<a
						href="http://localhost:8080/com.gcit.projects2web/administratorborrower.jsp">
						<img class="mx-auto rounded-circle grow"
						src="${auth4Jpg}" alt="">
					</a>
				</div>
				<h4 class="service-heading dark-areas">Borrower's information</h4>
				<p class="text-muted">Administators are authorized to add,
					update, and delete book borrower's records in accordance with
					business requirements.</p>
			</div>
			<div class="col-md-6">
				<div class="team-member">
					<a
						href="http://localhost:8080/com.gcit.projects2web/administratorbookloans.jsp">
						<img class="mx-auto rounded-circle grow"
						src="${auth5Jpg}" alt="">
					</a>
				</div>
				<h4 class="service-heading dark-areas">Due date</h4>
				<p class="text-muted">Administators are authorized to update
					book's due date in accordance with business requirements.</p>
			</div>
		</div>
	</div>
</section>

<!-- About is Borrower -->
<section id="about">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading text-uppercase">Borrower</h2>
				<br>
				<br>
			</div>
		</div>
		<div class="row text-center">
			<div class="col-md-12">
				<div class="team-member">
					<a
						href="http://localhost:8080/com.gcit.projects2web/borrowerentry.jsp">
						<img class="mx-auto rounded-circle grow"
						src="${borrJpg}" alt="">
					</a>
				</div>
				<h4 class="service-heading">Check</h4>
				<p class="text-muted">Customers are invited to Check books out
					for their reading pleasures and return them when finished.</p>
			</div>
		</div>
	</div>
</section>

<!-- Footer -->
<footer class="darken-section">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<span class="copyright dark-areas">Copyright © ThankGod
					Ofurum</span>
			</div>
		</div>
	</div>
</footer>
