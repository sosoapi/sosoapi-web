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
		<jsp:param name="menuProject" value="true"/>
	</jsp:include>
    <!-- END TOP SECTION -->

    <div class="container cust-container">
		<div class="row">
            <!-- LEFT SECTION -->
            <jsp:include page="/jsp/project/menu.jsp">
				<jsp:param name="menuDocModule" value="true"/>
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
                        <!-- SEARCH SECTION -->
                        <form class="form-horizontal" action="auth/doc/module/list.htm" method="get">
                        	<input type="hidden" name="projId" value="${param.projId}">
                        	<input id="docId" type="hidden" name="docId" value="${param.docId}">
			                <div class="form-group">
			                	<label class="control-label col-lg-1">名称</label>
			                	<div class="col-lg-3">
			                		<input type="text" name="name" value="${param.name}" class="form-control">
			                	</div>
			                	
			                	<label class="control-label col-lg-1">描述</label>
			                	<div class="col-lg-3">
			                		<input type="text" name="description" value="${param.description}" class="form-control">
			                	</div>
			                	
			                	<div class="col-lg-3">
			                		<button type="submit" class="btn btn-default">
			                			<i class="fa fa-search"></i> 查询
			                		</button>
			                	</div>
			                </div>
			            </form>
                        <!-- END SEARCH SECTION -->
	                    <hr>
	                    
	                    <div class="panel panel-default">
                       		<div class="panel-heading">
                           		<div class="text-muted bootstrap-admin-box-title">模块列表</div>
                           		<div class="btn-group pull-right">
	                         		<!-- <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-hover="dropdown" aria-expanded="false">
	                             		更多
	                         		</button>
	                         		<ul class="dropdown-menu slidedown">
	                             		<li>
	                                 		<a id="addBtn" href="#moduleFormModal" data-toggle="modal" data-form="moduleForm">
	                                    		<i class="fa fa-plus"></i> 新增
	                                 		</a>
	                             		</li>
	                         		</ul> -->
	                         		
	                         		<a href="javascript:void(0);" data-toggle="popover" data-placement="top" data-content="排序权重值越小排的越前面。建议权重值间隔50，方便后续调整顺序。" class="text-muted">
	                                	<i class="fa fa-info-circle"></i> 排序说明
	                                </a>
	                         		&nbsp;&nbsp;
	                         		
	                         		<a id="addBtn" href="#moduleFormModal" data-toggle="modal" data-form="moduleForm" class="text-muted">
	                                	<i class="fa fa-plus"></i> 新增
	                                 </a>
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
				                                	<!-- <th>编码</th> -->
				                                    <th>名称</th>
				                                    <th>描述</th>
				                                    <th>排序权重</th>
				                                    <th>创建时间</th>
				                                    <th>操作</th>
				                               </tr>
				                            </thead>
			                            
		                                 	<tbody>
		                                    	<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="moduleInfo" varStatus="status">
		                                 				<tr>
				                                        	<td class="col-lg-1">${status.index + 1}</td>
				                                        	<%-- <td>${moduleInfo.code}</td> --%>
				                                         	<td class="col-lg-2">${moduleInfo.name}</td>
				                                         	<td class="col-lg-3">${moduleInfo.description}</td>
				                                         	<td class="col-lg-1">${moduleInfo.sortWeight}</td>
				                                         	<td class="col-lg-2">
				                                         		<fmt:formatDate value="${moduleInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
				                                         	</td>
				                                         	<td class="col-lg-3 actions">
			                                                	<button onclick="initUpdateOper(${moduleInfo.id});" data-toggle="modal" data-target="#moduleFormModal" type="button" class="btn btn-sm btn-primary">
			                                                   		<i class="fa fa-pencil"></i> 编辑
			                                                   	</button>
			                                                   	
			                                                	<button onclick="initDelOper(${moduleInfo.id});" type="button" class="btn btn-sm btn-danger">
			                                                   		<i class="fa fa-trash"></i> 删除
			                                                   	</button>
				                                           </td>
				                                     	</tr>
		                                 			</c:forEach>
		                                 		</c:if>
		                                 	</tbody>
			                            </table>
	                            	</div>
	                            </div>
	                            
	                            <jsp:include page="/jsp/common/paginate.jsp"/>
	                            
	                            <!-- MODAL SECTION -->
								<div class="row">
									<div class="col-lg-12">
										<div class="modal fade" id="moduleFormModal" tabindex="-1" role="dialog" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
														<h4 class="modal-title">模块信息</h4>
													</div>
													<div class="modal-body">
														<div class="row">
															<div class="col-lg-12">
																<form id="moduleForm" role="form" class="form-horizontal">
																	<input type="hidden" id="operTypeId" value="">
																	<input type="hidden" name="docId" value="${param.docId}">
																	<input id="moduleId" type="hidden" name="moduleId">
																	<!-- <div class="form-group">
																		<label class="control-label col-lg-3">编码</label> 
																		<div class="col-lg-6">
																			<input name="code" class="form-control" />
																		</div>
																	</div> -->
																	
																	<div class="form-group">
																		<label class="control-label col-lg-3">名称</label> 
																		<div class="col-lg-6">
																			<input name="name" class="form-control" />
																		</div>
																	</div>
																	
																	<div class="form-group">
																		<label class="control-label col-lg-3">排序权重</label> 
																		<div class="col-lg-6">
																			<input name="sortWeight" value="0" class="form-control" />
																		</div>
																	</div>
																	
												                	<div class="form-group">
													                    <label class="control-label col-lg-3">描述</label>
													                    <div class="col-lg-6">
													                        <textarea name="description" class="form-control"></textarea>
													                    </div>
													                </div>
																</form>
															</div>
														</div>
													</div>
													
													<div class="modal-footer">
														<button id="saveModuleBtn" type="button" class="btn btn-success">保存</button>
														<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- END MODAL SECTION -->
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
    <script type="text/javascript" src="js/apidoc/moduleList.js?version=1.0.3"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
