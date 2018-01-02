<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<!-- saved from url=(0057)https://blackrockdigital.github.io/startbootstrap-agency/ -->
<html lang="en" class="gr__blackrockdigital_github_io">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon"
	href="http://54.83.8.59/site/wp-content/uploads/2014/02/favicon.png">

<title>LMSWeb</title>

<!-- Bootstrap core CSS -->
	<spring:url value="/resources/template_files/bootstrap.min.css" var="bootStrapCss" />

<!-- Custom fonts for this template -->
<spring:url value="/resources/template_files/font-awesome.min.css" var="fontAwesomeCss" />
<spring:url value="/resources/template_files/css" var="regularCss" />

<!-- Custom styles for this template -->
<spring:url value="/resources/template_files/agency.min.css" var="agencyCss" />

<link href="${bootStrapCss}" rel="stylesheet" />
<link href="${fontAwesomeCss}" rel="stylesheet" />
<link href="${regularCss}" rel="stylesheet" />
<link href="${agencyCss}" rel="stylesheet" />


</head>

<body id="page-top">

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger" href="#page-top">Library
				Management System</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fa fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav text-uppercase ml-auto">
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#services">Librarian</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#contact">Administrator</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#about">Borrowers</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Bootstrap core JavaScript -->
	<spring:url value="/resources/template_files/jquery.min.js.download" var="jQueryJs" />
	<spring:url value="/resources/template_files/bootstrap.bundle.min.js.download" var="bootStrapJs" />

	<!-- Plugin JavaScript -->
	<spring:url value="/resources/template_files/jquery.easing.min.js.download" var="easingJs" />
	
	<!-- Contact form JavaScript -->
	<spring:url value="/resources/template_files/jqBootstrapValidation.js.download" var="bootSVJs" />
	<spring:url value="/resources/template_files/contact_me.js.download" var="contactMeJs" />
	
	<!-- Custom scripts for this template -->
	<spring:url value="/resources/template_files/agency.min.js.download" var="agencyJs" />
	
	<script src="${jQueryJs}"></script>
	<script src="${bootStrapJs}"></script>
	<script src="${easingJs}"></script>
	<script src="${bootSVJs}"></script>
	<script src="${contactMeJs}"></script>
	<script src="${agencyJs}"></script>

</body>
</html>