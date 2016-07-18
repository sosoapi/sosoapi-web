<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title></title>
		
	<!-- PAGE LEVEL STYLES -->
	<link rel="stylesheet" href="plugin/steps/css/normalize.css" />
	<link rel="stylesheet" href="plugin/steps/css/wizardMain.css" />
	<link rel="stylesheet" href="plugin/steps/css/jquery.steps.css" />
	<style type="text/css">
		body {
			font-size: 14px;
			line-height: 1.42857143;
		}
		
		.wizard > .content{
			min-height: 75em;
		}
		
		.wizard > .content > .body {
			float: left;
			position: absolute;
			width: 100%;
			height: 100%;
		}

		.wizard.vertical > .steps {
			display: inline;
			float: left;
			width: 20%;
			line-height: 1;
		}
		
		.wizard.vertical > .content {
			display: inline;
			float: left;
			width: 75%;
			margin: 0 2.5% 0 2.5%;
		}
		
		.wizard, .tabcontrol {
			display: block;
			width: 100%;
		}

		.wizard a{
			background: #eee;
			color: #aaa;
			cursor: default;
		}
    </style>
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="bootstrap-admin-with-small-navbar">
    <!-- TOP SECTION -->
    <jsp:include page="/jsp/common/top.jsp">
		<jsp:param name="menuHome" value="true"/>
	</jsp:include>
    <!-- END TOP SECTION -->

    <div class="container cust-container">
		<div class="row">
            <!-- MAIN SECTION -->
            <div class="col-md-*">
            	<div class="row">
                	<div class="col-lg-12">
                		<div class="alert alert-success bootstrap-admin-alert">
                            <a class="close" data-dismiss="alert" href="#">×</a>
                            <h4>线下部署说明文档(可左右键切换步骤)</h4>
                        </div>
                	</div>
                </div>
                
    	        <div class="row">
        	        <div class="col-lg-12">
                        <div id="deployWizard">
						    <h4>下载API json文档</h4>
						    <section>
						    	<div class="row">
									<div class="col-lg-12">
										<dl>
											<dt>步骤说明</dt>
											<dd>
												<ol>
													<li>下载在线编辑后的API json文档，假设文档名称为"demo.json"</li>
												</ol>
											</dd>
											
											<dt>注意事项</dt>
											<dd>
												<ol>
													<li>文件名不能包含空格,"-"等特殊字符</li>
												</ol>
											</dd>
										</dl>
						        	</div>
						        </div>
						        
						    	<div class="row">
									<div class="col-lg-12">
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/deploy/1.png">
						        	</div>
						        </div>
						    </section>
						    
						    <h4>下载Swagger UI扩展版</h4>
						    <section>
						    	<div class="row">
									<div class="col-lg-12">
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/deploy/2.png">
						        	</div>
						        </div>
						    </section>
						    
						    <h4>部署SwaggerUI</h4>
						    <section>
						        <div class="row">
									<div class="col-lg-12">
										<dl>
											<dt>步骤说明</dt>
											<dd>
												<ol>
													<li>以web服务器tomcat7为例，其他服务器按部署静态站点方式部署即可。</li>
													<li>将SwaggerUI扩展版解压到{TOMCAT_HOME}/webapps目录下，
														下载的API json文档放到{TOMCAT_HOME}/webapps/SwaggerUI/json/目录下。
													</li>
													<li>启动tomcat</li>
												</ol>
											</dd>
										</dl>
						        	</div>
						        </div>
						        
						    	<div class="row">
									<div class="col-lg-12">
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/deploy/3.png">
						        		<p class="text-center">&nbsp;</p>
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/deploy/4.png">
						        	</div>
						        </div>
						    </section>
						    
						    <h4>访问SwaggerUI</h4>
						    <section>
						        <div class="row">
									<div class="col-lg-12">
										<dl>
											<dt>步骤说明</dt>
											<dd>
												<ol>
													<li>
														假设tomcat端口为8080
													</li>
													<li>直接在浏览器中输入"http://localhost:8080/SwaggerUI"，即可出现如下界面</li>
												</ol>
											</dd>
										</dl>
						        	</div>
						        </div>
						        
						    	<div class="row">
									<div class="col-lg-12">
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/deploy/5.png">
						        	</div>
						        </div>
						    </section>
						    
						    <h4>访问API文档并测试</h4>
						    <section>
						        <div class="row">
									<div class="col-lg-12">
										<dl>
											<dt>步骤说明</dt>
											<dd>
												<ol>
													<li>
														在SwaggerUI界面输入框中将“http://petstore.swagger.io/v2/swagger.json”
														替换为"http://localhost:8080/SwaggerUI/json/demo.json"
													</li>
													<li>
														点击"explore"即可以看到对应的API文档
													</li>
												</ol>
											</dd>
											
											<dt>注意事项</dt>
											<dd>
												<ol>
													<li>如果出现网站预览和本地预览效果不一致，可能是SwaggerUI版本已升级，需重新下载。</li>
													<li>
														<p>若测试时，"Response Headers"返回如下信息，则应该是ajax跨域请求问题。可参考"常见问题->线下部署"中的跨域请求相关资料后处理。 </p>
														<pre>
{
  "error": "no response from server"
}
														</pre>
													</li>
												</ol>
											</dd>
										</dl>
						        	</div>
						        </div>
						        
						    	<div class="row">
									<div class="col-lg-12">
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/deploy/6.png">
						        	</div>
						        </div>
						    </section>
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
    
    <!-- PAGE LEVEL SCRIPTS -->
	<script type="text/javascript" src="plugin/steps/js/jquery.steps.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#deployWizard").steps({
		        headerTag: "h4",
		        bodyTag: "section",
		        transitionEffect: "slideLeft",
		        stepsOrientation: "vertical",
		        enableAllSteps: true,
		        enablePagination: false,
		        autoFocus: true,
		        labels: {
		            cancel: "Cancel",
		            current: "current step:",
		            pagination: "Pagination",
		            finish: "结束",
		            next: "下一步",
		            previous: "上一步",
		            loading: "Loading ..."
		        }
		    });
		});
	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
