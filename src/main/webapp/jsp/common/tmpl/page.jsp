<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title></title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- <link href="" rel="stylesheet" /> -->
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="bootstrap-admin-with-small-navbar">
    <!-- TOP SECTION -->
    <jsp:include page="/jsp/common/top.jsp" />
    <!-- END TOP SECTION -->

    <div class="container cust-container">
		<div class="row">
            <!-- LEFT SECTION -->
            <jsp:include page="/jsp/common/left.jsp">
				<jsp:param name="" value="true"/>
			</jsp:include>
			<!-- END LEFT SECTION -->
				
            <!-- MAIN SECTION -->
            <div class="col-md-10">
    	        <div class="row">
        	        <div class="col-lg-12">
            	        <!-- <div class="navbar navbar-default bootstrap-admin-navbar-thin">
                            <ol class="breadcrumb bootstrap-admin-breadcrumb">
                                <li>
                                	<i class="fa fa-home"></i>
                                    <a href="javascript:void(0);">home</a>
                                </li>
                                <li>
                                    <a href="#">home</a>
                                </li>
                                <li class="active">home</li>
                            </ol>
                        </div> -->
                            
            	        <div class="bootstrap-admin-content-title">
                	       	<ul class="breadcrumb">
								<li>
                                	<i class="fa fa-home"></i>
                                    <a href="javascript:void(0);">home</a>
                                </li>
                                <li>
                                    <a href="#">home</a>
                                </li>
                                <li class="active">home</li>
							</ul>
                        </div>
                        
                        <!-- CONTENT SECTION -->
                        <!-- END CONTENT SECTION -->
                    </div>
                </div>
            </div>
            <!-- END MAIN SECTION -->
        </div>
    </div>

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
	<!-- <script type="text/javascript" src=""></script> -->
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
