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
				<jsp:param name="menuProjMem" value="true"/>
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
                        <form class="form-horizontal" action="auth/proj/mem/list.htm" method="get">
                        	<input type="hidden" name="projId" value="${param.projId}">
                        	<input type="hidden" name="docId" value="${param.docId}">
			                <div class="form-group">
			                	<label class="control-label col-lg-1">昵称</label>
			                	<div class="col-lg-2">
			                		<input type="text" name="nickName" value="${param.nickName}" class="form-control">
			                	</div>
			                	
			                	<label class="control-label col-lg-1">邮箱</label>
			                	<div class="col-lg-2">
			                		<input type="text" name="email" value="${param.email}" class="form-control">
			                	</div>
			                	
			                	<label class="control-label col-lg-1">角色</label>
			                	<div class="col-lg-2">
			                		<select class="form-control" name="role" data-initValue="${param.role}">
		                				<option value="">全部</option>
	                                	<option value="admin">管理员</option>
	                                	<!-- <option value="dev_serv">后端开发</option>
	                                	<option value="dev_client">前端开发</option> -->
	                                	<option value="guest">访客</option>
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
                           		<div class="text-muted bootstrap-admin-box-title">成员列表</div>
                           		<div class="btn-group pull-right">
                           			<auth:projAuth projId="${param.projId}">
                           				<!-- <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-hover="dropdown" aria-expanded="false">
		                             		更多
		                         		</button>
		                         		<ul class="dropdown-menu slidedown">
		                             		<li>
		                                 		<a href="#inviteModal" data-toggle="modal">
		                                    		<i class="fa fa-plus"></i> 邀请成员
		                                 		</a>
		                             		</li>
		                         		</ul> -->
                                 		
                                 		<a href="auth/proj/mem/listForAdd.htm?projId=${param.projId}&docId=${param.docId}" class="text-muted">
                                    		<i class="fa fa-plus"></i> 邀请成员
                                 		</a>
                           			</auth:projAuth>
	                     		</div>
                       		</div>
								
                       		<div class="bootstrap-admin-panel-content">
                       			<input id="projId" name="projId" type="hidden" value="${param.projId}"/>
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
			                                        <th>加入时间</th>
			                                        <th>操作</th>
			                                    </tr>
			                                </thead>
			                                <tbody>
			                                	<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="memInfo" varStatus="status">
		                                 				<tr>
				                                        	<td>${status.index + 1}</td>
				                                         	<td>
				                                         		<%-- <a href="auth/proj/mem/info.htm?userId=${memInfo.userId}">${memInfo.nickName}</a> --%>
				                                         		${memInfo.nickName}
				                                         	</td>
				                                         	<td>${memInfo.email}</td>
				                                         	<td>${memInfo.role.displayName}</td>
				                                         	<td>
				                                         		<fmt:formatDate value="${memInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
				                                         	</td>
				                                         	<td class="actions">
				                                         		<auth:projAuth projId="${param.projId}">
				                                         			<button onclick="initUpdateMember(${memInfo.userId});" type="button" class="btn btn-sm btn-primary">
				                                                   		<i class="fa fa-pencil"></i> 编辑
				                                                   	</button>
				                                                   	
				                                                	<button onclick="removeMember(${memInfo.userId})" type="button" class="btn btn-sm btn-danger">
				                                                   		<i class="fa fa-trash"></i> 移除
				                                                   	</button>
				                                         		</auth:projAuth>
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
                    </div>
                </div>
				
				<!-- MODAL SECTION -->
				<div class="row">
					<div class="col-lg-12">
						<div class="modal fade" id="updateFormModal" tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title">成员信息</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-lg-12">
												<form id="updateForm" role="form" class="form-horizontal">
													<input type="hidden" name="projId" value="${param.projId}">
													<input type="hidden" name="userId">
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
									                    <div class="col-lg-6" data-toggle="popover" data-content="对方重新登录后生效">
									                        <select class="form-control" name="role">
							                                	<option value="admin">管理员</option>
							                                	<!-- <option value="dev_serv">后端开发</option>
							                                	<option value="dev_client">前端开发</option> -->
							                                	<option value="guest">访客</option>
							                                </select>
									                    </div>
									                </div>
												</form>
											</div>
										</div>
									</div>
									
									<div class="modal-footer">
										<button id="saveMemBtn" type="button" class="btn btn-success">保存</button>
										<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- END MODAL SECTION -->
            </div>
            <!-- END MAIN SECTION -->
        </div>
    </div>

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="js/project/memList.js?version=1.0.2"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
