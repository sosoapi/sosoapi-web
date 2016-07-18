<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title></title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- <link href="" rel="stylesheet" /> -->
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="bootstrap-admin-with-small-navbar">
    <!-- TOP SECTION -->
    <jsp:include page="/jsp/common/top.jsp">
		<jsp:param name="menuApiDoc" value="true"/>
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
                            <h4>只有在"项目管理 -> {具体项目} -> Api文档信息"中的"是否发布"更改为"发布"后才会显示在该列表中。</h4>
                        </div>
                	</div>
                </div>
                
    	        <div class="row">
        	        <div class="col-lg-12">
                        <!-- CONTENT SECTION -->
                        <div class="panel panel-default">
                       		<div class="panel-heading">
                           		<div class="text-muted bootstrap-admin-box-title">api文档列表</div>
                           		<div class="button-groups pull-right">
                           			<%-- <div class="btn-group">
                           				<a href="${Cfg.SWAGGER_UI_DOWNLOAD_URL}" class="text-muted">
		                             		<i class="fa fa-cloud-download"></i> 下载SwaggerUI扩展版
		                         		</a>
                           			</div> --%>
                           		</div>
                       		</div>
								
                       		<div class="bootstrap-admin-panel-content">
                       			<!-- TABLE SECTION -->
                       			<div class="row">
        	    					<div class="col-lg-12">
										<table class="table table-hover table-bordered">
			                        		<thead>
				                            	<tr>
				                                	<th>#</th>
				                                    <th>文档标题</th>
				                                    <th>版本</th>
				                                    <th>项目编码</th>
				                                    <th>项目名称</th>
				                                    <th>创建时间</th>
				                                    <th>操作</th>
				                               </tr>
				                            </thead>
			                            
		                                 	<tbody>
		                                 		<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="docInfo" varStatus="status">
		                                 				<tr>
				                                        	<td>${status.index + 1}</td>
				                                        	<td>${docInfo.docTitle}</td>
				                                         	<td>${docInfo.version}</td>
				                                         	<td>${docInfo.projCode}</td>
				                                         	<td>${docInfo.projName}</td>
				                                         	<td>
				                                         		<fmt:formatDate value="${docInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
				                                         	</td>
				                                         	<td class="actions">
			                                                	<a href="auth/apidoc/preview.htm?docId=${docInfo.docId}" target="_bank" class="btn btn-sm btn-success">
			                                                   		<i class="fa fa-eye"></i> 预览
			                                                   	</a>
			                                                   	
			                                                   	<div class="btn-group">
															        <button class="btn btn-sm btn-default">
															        	<i class="fa fa-share"></i> 导出
															        </button>
															        
															        <button data-toggle="dropdown" class="btn btn-sm btn-default dropdown-toggle"><span class="caret"></span></button>
															        <ul class="dropdown-menu">
															            <li>
															            	<a href="auth/apidoc/export.htm?docId=${docInfo.docId}&docFormat=html">
															            		<i class="fa fa-file-text"></i> html文档
															            	</a>
															            </li>
															            <li>
															            	<a href="auth/apidoc/export.htm?docId=${docInfo.docId}&docFormat=doc">
															            		<i class="fa fa-file-word-o"></i> word文档
															            	</a>
															            </li>
															            <li>
															            	<a href="auth/apidoc/export.htm?docId=${docInfo.docId}&docFormat=json">
															            		<i class="fa fa-file-code-o"></i> json文档
															            	</a>
															            </li>
															        </ul>
															    </div>
				                                           </td>
				                                     	</tr>
		                                 			</c:forEach>
		                                 		</c:if>
		                                 	</tbody>
			                            </table>
	                            	</div>
	                            </div>
	                            
	                            <jsp:include page="/jsp/common/paginate.jsp"/>
							</div>
						</div>
                        <!-- END CONTENT SECTION -->
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
	<!-- <script type="text/javascript" src=""></script> -->
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
