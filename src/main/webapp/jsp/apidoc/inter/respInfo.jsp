<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default margin-top">
	<div class="panel-heading">
		<div class="text-muted bootstrap-admin-box-title">请求响应</div>
		<div class="btn-group pull-right">
     		<a href="javascript:void(0);" data-toggle="popover" data-placement="top" data-content="排序权重值越小排的越前面。建议权重值间隔50，方便后续调整。" class="text-muted">
            	<i class="fa fa-info-circle"></i> 排序说明
            </a>
 		</div>
	</div>
								
	<div class="bootstrap-admin-panel-content">
		<!-- TABLE SECTION -->
		<div class="row">
			<div class="col-lg-12">
				<table id="respInfoTable" class="table table-hover table-bordered">
					<thead>
                    	<tr>
                        	<th class="col-lg-1">#</th>
                            <th class="col-lg-2">编码</th>
                            <th class="col-lg-3">描述</th>
                            <th class="col-lg-2">类型</th>
                            <th class="col-lg-2">排序比重</th>
                            <th class="col-lg-2">操作</th>
                        </tr>
                     </thead>
                     
                     <tbody>
              		</tbody>
              	</table>
           	</div>
       	</div>
                    
		<div class="row">
			<div class="col-md-6" style="margin-top: 20px;">
				<button id="addRespBtn" type="button" class="btn btn-warning" data-toggle="modal" data-target="#respSchemaFormModal" data-form="respCustSchemaForm">
					<i class="fa fa-plus"></i> 新增
				</button>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="modal fade" id="respSchemaFormModal" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">请求响应信息</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="respCustSchemaForm" role="form" class="form-horizontal">
									<input id="operTypeId" type="hidden" value="">
									<input id="respId" name="respId" type="hidden" value="">
									<input name="docId" type="hidden" value="${param.docId}">
									<input name="interId" type="hidden" value="${param.interId}">
									
									<div class="form-group">
										<label class="control-label col-lg-3">编码</label> 
										<div class="col-lg-6" data-toggle="popover" data-content="若为默认响应信息则编码为'default'">
											<input name="code" class="form-control" />
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-lg-3">排序权重</label> 
										<div class="col-lg-6">
											<input name="sortWeight" value="0" class="form-control" />
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-lg-3">描述</label> 
										<div class="col-lg-6">
											<input name="description" class="form-control" />
										</div>
									</div>
				                	
				                	<!-- <div class="form-group">
				                		<label class="control-label col-lg-3">是否默认</label>
				                		<div class="col-lg-6">
											<select name="def" class="form-control">
												<option value="false">否</option>
												<option value="true">是</option>
				                            </select>
										</div>
				                	</div> -->
				                	
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
												<option value="cust_json">自定义</option>
				                          	</select>
										</div>
				                	</div>
					                	
				                	<div id="refSchemaDiv" class="form-group">
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
				                	
				                	<div id="respExtSchemaDiv" class="form-group">
				                		<label class="control-label col-lg-3">自定义结构</label>
				                		<div class="col-lg-6">
											<textarea id="respExtSchemaArea" style="resize: auto;height: auto;" name="extSchema" class="form-control" rows="10">
											</textarea>
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
								<button id="respFormatSchemaBtn" type="button" class="btn btn-warning">格式化</button>
								<button id="addCustSchemaRootNodeBtn" type="button" class="btn btn-warning">新增根节点</button>
							</div>
							<div class="col-xs-9 text-right">
								<button id="saveBtn" type="button" class="btn btn-success">保存</button>
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

<script id="respInfoTmpl" type="text/html">  
	<tr>
    	<td></td>
		<td>
			<input name="code" type="text" value="" class="form-control" readonly>
		</td>
    	<td>
			<input name="description" type="text" value="" class="form-control" readonly>
		</td>
		<td>
			<select name="type" class="form-control" disabled>
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
				<option value="cust_json">自定义</option>
           	</select>
		</td>	
        <td>
			<input name="sortWeight" type="text" value="" class="form-control" readonly>
		</td>
    	<td class="actions">
			<button type="button" class="btn btn-sm btn-primary oper-update" data-toggle="modal" data-target="#respSchemaFormModal">
            	<i class="fa fa-pencil"></i> 编辑
			</button>

			<button type="button" class="btn btn-sm btn-danger oper-del">
				<i class="fa fa-trash"></i>删除
			</button>
		</td>
    </tr>
</script> 

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

