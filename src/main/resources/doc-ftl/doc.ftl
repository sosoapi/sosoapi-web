<!doctype html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title></title>
        <style type="text/css">
            #container {margin:8px;}
            .param-table {background:#E6E6E6;border:1px solid #FFF;margin:12px 0;border-collapse:collapse;}
            .param-table th {background:gray;color:#FFF;font-size:14px;font-weight:bold;text-align:center;border:1px solid #FFF;}
            .param-table td {border-collapse:collapse;border:1px solid #FFF;font-size:12px;}
            .param-table .th-name {width:140px;}
            .schema-format {width: 550px;height: 150px;resize: none;}
        </style>
    </head>
    <body>
    	<div id="top">
    		<h1>说明</h1>
    		<h3>文档标题</h3>
    		<div>${basicInfo.title}</div>
    		
    		<h3>文档版本</h3>
    		<div>${basicInfo.version}</div>
    		
    		<h3>访问主机</h3>
    		<div>${basicInfo.host}</div>
    		
    		<h3>接口基路径</h3>
    		<div>${basicInfo.basePath}</div>
    		
    		<h3>文档说明</h3>
    		<div>${basicInfo.description}</div>
    	</div>
    	
        <div id="container">
        <#if moduleMap?exists>
            <#list moduleMap?keys as key> 
            	<div class="module">
            		<h1>${key_index + 1}. ${key}</h1>
            		<#list moduleMap[key] as interInfo>
            		 	<div class="page">
            		 		<h2>${key_index + 1}.${interInfo_index + 1}. ${interInfo.name}</h2>
            		 		<h3>接口说明</h3>
            		 		<div>${interInfo.description}</div>
            		 		
            		 		<h3>请求url</h3>
            		 		<div>${interInfo.path}</div>
            		 		
            		 		<h3>请求方式</h3>
            		 		<div>${interInfo.method}</div>
            		 		
            		 		<h3>请求协议</h3>
            		 		<div>${interInfo.scheme}</div>
            		 		
            		 		<h3>请求参数</h3>
            		 		<div>
            		 			<table class="param-table">
            		 				<thead>
            		 					<th class="th-name">参数</th>
	            		 				<th class="th-name">说明</th>
	            		 				<th class="th-name">参数位置</th>
	            		 				<th class="th-name">数据类型</th>
	            		 				<th class="th-name">默认值</th>
	            		 				<th class="th-name">必输项</th>
	            		 				<th class="th-name">请求格式</th>
            		 				</thead>
            		 				<#list interInfo.paramList as param>
									<tr>
										<td>${param.code}</td>
										<td>${param.description}</td>
										<td>${param.position}</td>
										<td>${param.type}</td>
										<td>${param.defValue}</td>
										<td>${param.required!'false'}</td>
										<td>
											<textarea class="schema-format">
${param.schemaFormat}
											</textarea>
										</td>
									</tr>
									</#list>
            		 			</table>
            		 		</div>
            		 		
            		 		<h3>请求响应</h3>
            		 		<div>
            		 			<table class="param-table">
            		 				<thead>
            		 					<th class="th-name">编码</th>
	            		 				<th class="th-name">说明</th>
	            		 				<th class="th-name">响应格式</th>
            		 				</thead>
            		 				<#list interInfo.respList as resp>
									<tr>
										<td>${resp.code}</td>
										<td>${resp.description}</td>
										<td>
											<textarea class="schema-format">
${resp.schemaFormat}
											</textarea>
										</td>
									</tr>
									</#list>
            		 			</table>
            		 		</div>
            		 	</div>
            		 </#list>
            	</div>
            </#list>
        </#if>
        </div>
    </body>
</html>