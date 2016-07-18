<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title></title>
		
	<!-- PAGE LEVEL STYLES -->
	<style type="text/css">
		.modal .modal-body textarea {
		    resize: auto;
		    height: auto;
		}
    </style>
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
				<jsp:param name="menuDocInfo" value="true"/>
				<jsp:param name="projId" value="${param.projId}" />
				<jsp:param name="docId" value="${param.docId}" />
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
	                            <div class="text-muted bootstrap-admin-box-title">Api文档信息</div>
	                        </div>
	                        <div class="bootstrap-admin-panel-content">
                                <form id="docInfoFrom" class="form-horizontal">
                                	<input type="hidden" name="docId" value="${docInfo.id}">
					                <div class="form-group">
					                    <label class="control-label col-lg-3">文档标题</label>
					
					                    <div class="col-lg-6">
					                        <input type="text" name="title" value="${docInfo.title}" class="form-control">
					                    </div>
					                </div>
					
					                <div class="form-group">
					                    <label class="control-label col-lg-3">访问主机</label>
					
					                    <div class="col-lg-6">
					                        <input type="text" name="host" value="${docInfo.host}" class="form-control">
					                    </div>
					                </div>
				                    
				                    <div class="form-group">
					                    <label class="control-label col-lg-3">接口基路径</label>
					
					                    <div class="col-lg-6">
					                        <input type="text" name="basePath" value="${docInfo.basePath}" class="form-control">
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <label class="control-label col-lg-3">文档版本</label>
					
					                    <div class="col-lg-6">
					                        <input type="text" name="version" value="${docInfo.version}" class="form-control">
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <label class="control-label col-lg-3">文档说明</label>
					
					                    <div class="col-lg-6">
					                        <textarea name="description" class="form-control" rows="10">${docInfo.description}</textarea>
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                	<label class="control-label col-lg-3">是否发布</label>
				
				                        <div class="col-lg-6" data-toggle="popover" data-content="只有发布后才能预览和下载">
				                            <select name="pub" class="form-control" data-initValue="${docInfo.pub}">
									            <option value="true">是</option>
									            <option value="false">否</option>
									        </select>
				                        </div>
					                </div>
					                
					                <%-- <div class="form-group">
					                	<label class="control-label col-lg-3">是否公开</label>
				
				                        <div class="col-lg-6" data-toggle="popover" data-content="公开后非项目成员也可以访问api文档">
				                            <select name="open" class="form-control" data-initValue="${docInfo.open}">
									            <option value="true">是</option>
									            <option value="false">否</option>
									        </select>
				                        </div>
					                </div> --%>
					                
				                    <div class="form-group">
				                        <div class="col-lg-1 col-lg-offset-3">
				                            <button id="saveDocBtn" class="btn btn-success btn-lg" type="button">保存</button>
				                        </div>
				                    </div>
							    </form>
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
    
    <!-- PAGE LEVEL SCRIPTS -->
    <script type="text/javascript" src="js/apidoc/docInfo.js?version=1.0.0"></script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
