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
    <jsp:include page="/jsp/common/top.jsp" >
		<jsp:param name="menuHome" value="true"/>
	</jsp:include>
    <!-- END TOP SECTION -->

    <div class="container cust-container">
		<div class="row">
            <!-- MAIN SECTION -->
            <div class="col-md-*">
    	        <div class="row">
        	        <div class="col-lg-12">
                        <div style="text-align: center;">
                        	<a class="quick-btn" href="auth/apidoc/preview.htm?docId=1" target="_blank"> 
								<i class="fa fa-bolt fa-2x"></i> 
								<span>Demo</span> 
								<!-- <span class="label label-success">456</span> -->
							</a>
						
							<a class="quick-btn" href="${Cfg.ONLINE_HELP_URL }"> 
								<i class="fa fa-edit fa-2x"></i>
								<span> 编辑说明</span> 
								<!-- <span class="label label-danger">2</span> -->
							</a> 
							
							<a class="quick-btn" href="${Cfg.OFFLINE_HELP_URL }"> 
								<i class="fa fa-cloud fa-2x"></i>
								<span> 部署说明</span> 
								<!-- <span class="label label-danger">2</span> -->
							</a> 
							
							<a class="quick-btn" href="${Cfg.SWAGGER_UI_DOWNLOAD_URL}" style="width:180px;"> 
								<i class="fa fa-cloud-download fa-2x"></i> 
								<span>SwaggerUI扩展版下载</span> 
							</a>
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
