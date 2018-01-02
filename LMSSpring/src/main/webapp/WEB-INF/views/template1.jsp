<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<!-- saved from url=(0053)https://getbootstrap.com/docs/4.0/examples/jumbotron/ -->
<html lang="en" class="gr__getbootstrap_com">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon"
	href="http://54.83.8.59/site/wp-content/uploads/2014/02/favicon.png">

<title>LMSWeb</title>

<style>
.inputForms {
	style ="font-size: 25px;
	"
}
</style>

<!-- Bootstrap core CSS -->
<spring:url value="/resources/template1_files/bootstrap.min.css" var="bootStrapCss" />
<link href="${bootStrapCss}" rel="stylesheet">

<!-- Custom styles for this template -->
<spring:url value="/resources/template1_files/jumbotron.css" var="jumbotronCss" />
<link href="${jumbotronCss}" rel="stylesheet">
</head>

<body>

	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExampleDefault"
			aria-controls="navbarsExampleDefault" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsExampleDefault">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active" style="padding-left: 30px;"><a
					class="nav-link"
					href="http://localhost:8080/springproject/">Library
						Management System <span class="sr-only">(current)</span>
				</a></li>
			</ul>
			<ul class="navbar-nav nav navbar-right">
				<li class="nav-item" style="padding-right: 30px;"><a
					class="nav-link"
					href="http://localhost:8080/springproject/">Librarian</a>
				</li>
				<li class="nav-item" style="padding-right: 30px;"><a
					class="nav-link"
					href="http://localhost:8080/springproject/">Administrator</a>
				</li>
				<li class="nav-item" style="padding-right: 30px;"><a
					class="nav-link"
					href="http://localhost:8080/springproject/">Borrower</a>
				</li>
			</ul>
		</div>
	</nav>

	<main role="main"> </main>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<spring:url value="/resources/template1_files/jquery-3.2.1.min.js" var="jQueryJs" />
	<spring:url value="/resources/template1_files/popper.min.js.download" var="popperJs" />
	<spring:url value="/resources/template1_files/bootstrap.min.js.download" var="bootStrapJs" />
	
	<script src="${jQueryJs}"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="${popperJs}"></script>
	<script src="${bootStrapJs}"></script>


</body>
</html>