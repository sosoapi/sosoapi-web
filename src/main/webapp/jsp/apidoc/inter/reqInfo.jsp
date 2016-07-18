<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row margin-top">
	<div class="col-lg-12">
		<div class="alert alert-success bootstrap-admin-alert">
        	<a class="close" data-dismiss="alert" href="#">×</a>
            <h4>注意:只有"参数位置"为"body"时，参数类型"ref"(引用)和"自定义"才可用。</h4>
       	</div>
   	</div>
</div>
     	
<div class="panel panel-default">
	<div class="panel-heading">
		<div class="text-muted bootstrap-admin-box-title">请求参数</div>
	</div>

	<div class="bootstrap-admin-panel-content">
		<!-- TABLE SECTION -->
    	<div class="row">
			<div class="col-lg-12">
				<form id="reqParamForm" class="form-horizontal">
					<table id="reqParamTable" class="table table-hover table-bordered table-responsive">
          				<thead>
			               	<tr>
			                	<th class="col-lg-1">#</th>
			                	<th class="col-lg-2">编码</th>
			                    <th class="col-lg-1 hidden">描述</th>
			                    <th class="col-lg-2">参数位置</th>
			                    <th class="col-lg-2">类型</th>
			                    <th class="col-lg-1 hidden">默认值</th>
			                    <th class="col-lg-1 hidden">必输项</th>
			                    <th class="col-lg-2">引用</th>
			                    <th class="col-lg-1">自定义</th>
			                    <th class="col-lg-1">更多</th>
			              	</tr>
               			</thead>
              
                  		<tbody>
                  		</tbody>
              		</table>
      			</form>
           	</div>
     	</div>
           
      	<div class="row">
        	<div class="col-md-6" style="margin-top: 20px;">
				<button id="addReqParamBtn" type="button" class="btn btn-warning">
					<i class="fa fa-plus"></i> 新增
				</button>
			
				<button id="saveReqParamBtn" type="button" class="btn btn-success">
					<i class="fa fa-floppy-o"></i> 保存
				</button>
			</div>
		</div>
		
		<!-- MODAL SECTION -->
		<div class="row">
			<div class="col-lg-12">
				<div class="modal fade" id="reqExtSchemaModal" tabindex="-1" role="dialog" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title">自定义结构</h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-lg-12">
										<textarea id="reqExtSchemaArea" style="resize: auto;height: auto;" name="extSchemaArea" class="form-control" rows="15"></textarea>
									</div>
								</div>
							</div>
							
							<div class="modal-footer">
								<div class="col-xs-3 text-left">
									<button id="reqFormatSchemaBtn" type="button" class="btn btn-warning">格式化</button>
								</div>
								<div class="col-xs-9 text-right">
									<button id="reqConfirmBtn" type="button" class="btn btn-success">确定</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END MODAL SECTION -->
		
		<!-- MODAL SECTION -->
		<div class="row">
			<div class="col-lg-12">
				<div class="modal fade" id="moreModal" tabindex="-1" role="dialog" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title">更多选项</h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-lg-12">
										<form id="moreForm" role="form" class="form-horizontal">
											<div class="form-group">
												<label class="control-label col-lg-3">默认值</label> 
												<div class="col-lg-6">
													<input name="defValue" type="text" value="" class="form-control">
												</div>
											</div>
											
											<div class="form-group">
												<label class="control-label col-lg-3">必输项</label> 
												<div class="col-lg-6">
													<select name="required" class="form-control">
														<option value="true">是</option>
														<option value="false">否</option>
													</select>
												</div>
											</div>
											
						                	<div class="form-group">
							                    <label class="control-label col-lg-3">描述</label>
							                    <div class="col-lg-6">
							                        <textarea name="description" class="form-control"></textarea>
							                    </div>
							                </div>
										</form>
									</div>
								</div>
							</div>
							
							<div class="modal-footer">
								<button id="saveMoreBtn" type="button" class="btn btn-success">确定</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END MODAL SECTION -->
	</div>
</div>

<script id="reqParamTmpl" type="text/html">  
	<tr>
    	<td></td>
		<td>
			<input name="code" type="text" value="" class="form-control">
		</td>
    	<td class="hidden">
			<input name="description" type="text" value="" class="form-control">
		</td>
		<td>
			<select name="position" class="form-control req-param-position">
				<option value="formData">formData</option>
				<option value="path">path</option>
				<option value="query">query</option>
				<option value="body">body</option>
				<option value="header">header</option>
				<option value="cookie">cookie</option>
			</select>
		</td>
    	<td>
			<select name="type" class="form-control req-param-type chzn-select">
				<option value="sys_string">string</option>
				<option value="sys_boolean">boolean</option>
				<option value="sys_integer_int32">int</option>
				<option value="sys_integer_int64">long</option>
				<option value="sys_number_float">float</option>
				<option value="sys_number_double">double</option>
				<option value="sys_number_decimal">decimal</option>
				<option value="sys_file">file</option>
				<option value="sys_ref">ref</option>
				<option value="cust_json">自定义</option>
			</select>
		</td>
    	<td class="hidden">
			<input name="defValue" type="text" value="" class="form-control">
		</td> 
    	<td class="hidden">
			<select name="required" class="form-control">
				<option value="true">是</option>
				<option value="false">否</option>
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
		<td>
			<input name="extSchema" type="hidden">
            <button class="btn ext-schema-btn" type="button">
            	<i class="fa fa-ellipsis-h"></i>
            </button>
		</td>
		<td>
            <button class="btn more-btn" type="button">
				更多
            </button>
		</td>
    </tr>
</script> 
