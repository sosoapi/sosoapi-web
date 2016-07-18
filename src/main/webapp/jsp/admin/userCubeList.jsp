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
				<jsp:param name="menuHome" value="true"/>
			</jsp:include>
			<!-- END LEFT SECTION -->
				
            <!-- MAIN SECTION -->
            <div class="col-md-10">
            	<div class="row">
        	        <div class="col-lg-12">
                        <div class="panel panel-default">
                       		<div class="panel-heading">
                           		<div class="text-muted bootstrap-admin-box-title">今日统计</div>
                       		</div>
								
                       		<div class="bootstrap-admin-panel-content">
                       			<!-- TABLE SECTION -->
                       			<div class="row">
        	    					<div class="col-lg-12">
        	    						<div class="form-horizontal">
        	    							<div class="form-group">
	        	    							<label class="col-lg-2 text-right">统计时间：</label>
							                	<div class="col-lg-4">
							                		<fmt:formatDate value="${currentCube.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
							                	</div>
							                	
							                	<label class="col-lg-2 text-right">当前在线用户数：</label>
							                	<div class="col-lg-4">
							                		${currentCube.onlineCount}
							                	</div>
	        	    						</div>
	        	    						
	        	    						<div class="form-group">
							                	<label class="col-lg-2 text-right">总注册用户数：</label>
							                	<div class="col-lg-4">
							                		${currentCube.totalRegistCount}
							                	</div>
							                	
							                	<label class="col-lg-2 text-right">今日注册用户数：</label>
							                	<div class="col-lg-4">
							                		${currentCube.dayRegistCount}
							                	</div>
				                			</div>
				                			
				                			<div class="form-group">
							                	<label class="col-lg-2 text-right">今日登录用户数：</label>
							                	<div class="col-lg-4">
							                		${currentCube.dayLoginCount}
							                	</div>
							                	
							                	<label class="col-lg-2 text-right">今日登陆老用户数：</label>
							                	<div class="col-lg-4">
							                		${currentCube.dayOldLoginCount}
							                	</div>
				                			</div>
				                			
				                			<div class="form-group">
							                	<label class="col-lg-2 text-right">总项目数：</label>
							                	<div class="col-lg-4">
							                		${currentCube.totalProjCount}
							                	</div>
							                	
							                	<label class="col-lg-2 text-right">今日新增项目数：</label>
							                	<div class="col-lg-4">
							                		${currentCube.dayProjCount}
							                	</div>
				                			</div>
        	    						</div>
	                            	</div>
	                            </div>
                       		</div>
                    	</div>
                    </div>
                </div>
                
    	        <div class="row">
        	        <div class="col-lg-12">
                        <div class="panel panel-default">
                       		<div class="panel-heading">
                           		<div class="text-muted bootstrap-admin-box-title">历史统计</div>
                       		</div>
								
                       		<div class="bootstrap-admin-panel-content">
                       			<!-- TABLE SECTION -->
                       			<div class="row">
        	    					<div class="col-lg-12">
        	    						<table class="table table-hover table-bordered">
			                            	<thead>
			                                	<tr>
			                                    	<th>#</th>
			                                        <th>总注册用户数</th>
			                                        <th>日注册用户数</th>
			                                        <th>日登录用户数</th>
			                                        <th>日登陆老用户数</th>
			                                        <th>总项目数 </th>
			                                        <th>日新增项目数</th>
			                                        <th>统计时间</th>
			                                    </tr>
			                                </thead>
			                                <tbody>
			                                	<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="cubeInfo" varStatus="status">
		                                 				<tr>
				                                        	<td>${status.index + 1}</td>
				                                         	<td>${cubeInfo.totalRegistCount}</td>
				                                         	<td>${cubeInfo.dayRegistCount}</td>
				                                         	<td>${cubeInfo.dayLoginCount}</td>
				                                         	<td>${cubeInfo.dayOldLoginCount}</td>
				                                         	<td>${cubeInfo.totalProjCount}</td>
				                                         	<td>${cubeInfo.dayProjCount}</td>
				                                         	<td>
				                                         		<fmt:formatDate value="${cubeInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
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
            </div>
            <!-- END MAIN SECTION -->
        </div>
    </div>

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <!-- END FOOTER SECTION -->
</body>
</html>
