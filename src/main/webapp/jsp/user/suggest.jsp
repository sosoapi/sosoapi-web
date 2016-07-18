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
		<jsp:param name="menuSuggest" value="true"/>
	</jsp:include>
    <!-- END TOP SECTION -->

    <div class="container cust-container">
		<div class="row">
            <!-- MAIN SECTION -->
            <div class="col-md-*">
                <div class="row">
                	<div class="col-lg-12">
                		<div class="alert alert-success bootstrap-admin-alert">
                            <a class="close" data-dismiss="alert" href="#">×</a>
                            <h4>您的建议是对我们最大的支持~</h4>
                        </div>
                	</div>
                </div>
                
                <div class="row">
                	<div class="col-lg-12">
                		<div class="panel panel-default">
                        	<div class="panel-heading">
                            	<div class="text-muted bootstrap-admin-box-title">意见反馈</div>
                             </div>
                             <div class="bootstrap-admin-panel-content">
                             	<form id="suggestForm" class="form-horizontal">
                             		<div class="form-group">
					                    <label class="control-label col-lg-2">标题</label>
					
					                    <div class="col-lg-8">
					                        <input type="text" name="title" class="form-control">
					                    </div>
					                </div>
					         
					         		<div class="form-group">
					                    <label class="control-label col-lg-2">类型</label>
					
					                    <div class="col-lg-8">
					                        <select name="type" class="form-control">
									            <option value="bug">Bug</option>
	                                			<option value="demand">功能需求</option>
	                                			<option value="other">其他</option>
									        </select>
					                    </div>
					                </div>
									               
					                <div class="form-group">
					                    <label class="control-label col-lg-2">内容</label>
					
					                    <div class="col-lg-8">
					                    	<div id="suggestContent"></div>
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
			$('#suggestContent').summernote({
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
	   		
	   		$("#suggestForm").bootstrapValidator({
	   			fields:{
	   				title:{
	   	                validators: {
	   	                    notEmpty: {
	   	                        message: '标题不能为空'
	   	                    }
	   	                }
	   				}
	   			}
	   		});
	   		
	   		$("#saveBtn").click(function(){
	   			if(isFormValid("suggestForm")){
	   				var param = $("#suggestForm").find("*").getFieldsValue();
	   				//富文本内容
	   				param.content = $("#suggestContent").code();
	   				doPost("auth/suggest/json/add.htm",param,function(data){
	   					notice("提交成功，我们会尽快处理，谢谢~");
	   				});
	   			}
	   		});
	   	});
   	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
