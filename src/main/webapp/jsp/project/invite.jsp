<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title></title>
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
                	       	<ul class="breadcrumb">
                                <li>
                                	<a href="auth/proj/mem/list.htm?projId=${param.projId}&docId=${param.docId}"><fmt:message key="project.member.invite.projMembers" /></a>
                                </li>
                                <li class="active"><fmt:message key="project.member.list.inviteMember" /></li>
							</ul>
                        </div>
                    </div>
                </div>
                <div class="row">
                	<div class="col-lg-12">
                		<input id="projId" type="hidden" value="${param.projId}">
                		<input id="docId" type="hidden" value="${param.docId}">
                		
                		<ul class="nav nav-tabs">
                			<li class="active">
                            	<a id="directInviteTab" href="#directInvite" data-toggle="tab"><fmt:message key="project.member.invite.addDirect" /></a>
                            </li>
                            
                    		<li class="">
                    			<a id="emailInviteTab" href="#emailInvite" data-toggle="tab"><fmt:message key="project.member.invite.byEmail" /></a>
                           	</li>
                     	</ul>
                            
                      	<div class="tab-content">
                           	<div class="tab-pane fade active in" id="directInvite">
                            	<div class="row margin-top">
									<div class="col-lg-12">
										<div class="alert alert-success bootstrap-admin-alert">
								        	<a class="close" data-dismiss="alert" href="#">×</a>
								            <h4><fmt:message key="project.member.invite.note" /></h4>
								       	</div>
								   	</div>
								</div>
								
								<div class="panel panel-default">
		                       		<div class="panel-heading">
		                           		<div class="text-muted bootstrap-admin-box-title"><fmt:message key="project.member.invite.histroyMemberList" /></div>
		                       		</div>
										
		                       		<div class="bootstrap-admin-panel-content">
		                       			<!-- TABLE SECTION -->
		                       			<div class="row">
		        	    					<div class="col-lg-12">
		        	    						<table id="memTable" class="table table-hover table-bordered table-responsive">
							          				<thead>
										               	<tr>
										                	<th class="col-lg-2">#</th>
										                	<th class="col-lg-2"><fmt:message key="project.member.list.nickName" /></th>
										                    <th class="col-lg-3"><fmt:message key="project.member.list.email" /></th>
										                    <th class="col-lg-3"><fmt:message key="project.member.invite.setRole" /></th>
										                    <th class="col-lg-2"><fmt:message key="project.member.list.operation" /></th>
										              	</tr>
							               			</thead>
							              
							                  		<tbody>
							                  			<c:if test="${not empty pager.list}">
				                                 			<c:forEach items="${pager.list}" var="memInfo" varStatus="status">
				                                 				<tr>
						                                        	<td>${status.index + 1}</td>
						                                         	<td>
						                                         		${memInfo.nickName}
						                                         	</td>
						                                         	<td>${memInfo.email}</td>
						                                         	<td>
						                                         		<select class="form-control" name="role">
						                                         			<option value="guest"><fmt:message key="project.member.list.role.guest" /></option>
										                                	<option value="admin"><fmt:message key="project.member.list.role.admin" /></option>
										                                </select>
						                                         	</td>
						                                         	<td class="actions">
						                                         		<button onclick="addMember(${memInfo.userId});" type="button" class="btn btn-sm btn-success">
					                                                   		<i class="fa fa-plus"></i> <span><fmt:message key="project.member.list.role.admin" /></span>
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
		                       		</div>
		                    	</div>
                           	</div>
                           	
                           	<div class="tab-pane fade" id="emailInvite">
                        		<div class="row margin-top">
									<div class="col-lg-12">
										<form id="inviteForm" role="form" class="form-horizontal">
											<div class="form-group">
												<label class="control-label col-lg-3"><fmt:message key="project.member.invite.menberEmail" /></label> 
												<div class="col-lg-6">
													<input id="invitedEmail" name="invitedEmail" class="form-control" />
												</div>
												<div class="col-lg-3">
													<button id="inviteBtn" type="button" class="btn btn-success"><fmt:message key="project.member.invite.button.invite" /></button>
												</div>
											</div>
										</form>
									</div>
								</div>
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
    <script type="text/javascript" src="js/project/invite.js?version=1.0.0"></script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
