<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<meta http-equiv="refresh" content="3;URL=${Cfg.WEB_BASE_URL}">
	<title></title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="bootstrap-admin-with-small-navbar">
    <!-- TOP SECTION -->
    <jsp:include page="/jsp/common/top.jsp" />
    <!-- END TOP SECTION -->

    <div class="container cust-container margin-top">
		<div class="row">
            <!-- MAIN SECTION -->
            <div class="col-md-offset-2 col-md-8">
                <div class="row">
                	<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
						    	<h3 class="panel-title">账号邮箱换绑成功，请用新的邮箱账号进行登陆</h3>
						  	</div>
							<div class="panel-body">
								<h3>
									<span id="countDownId" style="color: #f76120;">3</span>秒后自动为您跳转到官网<a href="">首页</a>
								</h3>
							</div>
							<!-- <div class="panel-footer">
							</div> -->
						</div>
					</div>
                </div>
        	</div>
    	</div>
        <!-- END MAIN SECTION -->
    </div>

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
   	<script type="text/javascript">
   		$(function(){
   			countDown("#countDownId",3);
   		});
   	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
