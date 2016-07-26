<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
			                	<label class="control-label col-lg-1"><spring:message code="project.list.page.number"/></label>
			                	<div class="col-lg-2">
			                		<input type="text" name="code" value="${param.code}" class="form-control">
			                	</div>
			                	
			                	<label class="control-label col-lg-1"><fmt:message key="project.list.page.name" /></label>
			                	<div class="col-lg-2">
			                		<input type="text" name="name" value="${param.name}" class="form-control">
			                	</div>
			                	
			                	<label class="control-label col-lg-1"><fmt:message key="project.list.page.status" /></label>
			                	<div class="col-lg-2">
			                		<select name="status" class="form-control" data-initValue="${param.status}">
		                				<option value=""><fmt:message key="project.list.page.all" /></option>
	                                	<option value="open"><fmt:message key="project.list.page.open" /></option>
	                                	<option value="close"><fmt:message key="project.list.page.close" /></option>
	                                </select>
			                	</div>
			                	
			                	<div class="col-lg-3">
			                		<button type="submit" class="btn btn-default">
			                			<i class="fa fa-search"></i> <fmt:message key="project.list.page.search" />
			                		</button>
			                	</div>
			                </div>
			            </form>
                        <!-- END SEARCH SECTION -->
	                    <hr>
	                    
	                    <div class="panel panel-default">
                       		<div class="panel-heading">
                           		<div class="text-muted bootstrap-admin-box-title"><fmt:message key="project.list.page.proList" /></div>
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
                                    		<i class="fa fa-plus"></i> <fmt:message key="project.list.page.new" />
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
				                                	<th><fmt:message key="project.list.page.number" /> </th>
				                                    <th><fmt:message key="project.list.page.name" /></th>
				                                    <th><fmt:message key="project.list.page.role" /></th>
				                                    <th><fmt:message key="project.list.page.createTime" /></th>
				                                    <th><fmt:message key="project.list.page.status" /></th>
				                                    <th><fmt:message key="project.list.page.operation" /></th>
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
			                                                   		<i class="fa fa-frown-o"></i> <fmt:message key="project.list.page.quit" />
			                                                   	</button>
			                                                   	
				                                         		<c:if test="${projInfo.role == 'admin'}">
					                                         		<!-- <button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#projFormModal">
				                                                   		<i class="fa fa-pencil"></i> 编辑
				                                                   	</button> -->
				                                                   	<button onclick="copyProj(${projInfo.projId},'${projInfo.name}');" type="button" class="btn btn-sm btn-success">
				                                                   		<i class="fa fa-plus"></i> <fmt:message key="project.list.page.copy" />
				                                                   	</button>
				                                                   	
				                                                	<button onclick="delProj(${projInfo.projId});" type="button" class="btn btn-sm btn-danger">
				                                                   		<i class="fa fa-trash"></i> <fmt:message key="project.list.page.delete" />
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
														<h4 class="modal-title"><fmt:message key="project.list.page.projInfo" /></h4>
													</div>
													<div class="modal-body">
														<div class="row">
															<div class="col-lg-12">
																<form id="projForm" role="form" class="form-horizontal">
																	<div class="form-group">
																		<label class="control-label col-lg-3"><fmt:message key="project.list.page.number" /></label> 
																		<div class="col-lg-6">
																			<input name="code" class="form-control" />
																		</div>
																	</div>
																	
																	<div class="form-group">
																		<label class="control-label col-lg-3"><fmt:message key="project.list.page.name" /></label> 
																		<div class="col-lg-6">
																			<input name="name" class="form-control" />
																		</div>
																	</div>
																	
												                	<div class="form-group">
												                		<label class="control-label col-lg-3"><fmt:message key="project.list.page.status" /></label>
												                		<div class="col-lg-6">
																			<select name="status" class="form-control">
												                				<option value="open"><fmt:message key="project.list.page.open" /></option>
												                				<option value="close"><fmt:message key="project.list.page.close" /></option>
											                                </select>
																		</div>
												                	</div>
																</form>
															</div>
														</div>
													</div>
													
													<div class="modal-footer">
														<button id="saveProjBtn" type="button" class="btn btn-success"><fmt:message key="project.list.page.save" /></button>
														<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="project.list.page.cancle" /></button>
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
