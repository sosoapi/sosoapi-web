<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title></title>
		
	<!-- PAGE LEVEL STYLES -->
	<link rel="stylesheet" href="plugin/context-menu/css/jquery.contextmenu.css" />
	<link rel="stylesheet" href="plugin/treegrid/css/jquery.treegrid.css" />
	<style type="text/css">
		#schemaFormModal .modal-dialog {
			width: 800px;
			margin: 30px auto;
		}
    </style>
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
				<jsp:param name="menuDocSchema" value="true"/>
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
                        <form class="form-horizontal" action="auth/doc/schema/list.htm" method="get">
                        	<input type="hidden" name="projId" value="${param.projId}">
                        	<input type="hidden" name="docId" value="${param.docId}">
			                <div class="form-group">
			                	<label class="control-label col-lg-1">编码</label>
			                	<div class="col-lg-3">
			                		<input type="text" name="code" value="${param.code}" class="form-control">
			                	</div>
			                	
			                	<label class="control-label col-lg-1">描述</label>
			                	<div class="col-lg-3">
			                		<input type="text" name="description" value="${param.description}" class="form-control">
			                	</div>
			                	
			                	<div class="col-lg-4">
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
                           		<div class="text-muted bootstrap-admin-box-title">数据结构列表</div>
                           		<div class="btn-group pull-right">
	                         		<!-- <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-hover="dropdown" aria-expanded="false">
	                             		更多
	                         		</button>
	                         		<ul class="dropdown-menu slidedown">
	                             		<li>
	                                 		<a id="addBtn" href="#schemaFormModal" data-toggle="modal" data-form="custSchemaForm">
	                                    		<i class="fa fa-plus"></i> 新增
	                                 		</a>
	                             		</li>
	                         		</ul> -->
	                         		
	                         		<a id="addBtn" href="#schemaFormModal" data-toggle="modal" data-form="custSchemaForm" class="text-muted">
	                                	<i class="fa fa-plus"></i> 新增
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
			                                    	<th>编码</th>
			                                       <!--  <th>名称</th> -->
			                                        <th>描述</th>
			                                        <th>类型</th>
			                                        <th>创建时间</th>
			                                        <th>操作</th>
			                                    </tr>
			                                </thead>
			                                <tbody>
			                                	<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="schemaInfo" varStatus="status">
		                                 				<tr>
				                                        	<td class="col-lg-1">${status.index + 1}</td>
				                                        	<td class="col-lg-2">${schemaInfo.code}</td>
				                                         	<%-- <td>${schemaInfo.name}</td> --%>
				                                         	<td class="col-lg-3">${schemaInfo.description}</td>
				                                         	<td class="col-lg-2">${schemaInfo.type.code}</td>
				                                         	<td class="col-lg-2">
				                                         		<fmt:formatDate value="${schemaInfo.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
				                                         	</td>
				                                         	<td class="col-lg-2 actions">
			                                                	<button onclick="initUpdateOper(${schemaInfo.id});" type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#schemaFormModal">
			                                                   		<i class="fa fa-pencil"></i> 编辑
			                                                   	</button>
			                                                   	
			                                                	<button onclick="initDelOper(${schemaInfo.id});" type="button" class="btn btn-sm btn-danger">
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
        </div>
    </div>

	<!-- MODAL SECTION -->
	<div class="row">
		<div class="col-lg-12">
			<div class="modal fade" id="schemaFormModal" tabindex="-1" role="dialog" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title">数据结构信息</h4>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-lg-12">
									<form id="custSchemaForm" role="form" class="form-horizontal">
										<input id="operTypeId" type="hidden" value="">
										<input id="schemaId" name="schemaId" type="hidden" value="">
										<input id="docId" name="docId" type="hidden" value="${param.docId}">
										<div class="form-group">
											<label class="control-label col-lg-3">编码</label> 
											<div class="col-lg-6">
												<input name="code" class="form-control" />
											</div>
										</div>
										
										<!-- <div class="form-group">
											<label class="control-label col-lg-3">名称</label> 
											<div class="col-lg-6">
												<input name="name" class="form-control" />
											</div>
										</div> -->
										
										<div class="form-group">
											<label class="control-label col-lg-3">描述</label> 
											<div class="col-lg-6">
												<textarea name="description" class="form-control"></textarea>
											</div>
										</div>
										
					                	<div class="form-group">
					                		<label class="control-label col-lg-3">类型</label>
					                		<div class="col-lg-6">
												<select id="schemaTypeSelect" name="type" class="form-control">
					                				<option value="sys_string">string</option>
					                				<option value="sys_array">array</option>
													<option value="sys_object">object</option>
													<option value="sys_boolean">boolean</option>
													<option value="sys_integer_int32">int</option>
													<option value="sys_integer_int64">long</option>
													<option value="sys_number_float">float</option>
													<option value="sys_number_double">double</option>
													<option value="sys_number_decimal">decimal</option>
													<option value="sys_ref">ref</option>
				                                </select>
											</div>
					                	</div>
					                	
					                	<div id="refSchemaSelectId" class="form-group">
					                		<label class="control-label col-lg-3">已有结构</label>
					                		<div class="col-lg-6">
												<select id="refSchemaSelect" name="refSchemaId" class="form-control">
													<c:if test="${not empty refSchemaList}">
														<c:forEach items="${refSchemaList}" var="refSchemaInfo" varStatus="status">
															<option value="${refSchemaInfo.code}">${refSchemaInfo.name}</option>						
														</c:forEach>
													</c:if>
				                                </select>
											</div>
					                	</div>
									</form>
								</div>
							</div>
							
							<div class="row">
								<div class="col-lg-12">
									<table id="custSchemaTable" class="tree table table-hover">
										<thead>
											<tr>
												<!-- <th class="col-xs-1">#</th> -->
		                                    	<th class="col-xs-3">编码</th>
		                                        <th class="col-xs-2">描述</th>
		                                        <th class="col-xs-2">类型</th>
		                                        <th class="col-xs-3">引用</th>
		                                    </tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="row">
								<div class="col-xs-3 text-left">
									<button id="addCustSchemaRootNodeBtn" type="button" class="btn btn-warning ">新增根节点</button>
								</div>
								<div class="col-xs-9 text-right">
									<button id="saveSchemaBtn" type="button" class="btn btn-success">保存</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END MODAL SECTION -->
	
	<script id="custSchemaTmpl" type="text/html">
		<tr>
			<td>
				<input name="code" class="form-control" style="display:inline;width:50%;"/>
			</td>
			<td>
				<input name="description" class="form-control" />
			</td>
			<td>
				<select name="type" class="form-control cust-schema-type">
            		<option value="sys_string">string</option>
            		<option value="sys_array">array</option>
					<option value="sys_object">object</option>
					<option value="sys_boolean">boolean</option>
					<option value="sys_integer_int32">int</option>
					<option value="sys_integer_int64">long</option>
					<option value="sys_number_float">float</option>
					<option value="sys_number_double">double</option>
					<option value="sys_number_decimal">decimal</option>
					<option value="sys_ref">ref</option>
            	</select>
			</td>						
			<td>
				<select name="refSchemaId" class="form-control cust-ref-schema">
					<c:if test="${not empty refSchemaList}">
						<c:forEach items="${refSchemaList}" var="refSchemaInfo" varStatus="status">
							<option value="${refSchemaInfo.code}">${refSchemaInfo.name}</option>						
						</c:forEach>
					</c:if>
		        </select>
			</td>		
    	</tr>
    </script>
    
    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="plugin/context-menu/ext/js/jquery.contextmenu.js"></script>
	<script type="text/javascript" src="plugin/treegrid/ext/js/jquery.treegridExt.js?version=1.0.0"></script>
    <script type="text/javascript" src="js/apidoc/schemaList.js?version=1.0.9"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
