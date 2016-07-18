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
		<jsp:param name="menuHome" value="true"/>
	</jsp:include>
    <!-- END TOP SECTION -->

    <div class="container cust-container">
		<div class="row">
            <!-- LEFT SECTION -->
            <jsp:include page="/jsp/admin/menu.jsp">
				<jsp:param name="menuUser" value="true"/>
			</jsp:include>
			<!-- END LEFT SECTION -->
				
            <!-- MAIN SECTION -->
            <div class="col-md-10">
    	    	<div class="row">
        	    	<div class="col-lg-12">
                        <!-- SEARCH SECTION -->
                        <form class="form-horizontal" action="admin/user/list.htm" method="get">
			                <div class="form-group">
			                	<label class="control-label col-lg-1">昵称</label>
			                	<div class="col-lg-2">
			                		<input type="text" name="nickName" value="${param.nickName}" class="form-control">
			                	</div>
			                	
			                	<label class="control-label col-lg-1">邮箱</label>
			                	<div class="col-lg-2">
			                		<input type="text" name="email" value="${param.email}" class="form-control">
			                	</div>
			                	
			                	<label class="control-label col-lg-1">验证</label>
			                	<div class="col-lg-2">
			                		<select name="valid" class="form-control" data-initValue="${param.valid}">
		                				<option value="">全部</option>
	                                	<option value="true">是</option>
	                                	<option value="false">否</option>
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
                           		<div class="text-muted bootstrap-admin-box-title">用户列表</div>
                       		</div>
								
                       		<div class="bootstrap-admin-panel-content">
                       			<!-- TABLE SECTION -->
                       			<div class="row">
        	    					<div class="col-lg-12">
										<table class="table table-hover table-bordered">
			                        		<thead>
				                            	<tr>
				                                	<th>#</th>
				                                	<th>昵称</th>
				                                    <th>邮箱</th>
				                                    <th>角色</th>
				                                    <th>验证</th>
				                                    <th>锁定</th>
				                                    <th>注册时间</th>
				                                    <th>操作</th>
				                               </tr>
				                            </thead>
			                            
		                                 	<tbody>
		                                 		<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="userInfo" varStatus="status">
		                                 				<tr>
				                                        	<td>${status.index + 1}</td>
				                                        	<td>${userInfo.nickName}</td>
				                                         	<td>${userInfo.email}</td>
				                                         	<td>${userInfo.role.displayName}</td>
				                                         	<td>${userInfo.valid ? '是' : '否'}</td>
				                                         	<td>${userInfo.locked ? '是' : '否'}</td>
				                                         	<td>
				                                         		<fmt:formatDate value="${userInfo.registDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
				                                         	</td>
				                                         	<td class="actions">
			                                         			<button type="button" onclick="initUpdateOper(${userInfo.userId});" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#userFormModal">
			                                                   		<i class="fa fa-pencil"></i> 编辑
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
										<div class="modal fade" id="userFormModal" tabindex="-1" role="dialog" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
														<h4 class="modal-title">用户信息</h4>
													</div>
													<div class="modal-body">
														<div class="row">
															<div class="col-lg-12">
																<form id="userForm" role="form" class="form-horizontal">
																	<input id="userId" type="hidden" name="userId">
																	<div class="form-group">
																		<label class="control-label col-lg-3">昵称</label> 
																		<div class="col-lg-6">
																			<input name="nickName" class="form-control" readonly/>
																		</div>
																	</div>
																	
																	<div class="form-group">
																		<label class="control-label col-lg-3">邮箱</label> 
																		<div class="col-lg-6">
																			<input name="email" class="form-control" readonly/>
																		</div>
																	</div>
																	
																	<div class="form-group">
																		<label class="control-label col-lg-3">角色</label>
													                	<div class="col-lg-6">
													                		<select name="role" class="form-control">
											                                	<option value="admin">管理员</option>
											                                	<option value="normal">普通用户</option>
											                                </select>
													                	</div>
			                										</div>
			                										
			                										<div class="form-group">
				                										<label class="control-label col-lg-3">是否验证</label>
													                	<div class="col-lg-6">
													                		<select name="valid" class="form-control">
											                                	<option value="true">是</option>
											                                	<option value="false">否</option>
											                                </select>
													                	</div>
												                	</div>
												                	
												                	<div class="form-group">
													                	<label class="control-label col-lg-3">是否锁定</label>
													                	<div class="col-lg-6">
													                		<select name="locked" class="form-control">
											                                	<option value="true">是</option>
											                                	<option value="false">否</option>
											                                </select>
													                	</div>
												                	</div>
																</form>
															</div>
														</div>
													</div>
													
													<div class="modal-footer">
														<button id="saveUserBtn" type="button" class="btn btn-success">保存</button>
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
    <script type="text/javascript" src="js/admin/userList.js?version=1.0.0"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
