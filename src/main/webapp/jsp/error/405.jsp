<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>

	<!-- PAGE LEVEL STYLES -->
	<link rel="stylesheet" href="css/error.css" />
</head>
<body class="bootstrap-admin-with-small-navbar">
	<!-- TOP SECTION -->
    <jsp:include page="/jsp/common/top.jsp" />
    <!-- END TOP SECTION -->
    
	<div class="container">
		<div class="col-lg-8 col-lg-offset-2 text-center">
			<div class="logo">
				<h1>Error 405 !</h1>
			</div>
			<p class="lead text-muted">Oops, an error has occurred. Not allowed!</p>
			<div class="clearfix"></div>
			<br />
			<div class="col-lg-6  col-lg-offset-3">
				<div class="btn-group btn-group-justified">
					<a href="" class="btn btn-success">Return Website</a>
				</div>
			</div>
		</div>
	</div>
	
	<!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <!-- END FOOTER SECTION -->
</body>
</html>
