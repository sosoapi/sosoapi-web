<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>

	<!-- PAGE LEVEL STYLES -->
	<style type="text/css">
        .alert{
            margin: 40px auto 20px;
        }
        
        .logImg {
			margin-top:100px;
		}
		
		.alert-info{
			font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
			background-color: #428bca;
			border-color: #357ebd;
			background-image: none;
			color: white;
		}
    </style>
	<!-- END PAGE LEVEL STYLES -->
</head>
<body class="bootstrap-admin-with-small-navbar">
	<!-- TOP SECTION -->
    <jsp:include page="/jsp/common/top.jsp" />
    <!-- END TOP SECTION -->
    
    <div class="container cust-container">
        <div class="row">
            <div class="col-lg-12">
                <div class="alert alert-info text-center"><h4>密码重置</h4></div>
                <form id="resetForm" method="post" action="resetPasswd.htm" class="bootstrap-admin-login-form">
					<input type="hidden" name="code" value="${param.code}">                    
                    <div class="form-group">
                        <input class="form-control" type="password" name="passwd" placeholder="新密码">
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="password" name="confirmPasswd" placeholder="确认密码">
                    </div>

                    <button class="btn btn-lg btn-success" type="submit">重置</button>
                </form>
            </div>
        </div>
    </div>

	<!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <!-- END FOOTER SECTION -->
    <script type="text/javascript">
        $(function() {
        	//设置提示标题宽度
        	setAlertWidth();
        	
        	$("#resetForm").bootstrapValidator({
        		fields:{
        			passwd:{
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            }
                        }
        			},
                    confirmPasswd: {
                    	trigger:"blur",
                        validators: {
                        	notEmpty: {
                                message: '密码确认不能为空'
                            },
                            identical: {
                                field: 'passwd',
                                message: '输入的密码不一致'
                            }
                        }
                    }
        		}
        	});
        });
        
        function setAlertWidth(){
        	// Setting width of the alert box
            var alert = $('.alert');
            var formWidth = $('.bootstrap-admin-login-form').innerWidth();
            var alertPadding = parseInt($('.alert').css('padding'));
            if (isNaN(alertPadding)) {
                alertPadding = parseInt($(alert).css('padding-left'));
            }
            $('.alert').width(formWidth - 2 * alertPadding);
        }
    </script>
</body>
</html>
