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
		<jsp:param name="menuUserHome" value="true"/>
	</jsp:include>
    <!-- END TOP SECTION -->

    <div class="container cust-container">
		<div class="row">
            <!-- LEFT SECTION -->
            <jsp:include page="/jsp/user/menu.jsp">
				<jsp:param name="menuMsg" value="true"/>
			</jsp:include>
			<!-- END LEFT SECTION -->
				
            <!-- MAIN SECTION -->
            <div class="col-md-10">
    	    	<div class="row">
        	    	<div class="col-lg-12">
                        <!-- SEARCH SECTION -->
                        <form class="form-horizontal" action="auth/msg/list.htm" method="get">
			                <div class="form-group">
			                	<label class="control-label col-lg-1">标题</label>
			                	<div class="col-lg-2">
			                		<input type="text" name="title" value="${param.title}" class="form-control">
			                	</div>
			                	
			                	<label class="control-label col-lg-1">状态</label>
			                	<div class="col-lg-2">
			                		<select name="isDeal" class="form-control" data-initValue="${param.isDeal}">
	                                	<option value="">全部</option>
	                                	<option value="false">未读</option>
	                                	<option value="true">已读</option>
	                                </select>
			                	</div>

			                	<label class="control-label col-lg-1">类型</label>
			                	<div class="col-lg-2">
			                		<select name="msgType" class="form-control" data-initValue="${param.msgType}">
		                				<option value="">全部</option>
	                                	<option value="notice">公告</option>
	                                	<option value="version">版本升级</option>
	                                	<option value="other">其他</option>
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
                           		<div class="text-muted bootstrap-admin-box-title">消息列表</div>
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
				                                    <th>类型</th>
				                                    <th>状态</th>
				                                    <th>发布时间</th>
				                                    <th>操作</th>
				                               </tr>
				                            </thead>
			                            
		                                 	<tbody>
		                                 		<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="msgInfo" varStatus="status">
		                                 				<tr>
				                                        	<td class="col-lg-1">${status.index + 1}</td>
				                                        	<td class="col-lg-2">${msgInfo.title}</td>
				                                         	<td class="col-lg-3">
				                                         		<div class="hidden">${msgInfo.content}</div>
				                                         		<a class="msg-content-btn" data-toggle="modal" href="#msgContentModal">
                                									查看详情
                            									</a>
				                                         	</td>
				                                         	<td class="col-lg-1">${msgInfo.msgType.displayName}</td>
				                                         	<td class="col-lg-1">${msgInfo.deal ? '已读' : '未读'}</td>
				                                         	<td class="col-lg-2">
				                                         		<fmt:formatDate value="${msgInfo.pubDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
				                                         	</td>
				                                         	<td class="actions col-lg-2">
				                                         		<button onclick="dealOper(${msgInfo.id});" type="button" class="btn btn-sm btn-primary">
			                                                   		<i class="fa fa-frown-o"></i> 已读
			                                                   	</button>
			                                                   	
				                                         		<button onclick="delOper(${msgInfo.id});" type="button" class="btn btn-sm btn-danger">
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
                            <h4 class="modal-title">消息详情</h4>
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
    <script type="text/javascript" src="js/user/msgList.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
