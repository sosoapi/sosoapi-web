<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Swagger UI</title>
	<link rel="icon" type="image/png" href="${Cfg.WEB_BASE_URL}swagger/images/favicon-32x32.png" sizes="32x32" />
	<link rel="icon" type="image/png" href="${Cfg.WEB_BASE_URL}swagger/images/favicon-16x16.png" sizes="16x16" />
	<link href='${Cfg.WEB_BASE_URL}swagger/css/typography.css' media='screen' rel='stylesheet' type='text/css' />
	<link href='${Cfg.WEB_BASE_URL}swagger/css/reset.css' media='screen' rel='stylesheet' type='text/css' />
	<link href='${Cfg.WEB_BASE_URL}swagger/css/screen.css' media='screen' rel='stylesheet' type='text/css' />
	<link href='${Cfg.WEB_BASE_URL}swagger/css/reset.css' media='print' rel='stylesheet' type='text/css' />
	<link href='${Cfg.WEB_BASE_URL}swagger/css/print.css' media='print' rel='stylesheet' type='text/css' />

	<script src='${Cfg.WEB_BASE_URL}swagger/lib/jquery-1.8.0.min.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/jquery.slideto.min.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/jquery.wiggle.min.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/jquery.ba-bbq.min.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/handlebars-2.0.0.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/underscore-min.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/backbone-min.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/swagger-ui-ext.js?version=1.0.6' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/highlight.7.3.pack.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/marked.js' type='text/javascript'></script>
	<script src='${Cfg.WEB_BASE_URL}swagger/lib/swagger-oauth.js' type='text/javascript'></script>

	<!-- Some basic translations -->
	<!-- <script src='${Cfg.WEB_BASE_URL}swagger/lang/translator.js' type='text/javascript'></script> -->
	<!-- <script src='${Cfg.WEB_BASE_URL}swagger/lang/ru.js' type='text/javascript'></script> -->
	<!-- <script src='${Cfg.WEB_BASE_URL}swagger/lang/en.js' type='text/javascript'></script> -->

	<script type="text/javascript">
		$(function() {
			var url = window.location.search.match(/url=([^&]+)/);
			if (url && url.length > 1) {
				url = decodeURIComponent(url[1]);
			} 
			else {
				/* url = "http://petstore.swagger.io/v2/swagger.json"; */
				url = $("#docUrl").val();
			}
	
			// Pre load translate...
			if (window.SwaggerTranslator) {
				window.SwaggerTranslator.translate();
			}
			window.swaggerUi = new SwaggerUi(
					{
						url : url,
						validatorUrl : null,
						dom_id : "swagger-ui-container",
						supportedSubmitMethods : [ 'get', 'post', 'put', 'delete','patch' ],
						//字符集设置
						//en:英文
						//zh_CN:中文(简体)
						locale : "en",
						onComplete : function(swaggerApi, swaggerUi) {
							if (typeof initOAuth == "function") {
								initOAuth({
									clientId : "your-client-id",
									clientSecret : "your-client-secret",
									realm : "your-realms",
									appName : "your-app-name",
									scopeSeparator : ","
								});
							}
	
							if (window.SwaggerTranslator) {
								window.SwaggerTranslator.translate();
							}
	
							$('pre code').each(function(i, e) {
								hljs.highlightBlock(e)
							});
	
							addApiKeyAuthorization();
						},
						onFailure : function(data) {
							log("Unable to Load SwaggerUI");
						},
						docExpansion : "none",
						//模块排序
						apisSorter : "sortWeight",
						//模块内部方法排序
						operationsSorter : "sortWeight",
						//方法响应排序
						operationResponsesSorter : "sortWeight",
						showRequestHeaders : false
					});
	
			function addApiKeyAuthorization() {
				var key = encodeURIComponent($('#input_apiKey')[0].value);
				if (key && key.trim() != "") {
					var apiKeyAuth = new SwaggerClient.ApiKeyAuthorization(
							"api_key", key, "query");
					window.swaggerUi.api.clientAuthorizations.add("api_key",
							apiKeyAuth);
					log("added key " + key);
				}
			}
	
			$('#input_apiKey').change(addApiKeyAuthorization);
	
			// if you have an apiKey you would like to pre-populate on the page for demonstration purposes...
			/*
			  var apiKey = "myApiKeyXXXX123456789";
			  $('#input_apiKey').val(apiKey);
			 */
	
			window.swaggerUi.load();
	
			function log() {
				if ('console' in window) {
					console.log.apply(console, arguments);
				}
			}
			
			//国际化
			$("#language").change(function(){
				window.swaggerUi.setLocale($("#language").val());
				window.swaggerUi.load();
			});
		});
	</script>
</head>

<body class="swagger-section">
	<input type="hidden" id="docUrl" value="${docUrl}">
	<div id='header'>
		<div class="swagger-ui-wrap" style="max-width:1000px;">
			<a id="logo" href="http://swagger.io">swagger</a>
			<form id='api_selector'>
				<div class='input'>
					<input placeholder="http://example.com/api" id="input_baseUrl" name="baseUrl" type="text" />
				</div>
				<div class='input'>
					<input placeholder="api_key" id="input_apiKey" name="apiKey" type="text" />
				</div>
				<div class='input'>
					<a id="explore" href="#" data-sw-translate>Explore</a>
				</div>
				
				<div class='input'>
					<select id="language">
						<option value="en">English</option>
						<option value="zh_CN">中文(简体)</option>
					</select>
				</div>
			</form>
		</div>
	</div>

	<div id="message-bar" class="swagger-ui-wrap" data-sw-translate>&nbsp;</div>
	<div id="swagger-ui-container" class="swagger-ui-wrap"></div>
</body>
</html>
