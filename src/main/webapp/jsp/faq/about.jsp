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
    <jsp:include page="/jsp/common/top.jsp">
		<jsp:param name="menuFaq" value="true"/>
	</jsp:include>
    <!-- END TOP SECTION -->

    <div class="container cust-container">
		<div class="row">
            <!-- LEFT SECTION -->
            <jsp:include page="/jsp/faq/menu.jsp">
				<jsp:param name="menuAbout" value="true"/>
			</jsp:include>
			<!-- END LEFT SECTION -->
				
            <!-- MAIN SECTION -->
            <div class="col-md-10">
            	<div class="row">
        	        <div class="col-lg-10">
                        <div class="panel-group">
	                        <div class="panel panel-default">
	                            <div class="panel-heading">
	                                <h4 class="panel-title">
	                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" aria-expanded="false" class="collapsed">
	                                    	1、qq群
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse1" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
	                                <div class="panel-body">
	                                	<p>&nbsp;&nbsp;SosoApi开发者交流群：305629848</p>
                    					<p>SwagggerUI技术交流群：482162806</p>
                    				</div>
	                            </div>
	                        </div>
	                        
	                        <div class="panel panel-default">
	                            <div class="panel-heading">
	                                <h4 class="panel-title">
	                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse2" class="collapsed" aria-expanded="false">
	                                    	2、联系我们
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse2" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
	                                <div class="panel-body">
	                                	<p>您可以通过如下方式与我们联系。</p>
					                    <ul>
					                    	<li>邮箱：3259073638@qq.com</li>
					                    	<li>&nbsp;&nbsp;&nbsp;qq：3259073638</li>
					                    </ul>
	                                </div>
	                            </div>
	                        </div>
	                	</div>
                    </div>
                </div>
            </div>
            <!-- END MAIN SECTION -->
        </div>
    </div>

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <!-- END FOOTER SECTION -->
</body>
</html>
