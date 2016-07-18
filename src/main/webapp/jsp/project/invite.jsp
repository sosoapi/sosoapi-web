<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                                	<a href="auth/proj/mem/list.htm?projId=${param.projId}&docId=${param.docId}">项目成员</a>
                                </li>
                                <li class="active">邀请成员</li>
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
                            	<a id="directInviteTab" href="#directInvite" data-toggle="tab">直接加入</a>
                            </li>
                            
                    		<li class="">
                    			<a id="emailInviteTab" href="#emailInvite" data-toggle="tab">邮件邀请</a>
                           	</li>
                     	</ul>
                            
                      	<div class="tab-content">
                           	<div class="tab-pane fade active in" id="directInvite">
                            	<div class="row margin-top">
									<div class="col-lg-12">
										<div class="alert alert-success bootstrap-admin-alert">
								        	<a class="close" data-dismiss="alert" href="#">×</a>
								            <h4>注意:只有之前参加过同一个项目的成员才可以直接加入到项目中。</h4>
								       	</div>
								   	</div>
								</div>
								
								<div class="panel panel-default">
		                       		<div class="panel-heading">
		                           		<div class="text-muted bootstrap-admin-box-title">历史成员列表</div>
		                       		</div>
										
		                       		<div class="bootstrap-admin-panel-content">
		                       			<!-- TABLE SECTION -->
		                       			<div class="row">
		        	    					<div class="col-lg-12">
		        	    						<table id="memTable" class="table table-hover table-bordered table-responsive">
							          				<thead>
										               	<tr>
										                	<th class="col-lg-2">#</th>
										                	<th class="col-lg-2">昵称</th>
										                    <th class="col-lg-3">邮箱</th>
										                    <th class="col-lg-3">预设角色</th>
										                    <th class="col-lg-2">操作</th>
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
						                                         			<option value="guest">访客</option>
										                                	<option value="admin">管理员</option>
										                                </select>
						                                         	</td>
						                                         	<td class="actions">
						                                         		<button onclick="addMember(${memInfo.userId});" type="button" class="btn btn-sm btn-success">
					                                                   		<i class="fa fa-plus"></i> <span>添加</span>
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
												<label class="control-label col-lg-3">成员邮箱</label> 
												<div class="col-lg-6">
													<input id="invitedEmail" name="invitedEmail" class="form-control" />
												</div>
												<div class="col-lg-3">
													<button id="inviteBtn" type="button" class="btn btn-success">邀请</button>
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
