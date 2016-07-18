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
		<jsp:param name="menuProject" value="true"/>
	</jsp:include>
    <!-- END TOP SECTION -->

    <div class="container cust-container">
		<div class="row">
            <!-- LEFT SECTION -->
            <jsp:include page="/jsp/project/menu.jsp">
				<jsp:param name="menuNotice" value="true"/>
				<jsp:param name="projId" value="${param.projId}"/>
				<jsp:param name="docId" value="${param.docId}"/>
			</jsp:include>
			<!-- END LEFT SECTION -->
			
            <!-- MAIN SECTION -->
            <div class="col-md-10">
            	<div class="row">
        	        <div class="col-lg-12">
                        <div class="page-header bootstrap-admin-content-title">
                            <!-- TOOLBAR SECTION -->
                        	<jsp:include page="/jsp/apidoc/toolBar.jsp">
								<jsp:param name="projId" value="${param.projId}"/>
								<jsp:param name="docId" value="${param.docId}"/>
							</jsp:include>
							<!-- TOOLBAR SECTION -->
                        </div>
                        
                        <div class="bootstrap-admin-content-title">
							&nbsp;
                        </div>
                    </div>
                </div>
                
                <div class="row">
                	<div class="col-lg-12">
                		<div class="panel panel-default">
                        	<div class="panel-heading">
                            	<div class="text-muted bootstrap-admin-box-title">邮件通知</div>
                             </div>
                             <div class="bootstrap-admin-panel-content">
                             	<form id="noticeForm" class="form-horizontal">
                             		<input type="hidden" name="projId" value="${param.projId}"/>
                             		<div class="form-group">
					                    <label class="control-label col-lg-2">邮件标题</label>
					
					                    <div class="col-lg-8">
					                        <input type="text" name="title" class="form-control" value='项目[${projTempMap["" + param.projId + ""].code}] - 接口变更通知'>
					                    </div>
					                </div>
					         
					         		<div class="form-group">
					                    <label class="control-label col-lg-2">是否添加到变更历史中</label>
					
					                    <div class="col-lg-8">
					                        <select name="addLog" class="form-control">
	                                			<option value="true">是</option>
	                                			<option value="false">否</option>
									        </select>
					                    </div>
					                </div>
					                
					         		<div class="form-group">
					                    <label class="control-label col-lg-2">通知角色类型</label>
					
					                    <div class="col-lg-8">
					                        <select name="receiverRole" class="form-control">
									            <option value="">所有成员</option>
									            <option value="guest">访客</option>
	                                			<option value="admin">管理员</option>
	                                			<option value="none">无</option>
									        </select>
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <label class="control-label col-lg-2">其他通知邮箱</label>
					
					                    <div class="col-lg-8">
					                        <textarea name="otherReceivers" class="form-control" placeholder="多个邮箱以';'分隔"></textarea>
					                    </div>
					                </div>
									               
					                <div class="form-group">
					                    <label class="control-label col-lg-2">内容</label>
					
					                    <div class="col-lg-8">
					                    	<div id="noticeContent"></div>
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
			$('#noticeContent').summernote({
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
	   		
	   		$("#noticeForm").bootstrapValidator({
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
	   			if(isFormValid("noticeForm")){
	   				var param = $("#noticeForm").find("*").getFieldsValue();
	   				//富文本内容
	   				param.content = $("#noticeContent").code();
	   				doPost("auth/proj/mem/json/sendNotice.htm",param,function(data){
	   					notice("邮件发送成功!");
	   				});
	   			}
	   		});
	   	});
   	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
