<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}" />
	<%@include file="/jsp/common/head.jsp"%>
	
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/stickup/css/stickup.css" rel="stylesheet">
	<style type="text/css">
		body {
			padding-top: 0px;
			padding-bottom: 40px;
			color: #5a5a5a;
		}

		.carousel .item {
			height: 280px;
			background-color: #0A64A4;
		}
    </style>
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body>
	<!-- START THE COVER -->
	<div id="home" class="carousel">
		<div class="carousel-inner">
			<div class="item active">
				<div class="container">
					<div class="carousel-caption">
						<h1>SosoApi</h1>
						<h3>Simple online,Simple offline</h3>
						<p>专注于API接口文档管理及线上线下测试的API服务平台</p>
					</div>
				</div>
			</div>
		</div>
		<!-- END COVER -->

		<!-- START THE NAVBAR -->
		<div class="navbar-wrapper">
			<div class="container">
				<div class="navwrapper">
					<div class="navbar navbar-inverse navbar-static-top">
						<div class="container">
							<div class="navbar-header">
								<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								</button>
								<a class="navbar-brand" href="javascript:void(0);">SosoApi</a>
							</div>
							<div class="navbar-collapse collapse">
								<ul class="nav navbar-nav">
									<li class="menuItem active" data-menu="home"><a href="#home">首页</a></li>
									<li class="menuItem" data-menu="features"><a href="#features">特色</a></li>
									<li class="menuItem" data-menu="brief"><a href="#brief">Swagger简介</a></li>
									<li class="menuItem" data-menu="contact"><a href="#contact">联系我们</a></li>
									<li class="menuItem" data-menu="faq"><a href="pass/faq/home.htm">常见问题</a></li>
									<li class="menuItem" data-menu="quickStart"><a href="pass/faq/editWizard.htm">快速上手</a></li>
									<li class="menuItem" data-menu="demo"><a href="pass/apidoc/demo.htm">Demo</a></li>
								</ul>

								<div class="navbar-right">
									<c:choose>
										<c:when test="${not empty userInfo}">
											<c:if test="${userInfo.role == 'admin'}">
												<a class="navbar-brand" href="admin/home.htm" style="margin-right: 30px;">${userInfo.nickName}</a> 
											</c:if>
											<c:if test="${userInfo.role != 'admin'}">
												<a class="navbar-brand" href="auth/home/home.htm" style="margin-right: 30px;">${userInfo.nickName}</a>												
											</c:if>
										</c:when>
										<c:otherwise>
											<a class="navbar-brand" href="forwardLogin.htm">登录</a> 
											<a class="navbar-brand" href="regist/forwardRegist.htm" style="margin-right: 30px;">注册</a>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- End Navbar -->
			</div>
		</div>
		<!-- END NAVBAR -->

		<!-- START THE CONTENT -->
		<div id='contents'>
			<!-- START THE FEATURES -->
			<div id="features" class="row featurette">
				<!-- Three columns of text -->
				<div class="row">
					<div class="col-lg-3 col-lg-offset-2">
						<img src="${Cfg.IMG_DOMAIN}image/index/word.png">
						<h2>告别word</h2>
						<p>
							<h4>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您是否已厌倦了更改一个api接口就得多人通知，文档互传，最终导致版本混乱接口调错的情况？
							自从有了sosoapi再也不用担心接口调错了~
							</h4>
						</p>
					</div>

					<div class="col-lg-3">
						<img src="${Cfg.IMG_DOMAIN}image/index/simple.png">
						<h2>快速编写</h2>
						<p>
							<h4>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;表单傻瓜式接口文档创建，只要动动鼠标，敲敲几个字，一个接口文档就ok了。
							</h4>
						</p>
					</div>

					<div class="col-lg-3">
						<img src="${Cfg.IMG_DOMAIN}image/index/opensource.png">
						<h2>线上线下测试</h2>
						<p>
							<h4>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生成基于<a href="http://swagger.io/" target="_blank">Swagger UI</a>文档格式的json，既可在线进行测试也可下载相关的json部署到自己的服务器上，既方便又快捷。
							</h4>
						<p>
					</div>
				</div>
			</div>
			<!-- END FEATURES -->

			<!-- START THE BRIEF -->
			<div id="brief" class="row featurette">
				<div class="col-lg-12">
					<h2 class="featurette-heading">Swagger UI简介</h2>
				</div>
				<div class="col-lg-offset-1 col-lg-10">
					<h4>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在开发过程中有时对一些API说明的理解比较模糊，总想着能直接验证一下自己的理解就好了，而不是需要去写测试代码来验证自己的想法，即API文档应具备直接执行能力。 
						<a href="http://swagger.io/" target="_blank">Swagger UI</a>就是这样的一个利器，一款RESTFUL接口的文档在线自动生成+功能测试功能软件，功能强大，UI界面漂亮，并且支持在线测试等等其实。
						Swagger本身的目标比上面描述的要大很多：“Swagger is a specification and complete framework implementation for describing, producing, consuming, and visualizing RESTful web services. ”。
					</h4>
					<p>
						<h4>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;但是，在使用Swagger UI的过程中发现要么在工程代码中集成，通过注解来生成，要么编写相关的json/yaml文档。其中，第一种方式虽然比较简单，不过，因为要在代码层级做嵌入，
						编写注解，侵入性比较大，而且依赖于项目。第二种方式虽然比较独立，不过文档编辑比较麻烦，而且多人分工比较不方便。基于上述原因，开发了SosoApi平台，通过简便的表单方式生成对应的Swagger UI相关的格式文档，
						解决使用Swagger UI的后顾之忧。
						</h4>
					</p>
					
				</div>
				<div class="col-lg-offset-1 col-lg-10">
					<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/index/swagger-demo-full.png">
					<p class="text-center">&nbsp;</p>
				</div>
				
				<div class="col-lg-offset-1 col-lg-10">
					<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/index/swagger-demo-one.png">
				</div>
			</div>
			<!-- END BRIEF -->

			<!-- START THE FOOTER -->
			<div id="contact" class="row featurette">
				<div class="col-12 col-lg-12" style="margin-top:100px;">
					<div class="col-lg-offset-4 col-lg-2 text-center">
						<a target="_blank" href="http://jq.qq.com/?_wv=1027&k=dPKj9Q">
							<img src="${Cfg.IMG_DOMAIN}image/index/qq.png">
						</a>
						<h3>QQ</h3>
					</div>
					
					<div class="col-lg-2 text-center">
						<a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=hLe2sb20s7eyt7zE9fWq5_vp" style="text-decoration:none;">
							<img src="${Cfg.IMG_DOMAIN}image/index/mail.jpg" style="height:60px;">
						</a>
						<h3>E-Mail</h3>
					</div>
				</div>
			</div>
			<!-- END FOOTER -->
		</div>
		<!-- END CONTENT -->
	</div>

	<!-- PAGE LEVEL SCRIPTS -->
	<jsp:include page="/jsp/common/footer.jsp"/>
	<script type="text/javascript" src="plugin/stickup/js/stickUp.js"></script>
	<script type="text/javascript">
		jQuery(function($) {
			$(function() {
				$('.navbar-wrapper').stickUp({
					itemClass : 'menuItem',
					itemHover : 'active',
					topMargin : 'auto'
				});
			});
		});
	</script>
	
	<!-- baidu统计 -->
	<script>
		var _hmt = _hmt || [];
		(function() {
		  var hm = document.createElement("script");
		  hm.src = "//hm.baidu.com/hm.js?b6d830db139defa52e47fb1dc0018c06";
		  var s = document.getElementsByTagName("script")[0]; 
		  s.parentNode.insertBefore(hm, s);
		})();
	</script>
</body>
</html>
