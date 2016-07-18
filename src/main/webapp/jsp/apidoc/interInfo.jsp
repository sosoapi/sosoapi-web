<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title></title>
		
	<!-- PAGE LEVEL STYLES -->
	<link rel="stylesheet" href="plugin/context-menu/css/jquery.contextmenu.css" />
	<link rel="stylesheet" href="plugin/treegrid/css/jquery.treegrid.css" />
	<style type="text/css">
		#respSchemaFormModal .modal-dialog {
			width: 800px;
			margin: 30px auto;
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
				<jsp:param name="menuDocInter" value="true"/>
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
                	       	<ul class="breadcrumb">
                                <li>
                                	<a href="auth/doc/inter/list.htm?projId=${param.projId}&docId=${param.docId}">接口管理</a>
                                </li>
                                <li>
                                	<a href="auth/doc/inter/list.htm?projId=${param.projId}&docId=${param.docId}&moduleId=${interInfo.moduleId}">${empty interInfo.moduleName ? 'default' : interInfo.moduleName}</a>
                                </li>
                                <li class="active">${interInfo.name}</li>
							</ul>
                        </div>
                    </div>
                </div>
                <div class="row">
                	<div class="col-lg-12">
                		<input id="projId" type="hidden" value="${param.projId}">
                		<input id="docId" type="hidden" value="${param.docId}">
                		<input id="interId" type="hidden" value="${param.interId}">
                		
                		<ul class="nav nav-tabs">
                    		<li class="active">
                    			<a id="basicInfoTab" href="#basicInfo" data-toggle="tab">基本信息</a>
                           	</li>
                            <li class="">
                            	<a id="reqInfoTab" href="#reqInfo" data-toggle="tab">请求信息</a>
                            </li>
                            <li class="">
                            	<a id="respInfoTab" href="#respInfo" data-toggle="tab">响应信息</a>
                           	</li>
                     	</ul>
                            
                      	<div class="tab-content">
                        	<div class="tab-pane fade active in" id="basicInfo">
                        		<jsp:include page="/jsp/apidoc/inter/basicInfo.jsp"/>
                           	</div>
                            
                           	<div class="tab-pane fade" id="reqInfo">
                            	<jsp:include page="/jsp/apidoc/inter/reqInfo.jsp"/>
                           	</div>
                            
                           	<div class="tab-pane fade" id="respInfo">
                            	<jsp:include page="/jsp/apidoc/inter/respInfo.jsp"/>
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
    <script type="text/javascript" src="plugin/context-menu/ext/js/jquery.contextmenu.js"></script>
	<script type="text/javascript" src="plugin/treegrid/ext/js/jquery.treegridExt.js?version=1.0.0"></script>
    <script type="text/javascript" src="js/apidoc/inter/basicInfo.js?version=1.0.1"></script>
    <script type="text/javascript" src="js/apidoc/inter/reqInfo.js?version=1.0.7"></script>
    <script type="text/javascript" src="js/apidoc/inter/respInfo.js?version=1.0.10"></script>
    <script type="text/javascript" src="js/apidoc/interInfo.js?version=1.0.2"></script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
