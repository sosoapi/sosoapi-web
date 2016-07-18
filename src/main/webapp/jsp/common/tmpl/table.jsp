<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title></title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="bootstrap-admin-with-small-navbar">
    <!-- TOP SECTION -->
    <jsp:include page="/jsp/common/top.jsp" />
    <!-- END TOP SECTION -->

    <div class="container cust-container">
		<div class="row">
            <!-- LEFT SECTION -->
            <%-- <jsp:include page="/jsp/common/left.jsp">
				<jsp:param name="" value="true"/>
			</jsp:include> --%>
			<!-- END LEFT SECTION -->
				
            <!-- MAIN SECTION -->
            <div class="col-md-10">
    	        <div class="row">
        	        <div class="col-lg-12">
            	        <div class="bootstrap-admin-content-title">
                	       	<ul class="breadcrumb">
								<li>
                                	<i class="fa fa-home"></i>
                                    <a href="javascript:void(0);">home</a>
                                </li>
                                <li>
                                    <a href="#">home</a>
                                </li>
                                <li class="active">home</li>
							</ul>
                        </div>
                        
                        <!-- CONTENT SECTION -->
                        <form class="form-horizontal">
			                <div class="form-group">
			                	<label class="control-label col-lg-1">编码</label>
			                	<div class="col-lg-2">
			                		<input type="text" value="" class="form-control">
			                	</div>
			                	
			                	<label class="control-label col-lg-1">描述</label>
			                	<div class="col-lg-2">
			                		<input type="text" value="" class="form-control">
			                	</div>
			                	
			                	<label class="control-label col-lg-1">范围</label>
			                	<div class="col-lg-2">
			                		<select class="form-control">
		                				<option value="">全部</option>
	                                </select>
			                	</div>
			                	
			                	<div class="col-lg-3">
			                		<button type="button" class="btn btn-default">
			                			<i class="fa fa-search"></i> 查询
			                		</button>
			                	</div>
			                </div>
			            </form>
                        <!-- END SEARCH SECTION -->
	                    <hr>
	                    
	                    <div class="panel panel-default">
                       		<div class="panel-heading">
                           		<div class="text-muted bootstrap-admin-box-title">错误码列表</div>
                           		<div class="button-groups pull-right">
                           			<div class="btn-group">
                           				<button type="button" class="btn btn-default btn-xs">
		                             		菜单1
		                         		</button>
                           			</div>
                           			
                           			<div class="btn-group">
                           				<button type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#">
                                        	<i class="fa fa-plus"></i> 新增
                                       	</button>
                                       	
                                 		<a href="#" data-toggle="modal" class="text-muted">
                                    		<i class="fa fa-plus"></i> 新增
                                 		</a>
                                 		
                           				<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-hover="dropdown" aria-expanded="false">
		                             		更多
		                         		</button>
		                         		<ul class="dropdown-menu slidedown">
		                             		<li>
		                                 		<a href="javascript:void(0);">
		                                    		<i class="fa fa-plus"></i> 菜单2
		                                 		</a>
		                             		</li>
		                         		</ul>
                           			</div>
                           		</div>
                       		</div>
								
                       		<div class="bootstrap-admin-panel-content">
                       			<!-- TABLE SECTION -->
                       			<div class="row">
        	    					<div class="col-lg-12">
        	    						<table id="contentTable" class="table table-hover table-bordered">
			                        		<thead>
				                            	<tr>
				                                	<th>#</th>
				                                    <th>编码</th>
				                                    <th>描述</th>
				                                    <th>适用范围</th>
				                                    <th>创建时间</th>
				                                    <th>操作</th>
				                               </tr>
				                            </thead>
			                            
		                                 	<tbody>
		                                    	<tr>
		                                        	<td>1</td>
		                                         	<td>
		                                         		<input name="code" value="0" class="form-control">
		                                         	</td>
		                                         	<td>执行成功</td>
		                                         	<td>全局</td>
		                                         	<td>2015-07-04</td>
		                                         	<td class="actions">
		                                         		<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#formModal">
	                                                   		<i class="fa fa-pencil"></i> 编辑
	                                                   	</button>
	                                                   	
		                                            	<button type="button" class="btn btn-sm btn-danger oper-del">
	                                                   		<i class="fa fa-trash"></i> 删除
	                                                   	</button>
		                                           </td>
		                                     	</tr>
		                                     	<tr>
		                                        	<td>2</td>
		                                        	<td>
		                                         		<input name="code" value="1" class="form-control">
		                                         	</td>
		                                         	<td>系统繁忙</td>
		                                         	<td>全局</td>
		                                         	<td>2015-07-04</td>
		                                         	<td class="actions">
		                                         		<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#formModal">
	                                                   		<i class="fa fa-pencil"></i> 编辑
	                                                   	</button>
	                                                   	
		                                            	<button type="button" class="btn btn-sm btn-danger oper-del">
	                                                   		<i class="fa fa-trash"></i> 删除
	                                                   	</button>
		                                           </td>
		                                     	</tr>
		                                 	</tbody>
			                            </table>
	                            	</div>
	                            </div>
	                            
	                            <div class="row">
	                            	<div class="col-md-6" style="margin-top: 20px;">
	                            		<button id="addRowBtn" type="button" class="btn btn-success">
											<i class="fa fa-plus"></i> 新增
										</button>
										<button id="getDataBtn" type="button" class="btn btn-success">
											<i class="fa fa-plus"></i> 获取数据
										</button>
	                            	</div>
	                            	
									<div class="col-md-6">
										<div class="dataTables_paginate paging_bootstrap">
											<ul class="pagination">
												<li class="prev disabled"><a href="#">上一页</a></li>
												<li class="active"><a href="#">1</a></li>
												<li><a href="#">2</a></li>
												<li><a href="#">3</a></li>
												<li><a href="#">4</a></li>
												<li><a href="#">5</a></li>
												<li class="next"><a href="#">下一页 </a></li>
											</ul>
										</div>
									</div>
								</div>
                       		</div>
                    	</div>
                        <!-- END CONTENT SECTION -->
                    </div>
                </div>
                
                <!-- MODAL SECTION -->
				<div class="row">
					<div class="col-lg-12">
						<div class="modal fade" id="formModal" tabindex="-1" role="dialog" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title">错误码信息</h4>
									</div>
									<div class="modal-body">
										<form role="form" class="form-horizontal">
											<div class="form-group">
												<label class="control-label col-lg-3">编码</label> 
												<div class="col-lg-6">
													<input class="form-control" />
												</div>
											</div>
											
											<div class="form-group">
												<label class="control-label col-lg-3">描述</label> 
												<div class="col-lg-6">
													<input class="form-control" />
												</div>
											</div>
											
						                	<div class="form-group">
						                		<label class="control-label col-lg-3">范围</label>
						                		<div class="col-lg-6">
													<select class="form-control">
						                				<option value="">全部</option>
					                                </select>
												</div>
						                	</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary">保存</button>
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
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
	<script type="text/javascript" src="jsp/common/tmpl/js/table.js"></script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
