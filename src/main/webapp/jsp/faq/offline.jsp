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
		<jsp:param name="menuFaq" value="true"/>
	</jsp:include>
    <!-- END TOP SECTION -->

    <div class="container cust-container">
		<div class="row">
            <!-- LEFT SECTION -->
            <jsp:include page="/jsp/faq/menu.jsp">
				<jsp:param name="menuOffline" value="true"/>
			</jsp:include>
			<!-- END LEFT SECTION -->
				
            <!-- MAIN SECTION -->
            <div class="col-md-10">
    	        <div class="row">
        	        <div class="col-lg-10">
                        <div class="panel-group">
	                        <div class="panel panel-default">
	                            <div class="panel-heading">
	                                <h4 class="panel-title">
	                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" aria-expanded="false" class="collapsed">
	                                    	1、如何进行线下部署？
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse1" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
	                                <div class="panel-body">
	                                	<p><a href="${Cfg.OFFLINE_HELP_URL}">线下部署说明文档</a></p>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <div class="panel panel-default">
	                            <div class="panel-heading">
	                                <h4 class="panel-title">
	                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse2" aria-expanded="false" class="collapsed">
	                                    	2、如何处理SwaggerUI界面国际化？
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse2" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
	                                <div class="panel-body">
	                                	<p>
	                                		目前SwaggerUI国际化仅支持英文和中文，部署后编辑{SwaggerUI_HOME}/index.html，设置SwaggerUi的locale即可。
<pre>
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
	apisSorter : "alpha",
	showRequestHeaders : false
});
</pre>
	                                	</p>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
                    </div>
                </div>
            </div>
            <!-- END MAIN SECTION -->
        </div>
    </div>

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <!-- END FOOTER SECTION -->
</body>
</html>
