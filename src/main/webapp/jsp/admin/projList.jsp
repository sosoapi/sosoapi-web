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
				<jsp:param name="menuProj" value="true"/>
			</jsp:include>
			<!-- END LEFT SECTION -->
				
            <!-- MAIN SECTION -->
            <div class="col-md-10">
    	    	<div class="row">
        	    	<div class="col-lg-12">
                        <!-- SEARCH SECTION -->
                        <form class="form-horizontal" action="admin/proj/list.htm" method="get">
			                <div class="form-group">
			                	<label class="control-label col-lg-1">编码</label>
			                	<div class="col-lg-2">
			                		<input type="text" name="code" value="${param.code}" class="form-control">
			                	</div>
			                	
			                	<label class="control-label col-lg-1">昵称</label>
			                	<div class="col-lg-2">
			                		<input type="text" name="nickName" value="${param.nickName}" class="form-control">
			                	</div>
			                	
			                	<label class="control-label col-lg-1">状态</label>
			                	<div class="col-lg-2">
			                		<select name="status" class="form-control" data-initValue="${param.status}">
		                				<option value="">全部</option>
	                                	<option value="open">开启</option>
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
				                                    <th>状态</th>
				                                    <th>成员数目</th>
				                                    <th>创建者昵称</th>
				                                    <th>创建时间</th>
				                               </tr>
				                            </thead>
			                            
		                                 	<tbody>
		                                 		<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="projInfo" varStatus="status">
		                                 				<tr>
				                                        	<td>${status.index + 1}</td>
				                                        	<td>${projInfo.code}</td>
				                                         	<td>${projInfo.name}</td>
				                                         	<td>${projInfo.status.displayName}</td>
				                                         	<td>${projInfo.memCount}</td>
				                                         	<td>${projInfo.createrNickName}</td>
				                                         	<td>
				                                         		<fmt:formatDate value="${projInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
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
