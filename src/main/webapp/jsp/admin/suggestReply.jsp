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
    <jsp:include page="/jsp/common/top.jsp">
		<jsp:param name="menuHome" value="true"/>
	</jsp:include>
    <!-- END TOP SECTION -->

    <div class="container cust-container">
		<div class="row">
            <!-- LEFT SECTION -->
            <jsp:include page="/jsp/admin/menu.jsp">
				<jsp:param name="menuSuggest" value="true"/>
			</jsp:include>
			<!-- END LEFT SECTION -->
			
            <!-- MAIN SECTION -->
            <div class="col-md-10">
                <div class="row">
                	<div class="col-lg-12">
                		<div class="panel panel-default">
                        	<div class="panel-heading">
                            	<div class="text-muted bootstrap-admin-box-title">反馈回复</div>
                             </div>
                             <div class="bootstrap-admin-panel-content">
                             	<form id="replyForm" class="form-horizontal">
                             		<input type="hidden" name="suggestId" value="${suggestInfo.suggestId}"/>
                             		<div class="form-group">
					                    <label class="control-label col-lg-2">收件人</label>
					
					                    <div class="col-lg-8">
					                        <input type="text" class="form-control" name="toEmail" value="${suggestInfo.email}">
					                    </div>
					                </div>

                             		<div class="form-group">
					                    <label class="control-label col-lg-2">邮件标题</label>
					
					                    <div class="col-lg-8">
					                        <input type="text" name="title" class="form-control" value='SosoApi - 反馈回复'>
					                    </div>
					                </div>
					         
					                <div class="form-group">
					                    <label class="control-label col-lg-2">内容</label>
					
					                    <div class="col-lg-8">
					                    	<div id="content">
					                    		<p>您好，</p>
					                    		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;感谢使用SosoApi。</p>
					                    		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您之前的反馈"${suggestInfo.title}"已收到。</p>
					                    	</div>
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <div class="col-lg-offset-2 col-lg-8">
					                        <div class="form-actions">
		                                    	<input id="saveBtn" type="button" value="提交" class="btn btn-success" />
		                                	</div>
					                    </div>
					                </div>
                            	</form>
                             </div>
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
			$('#content').summernote({
				height: 300, 
				minHeight: null,
				maxHeight: null,
				focus: true,
				lang: 'zh-CN', // default: 'en-US'
				toolbar: [
			          ['style', ['style','fontname','fontsize','height']],
			          ['color', ['color']],
			          ['font', ['bold', 'italic', 'underline', 'clear']],
			          ['para', ['ul', 'ol', 'paragraph']],
			          ['insert', ['table','link','hr']],
			          ['view', ['fullscreen','preview']]
			  	]
			});
	   		
	   		$("#replyForm").bootstrapValidator({
	   			fields:{
	   				title:{
	   	                validators: {
	   	                    notEmpty: {
	   	                        message: '邮件标题不能为空'
	   	                    }
	   	                }
	   				}
	   			}
	   		});
	   		
	   		$("#saveBtn").click(function(){
	   			if(isFormValid("replyForm")){
	   				var param = $("#replyForm").find("*").getFieldsValue();
	   				//富文本内容
	   				param.content = $("#content").code();
	   				doPost("admin/suggest/json/reply.htm",param,function(data){
	   					notice("邮件发送成功!");
	   				});
	   			}
	   		});
	   	});
   	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
