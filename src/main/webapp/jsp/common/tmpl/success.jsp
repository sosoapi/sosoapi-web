<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
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
						    	<h3 class="panel-title">操作提示</h3>
						  	</div>
							<div class="panel-body">
								<h3>执行成功</h3>
								<p>后续操作</p>
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
   			$("#resendBtn").click(function(){
   				var param = new Object();
				param.email = $("#email").val();
				doPost("regist/json/sendActiveCode.htm",param,function(data){
					bootbox.alert("发送成功");
				});
   			});
   			
   			//跳转到用户注册的邮箱服务器
   			$("#forwardActiveBtn").click(function(){
   				var userMail = $("#email").val();
   				if(isNull(userMail)){
   					return ;
   				}
   				
   				var mailServer = userMail.substring(userMail.indexOf("@") + 1,userMail.length);
   				window.location = "http://mail." + mailServer;
   			});
   			
   			//提示信息
   			$('#resendBtn').popover({
   				placement: 'right', 
   				trigger: 'hover',
   				content:'请检查你的垃圾箱或广告箱，邮件有可能被误认为垃圾或广告邮件'
   			});
   		});
   	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
