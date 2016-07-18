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
				<jsp:param name="menuProjLog" value="true"/>
				<jsp:param name="projId" value="${param.projId}" />
				<jsp:param name="docId" value="${param.docId}" />
			</jsp:include>
			<!-- END LEFT SECTION -->
			
            <!-- MAIN SECTION -->
            <div class="col-md-10">
                <div class="row">
                	<div class="col-lg-12">
                		<div class="panel panel-default">
                        	<div class="panel-heading">
                            	<div class="text-muted bootstrap-admin-box-title">新增变更日志</div>
                             </div>
                             <div class="bootstrap-admin-panel-content">
                             	<form id="msgInfoForm" class="form-horizontal">
                             		<input id="projId" type="hidden" name="projId" value="${param.projId}"/>
                             		<input id="docId" type="hidden" name="docId" value="${param.docId}"/>
                             		<div class="form-group">
					                    <label class="control-label col-lg-2">标题</label>
					                    <div class="col-lg-8">
					                        <input type="text" name="title" class="form-control">
					                    </div>
					                </div>
					         
					                <div class="form-group">
					                    <label class="control-label col-lg-2">内容</label>
					                    <div class="col-lg-8">
					                    	<textarea id="msgContent" name="content"></textarea>
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
	   				var param = $("#msgInfoForm").find("*").getFieldsValue();
	   				//富文本内容
	   				param.content = $("#msgContent").code();
	   				doPost("auth/proj/log/json/add.htm",param,function(data){
	   					window.location.href = "auth/proj/log/list.htm?projId=" + $("#projId").val() + "&docId=" + $("#docId").val();
	   				});
	   			}
	   		});
	   	});
   	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
