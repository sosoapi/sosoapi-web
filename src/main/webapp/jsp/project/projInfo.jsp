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
				<jsp:param name="menuProjInfo" value="true"/>
				<jsp:param name="projId" value="${projInfo.projId}"/>
				<jsp:param name="docId" value="${projInfo.docId}"/>
			</jsp:include>
			<!-- END LEFT SECTION -->
				
            <!-- MAIN SECTION -->
            <div class="col-md-10">
    	        <div class="row">
        	        <div class="col-lg-12">
                        <div class="page-header bootstrap-admin-content-title">
                        	<!-- TOOLBAR SECTION -->
                        	<jsp:include page="/jsp/apidoc/toolBar.jsp">
								<jsp:param name="projId" value="${projInfo.projId}"/>
								<jsp:param name="docId" value="${projInfo.docId}"/>
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
	                            <div class="text-muted bootstrap-admin-box-title">基本信息</div>
	                        </div>
	                        <div class="bootstrap-admin-panel-content">
                                <form id="projInfoForm" class="form-horizontal">
                                	<input type="hidden" name="projId" value="${projInfo.projId}">
                                	<div class="form-group">
					                    <label class="control-label col-lg-3">项目编码</label>
					
					                    <div class="col-lg-6">
					                        <input type="text" name="code" value="${projInfo.code}" class="form-control">
					                    </div>
					                </div>
					                
					                <div class="form-group">
					                    <label class="control-label col-lg-3">项目名称</label>
					
					                    <div class="col-lg-6">
					                        <input type="text" name="name" value="${projInfo.name}" class="form-control">
					                    </div>
					                </div>
					
					                <div class="form-group">
					                    <label class="control-label col-lg-3">项目描述</label>
					
					                    <div class="col-lg-6">
					                        <textarea name="description" class="form-control" rows="10">${projInfo.description}</textarea>
					                    </div>
					                </div>
					
					                <!-- <div class="form-group">
									    <label class="control-label col-lg-4">项目语言</label>
									
									    <div class="col-lg-4">
									        <select data-placeholder="请选择项目中使用的语言" multiple class="form-control chzn-select  chzn-rtl" tabindex="10">
									            <option>java</option>
									            <option>php</option>
									            <option>python</option>
									        </select>
									    </div>
									</div> -->
									
									<div class="form-group">
				                        <label class="control-label col-lg-3">状态</label>
				
				                        <div class="col-lg-6">
				                            <select name="status" class="form-control" data-initValue="${projInfo.status}">
									            <option value="open">开启</option>
									            <option value="close">关闭</option>
									        </select>
				                        </div>
				                    </div>
				                    
				                    <auth:projAuth projId="${param.projId}">
				                    	<div class="form-group">
					                        <div class="col-lg-1 col-lg-offset-3">
					                            <button id="saveProjInfoBtn" class="btn btn-success btn-lg" type="button">保存</button>
					                        </div>
					                    </div>
				                    </auth:projAuth>
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
    <script type="text/javascript" src="js/project/projInfo.js?version=1.0.1"></script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
