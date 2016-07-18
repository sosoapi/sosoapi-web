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
				<jsp:param name="menuMsg" value="true"/>
			</jsp:include>
			<!-- END LEFT SECTION -->
			
            <!-- MAIN SECTION -->
            <div class="col-md-10">
                <div class="row">
                	<div class="col-lg-12">
                		<div class="alert alert-success bootstrap-admin-alert text-center">
                            <a class="close" data-dismiss="alert" href="#">×</a>
                            <h4>发布系统消息</h4>
                        </div>
                	</div>
                </div>
                
                <div class="row">
                	<div class="col-lg-12">
                		<div class="panel panel-default">
                        	<div class="panel-heading">
                            	<div class="text-muted bootstrap-admin-box-title">系统消息</div>
                             </div>
                             <div class="bootstrap-admin-panel-content">
                             	<form id="msgInfoForm" method="post" action="${action}" class="form-horizontal">
                             		<input type="hidden" name="msgId" value="${msgInfo.id}" />
                             		<div class="form-group">
					                    <label class="control-label col-lg-2">标题</label>
					                    <div class="col-lg-8">
					                        <input type="text" name="title" class="form-control" value="${msgInfo.title}">
					                    </div>
					                </div>
					         
					         		<div class="form-group">
					                    <label class="control-label col-lg-2">消息类型</label>
					                    <div class="col-lg-8">
					                        <select name="msgType" class="form-control" data-initValue="${msgInfo.msgType}">
			                                	<option value="notice">公告</option>
			                                	<option value="version">版本升级</option>
			                                	<option value="other">其他</option>
			                                </select>
					                    </div>
					                </div>
									       
									<div class="form-group">
					                    <label class="control-label col-lg-2">对象类型</label>
					                    <div class="col-lg-8">
					                        <select name="userRole" class="form-control" data-initValue="${msgInfo.userRole}">
			                                	<option value="normal">普通用户</option>
			                                	<option value="admin">管理员</option>
			                                </select>
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <label class="control-label col-lg-2">消息状态</label>
					                    <div class="col-lg-8">
					                        <select name="msgStatus" class="form-control" data-initValue="${msgInfo.msgStatus}">
			                                	<option value="pub">发布</option>
			                                	<option value="unpub">未发布</option>
			                                </select>
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <label class="control-label col-lg-2">内容</label>
					                    <div class="col-lg-8">
					                    	<textarea id="msgContent" name="content">${msgInfo.content}</textarea>
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
	   		$('#msgContent').summernote({
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
	   	
	   		$("#msgInfoForm").bootstrapValidator({
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
	   			if(isFormValid("msgInfoForm")){
	   				bootbox.confirm("确定发布当前消息？",function(){
	   					$("#msgInfoForm").bootstrapValidator('defaultSubmit');
		   			});
	   			}
	   		});
	   	});
   	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
