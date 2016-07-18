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
                    </div>
                </div>
            
            	<div class="row">
            		<div class="col-lg-12 text-left" style="margin-bottom:20px;">
            			<button id="addRootNodeBtn" type="button" class="btn btn-warning">新增根节点</button>
            			<button id="getDataBtn" type="button" class="btn btn-success">获取表格数据</button>
            			<button id="loadDataBtn" type="button" class="btn btn-success">加载表格数据</button>
            		</div>
            	</div>
            	
            	<div class="row">
            		<div class="col-lg-12">
            			<table id="contentTable" class="tree table table-hover">
            				<thead>
                               	<tr>
                                   	<th class="col-xs-2">#</th>
                                   	<th class="col-xs-2">编码</th>
                                    <th class="col-xs-2">名称</th>
                                    <th class="col-xs-2">描述</th>
                                    <th class="col-xs-2">类型</th>
                                    <th class="col-xs-2">引用</th>
                                </tr>
                            </thead>
						</table>
            		</div>
            	</div>
            	
            	<hr>
            	
            	<div class="row">
            		<div class="col-lg-12">
            			<textarea id="dataArea" rows="10" class="form-control"></textarea>
            		</div>
            	</div>
            </div>
            <!-- END MAIN SECTION -->
        </div>
    </div>

	<script id="rowTmpl" type="text/html">  
		<tr>
			<td></td>
			<td>
				<input name="code" class="form-control" />
			</td>
			
			<td>
				<input name="name" class="form-control" />
			</td>
			
			<td>
				<input name="description" class="form-control" />
			</td>
				
			<td>
				<select name="type" class="form-control">
            		<option value="sys_string">string</option>
            		<option value="sys_array">array</option>
					<option value="sys_integer_int32">int</option>
					<option value="sys_integer_int64">long</option>
					<option value="sys_number_float">float</option>
					<option value="sys_number_double">double</option>
					<option value="sys_number_decimal">decimal</option>
					<option value="sys_object">object</option>
					<option value="sys_ref">ref</option>
            	</select>
			</td>						
			<td>
				<select name="moduleId" class="form-control">
			    	<option value="1">51002 -- 用户已注册</option>
			        <option value="2">51004 -- 用户名或密码错误</option>
		        </select>
			</td>		
    	</tr>
    </script>
    
    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
    <script type="text/javascript" src="plugin/context-menu/js/jquery.contextmenu.js"></script>
	<script type="text/javascript" src="plugin/treegrid/ext/js/jquery.treegridExt.js"></script>
	
	<script type="text/javascript" src="jsp/common/tmpl/js/treeTable.js"></script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
