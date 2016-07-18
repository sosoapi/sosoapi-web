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
				<jsp:param name="menuProjLog" value="true"/>
				<jsp:param name="projId" value="${param.projId}" />
				<jsp:param name="docId" value="${param.docId}" />
			</jsp:include>
			<!-- END LEFT SECTION -->
				
            <!-- MAIN SECTION -->
            <div class="col-md-10">
    	    	<div class="row">
        	    	<div class="col-lg-12">
	                    <div class="panel panel-default">
                       		<div class="panel-heading">
                           		<div class="text-muted bootstrap-admin-box-title">变更日志</div>
                           		<div class="button-groups pull-right">
                           			<a href="auth/proj/log/forwardAddLog.htm?projId=${param.projId}&docId=${param.docId}" class="text-muted">
                                   		<i class="fa fa-plus"></i> 新增日志
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
				                                	<th>标题</th>
				                                    <th>内容</th>
				                                    <th>发布者</th>
				                                    <th>发布时间</th>
				                                    <th>操作</th>
				                               </tr>
				                            </thead>
			                            
		                                 	<tbody>
		                                 		<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="logInfo" varStatus="status">
		                                 				<tr>
				                                        	<td class="col-lg-1">${status.index + 1}</td>
				                                        	<td class="col-lg-3">${logInfo.title}</td>
				                                         	<td class="col-lg-2">
				                                         		<div class="hidden">${logInfo.content}</div>
				                                         		<a class="msg-content-btn" data-toggle="modal" href="#msgContentModal">
                                									查看详情
                            									</a>
				                                         	</td>
				                                         	<td class="col-lg-2">${logInfo.pubNickName}</td>
				                                         	<td class="col-lg-2">
				                                         		<fmt:formatDate value="${logInfo.pubDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
				                                         	</td>
				                                         	<td class=" col-lg-2 actions">
			                                                   	<a onclick="delOper(${logInfo.projId},${logInfo.logId});" type="button" class="btn btn-sm btn-danger">
			                                                   		<i class="fa fa-trash"></i> 删除
			                                                   	</a>
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
            
            <div class="modal fade" id="msgContentModal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title">日志详情</h4>
                        </div>
                        <div id="msgContentModalBody" class="modal-body">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="js/project/logList.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
