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
            <!-- MAIN SECTION -->
            <div class="col-md-*">
    	    	<div class="row">
        	    	<div class="col-lg-12">
                        <!-- SEARCH SECTION -->
                        <form class="form-horizontal" action="auth/proj/list.htm" method="get">
			                <div class="form-group">
			                	<label class="control-label col-lg-1">编码</label>
			                	<div class="col-lg-2">
			                		<input type="text" name="code" value="${param.code}" class="form-control">
			                	</div>
			                	
			                	<label class="control-label col-lg-1">名称</label>
			                	<div class="col-lg-2">
			                		<input type="text" name="name" value="${param.name}" class="form-control">
			                	</div>
			                	
			                	<label class="control-label col-lg-1">状态</label>
			                	<div class="col-lg-2">
			                		<select name="status" class="form-control" data-initValue="${param.status}">
		                				<option value="">全部</option>
	                                	<option value="open">启用</option>
	                                	<option value="close">关闭</option>
	                                </select>
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
                           		<div class="text-muted bootstrap-admin-box-title">项目列表</div>
                           		<div class="button-groups pull-right">
                           			<div class="btn-group">
                           				<!-- <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-hover="dropdown" aria-expanded="false">
		                             		更多
		                         		</button>
		                         		<ul class="dropdown-menu slidedown">
		                             		<li>
		                                 		<a href="#projFormModal" data-toggle="modal">
		                                    		<i class="fa fa-plus"></i> 新增
		                                 		</a>
		                             		</li>
		                         		</ul> -->
                                       	
                                 		<a href="#projFormModal" data-toggle="modal" class="text-muted">
                                    		<i class="fa fa-plus"></i> 新增
                                 		</a>
                           			</div>
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
				                                	<th>编码</th>
				                                    <th>名称</th>
				                                    <th>角色</th>
				                                    <th>创建时间</th>
				                                    <th>状态</th>
				                                    <th>操作</th>
				                               </tr>
				                            </thead>
			                            
		                                 	<tbody>
		                                 		<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="projInfo" varStatus="status">
		                                 				<tr>
				                                        	<td>${status.index + 1}</td>
				                                        	<td>
				                                         		<a href="auth/proj/info.htm?projId=${projInfo.projId}">${projInfo.code}</a>
				                                         	</td>
				                                         	<td>${projInfo.name}</td>
				                                         	<td>${projInfo.role.displayName}</td>
				                                         	<td>
				                                         		<fmt:formatDate value="${projInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
				                                         	</td>
				                                         	<td>${projInfo.status.displayName}</td>
				                                         	<td class="actions">
				                                         		<button onclick="quitProj(${projInfo.projId});" type="button" class="btn btn-sm btn-primary">
			                                                   		<i class="fa fa-frown-o"></i> 退出
			                                                   	</button>
			                                                   	
				                                         		<c:if test="${projInfo.role == 'admin'}">
					                                         		<!-- <button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#projFormModal">
				                                                   		<i class="fa fa-pencil"></i> 编辑
				                                                   	</button> -->
				                                                   	<button onclick="copyProj(${projInfo.projId},'${projInfo.name}');" type="button" class="btn btn-sm btn-success">
				                                                   		<i class="fa fa-plus"></i> 复制
				                                                   	</button>
				                                                   	
				                                                	<button onclick="delProj(${projInfo.projId});" type="button" class="btn btn-sm btn-danger">
				                                                   		<i class="fa fa-trash"></i> 删除
				                                                   	</button>
				                                         		</c:if>
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
										<div class="modal fade" id="projFormModal" tabindex="-1" role="dialog" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
														<h4 class="modal-title">项目信息</h4>
													</div>
													<div class="modal-body">
														<div class="row">
															<div class="col-lg-12">
																<form id="projForm" role="form" class="form-horizontal">
																	<div class="form-group">
																		<label class="control-label col-lg-3">编码</label> 
																		<div class="col-lg-6">
																			<input name="code" class="form-control" />
																		</div>
																	</div>
																	
																	<div class="form-group">
																		<label class="control-label col-lg-3">名称</label> 
																		<div class="col-lg-6">
																			<input name="name" class="form-control" />
																		</div>
																	</div>
																	
												                	<div class="form-group">
												                		<label class="control-label col-lg-3">状态</label>
												                		<div class="col-lg-6">
																			<select name="status" class="form-control">
												                				<option value="open">开启</option>
												                				<option value="close">关闭</option>
											                                </select>
																		</div>
												                	</div>
																</form>
															</div>
														</div>
													</div>
													
													<div class="modal-footer">
														<button id="saveProjBtn" type="button" class="btn btn-success">保存</button>
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
    <script type="text/javascript" src="plugin/jquery.query.js"></script>
    <script type="text/javascript" src="js/project/projList.js?version=1.0.1"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
