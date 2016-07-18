<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row margin-top">
	<div class="col-lg-12">
		<form id="basicInfoFrom" class="form-horizontal">
			<div class="form-group">
				<label class="control-label col-lg-2">所属模块</label>
							
				<div class="col-lg-6">
					<select id="moduleSelectId" class="form-control" name="moduleId">
					</select>
				</div>
			</div>
			
			<div class="form-group">
              	<label class="control-label col-lg-2">排序权重</label>

               	<div class="col-lg-6">
                	<input type="text" value="0" class="form-control" name="sortWeight">
               	</div>
           	</div>
														           	              
            <div class="form-group">
              	<label class="control-label col-lg-2">接口名称</label>

               	<div class="col-lg-6">
                	<input type="text" class="form-control" name="name">
               	</div>
           	</div>
							     
 			<!-- <div class="form-group">
                <label class="control-label col-lg-2">概要信息</label>

                <div class="col-lg-6">
                    <input type="text" class="form-control" name="summary">
                </div>
            </div> -->
							                           
            <div class="form-group">
                <label class="control-label col-lg-2">请求url</label>

                <div class="col-lg-6">
                    <input type="text" class="form-control" name="path">
                </div>
            </div>
						                    
           	<div class="form-group">
            	<label class="control-label col-lg-2">请求方式</label>

                <div class="col-lg-6">
               		<select class="form-control" name="method">
						<option value="GET">GET</option>
						<option value="POST">POST</option>
						<option value="PUT">PUT</option>
						<option value="DELETE">DELETE</option>
					</select>
                </div>
            </div>
							                
            <div class="form-group">
                <label class="control-label col-lg-2">请求协议</label>

                <div class="col-lg-6">
                    <select class="form-control" name="scheme">
						<option value="HTTP">HTTP</option>
						<option value="HTTPS">HTTPS</option>
					</select>
                </div>
            </div>
							                
            <div class="form-group">
                <label class="control-label col-lg-2">请求格式</label>

                <div class="col-lg-6">
                    <select class="form-control" name="consume">
						<option value="application/json">application/json</option>
						<!-- <option value="application/xml">application/xml</option> -->
						<option value="application/x-www-form-urlencoded">application/x-www-form-urlencoded</option>
						<option value="multipart/form-data">multipart/form-data</option>
					</select>
                </div>
            </div>
							                
            <div class="form-group">
                <label class="control-label col-lg-2">响应格式</label>

                <div class="col-lg-6">
                    <select class="form-control" name="produce">
						<option value="application/json">application/json</option>
						<!-- <option value="application/xml">application/xml</option> -->
					</select>
                </div>
            </div>
							                
            <div class="form-group">
                <label class="control-label col-lg-2">是否弃用</label>

                <div class="col-lg-6">
                    <select class="form-control" name="deprecated">
					<option value="true">是</option>
					<option value="false">否</option>
				</select>
                </div>
            </div>
							                
            <div class="form-group">
                <label class="control-label col-lg-2">描述信息</label>

                <div class="col-lg-6">
                    <textarea class="form-control" name="description" rows="10">${interInfo.description}</textarea>
                </div>
            </div>
							                
            <div class="form-group">
                <div class="col-lg-1 col-lg-offset-2">
                    <button id="saveBasicInfoBtn" class="btn btn-success btn-lg" type="button">保存</button>
                </div>
            </div>
		</form>
	</div>
</div>
