<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}" />
	<%@include file="/jsp/common/head.jsp"%>

	<style type="text/css">
		body{font-family:"Helvetica Neue",Helvetica,Arial,"Hiragino Sans GB","Hiragino Sans GB W3","WenQuanYi Micro Hei",sans-serif}h1,.h1,h2,.h2,h3,.h3,h4,.h4,.lead{font-family:"Helvetica Neue",Helvetica,Arial,"Hiragino Sans GB","Hiragino Sans GB W3","Microsoft YaHei UI","Microsoft YaHei","WenQuanYi Micro Hei",sans-serif}body{padding-top:30px}@media (min-width:768px){.navbar{min-height:40px}.navbar-nav>li>a{font-size:14px;padding-top:11px;padding-bottom:11px}.navbar-brand{padding-top:0;padding-bottom:0;line-height:42px;height:42px}}.jumbotron{position:relative;padding:40px 0;color:#fff;text-align:center;text-shadow:0 1px 3px rgba(0,0,0,.4),0 0 30px rgba(0,0,0,.075);background:#020031;background:-moz-linear-gradient(45deg,#020031 0,#6d3353 100%);background:-webkit-gradient(linear,left bottom,right top,color-stop(0%,#020031),color-stop(100%,#6d3353));background:-webkit-linear-gradient(45deg,#020031 0,#6d3353 100%);background:-o-linear-gradient(45deg,#020031 0,#6d3353 100%);background:-ms-linear-gradient(45deg,#020031 0,#6d3353 100%);background:linear-gradient(45deg,#3F7B6D 0,#174344 100%);filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#020031', endColorstr='#6d3353', GradientType=1);-webkit-box-shadow:inset 0 3px 7px rgba(0,0,0,.2),inset 0 -3px 7px rgba(0,0,0,.2);-moz-box-shadow:inset 0 3px 7px rgba(0,0,0,.2),inset 0 -3px 7px rgba(0,0,0,.2);box-shadow:inset 0 3px 7px rgba(0,0,0,.2),inset 0 -3px 7px rgba(0,0,0,.2)}.jumbotron a{color:#fff;color:rgba(255,255,255,.5);-webkit-transition:all .2s ease-in-out;-moz-transition:all .2s ease-in-out;transition:all .2s ease-in-out}.jumbotron aa:hover{color:#fff;text-shadow:0 0 10px rgba(255,255,255,.25)}.jumbotron .container{position:relative;z-index:2}.jumbotron:after{content:'';display:block;position:absolute;top:0;right:0;bottom:0;left:0;background:url(../img/bs-docs-masthead-pattern.png) repeat center center;opacity:.4}@media only screen and (-webkit-min-device-pixel-ratio:2),only screen and (min--moz-device-pixel-ratio:2),only screen and (-o-min-device-pixel-ratio:2/1){.jumbotron:after{background-size:150px 150px}}.masthead{padding:60px 0 80px;margin-bottom:0;color:#fff}@media screen and (min-width:768px){.masthead{padding:90px 0 110px}}.masthead h1{font-size:60px;line-height:1;letter-spacing:-2px;font-weight:700}@media screen and (min-width:768px){.masthead h1{font-size:90px}}@media screen and (min-width:992px){.masthead h1{font-size:100px}}.masthead h2{font-size:18px;font-weight:200;line-height:1.25}@media screen and (min-width:768px){.masthead h2{font-size:24px}}@media screen and (min-width:992px){.masthead h2{font-size:30px}}.masthead p{font-size:40px;font-weight:200;line-height:1.25}.masthead .masthead-button-links{margin-top:30px}.masthead-links{margin:0;padding:0;list-style:none}.masthead-links li{display:inline;padding:0 10px;color:rgba(255,255,255,.25)}.masthead-links li a:hover{color:#fff}.subhead{text-align:center;border-bottom:1px solid #ddd}@media screen and (min-width:768px){.subhead{text-align:left}}.subhead h1{font-size:60px}.subhead p{margin-bottom:20px}@media screen and (min-width:768px){.subhead p{text-align:left}}.btn-primary.btn-shadow{-webkit-box-shadow:inset 0 -4px 0 #2a6496;box-shadow:inset 0 -4px 0 #2a6496;border:0;color:#fff}.btn-lg.btn-shadow{padding:13px 35px 17px}.bc-social{padding:15px 0;text-align:center;background-color:#f5f5f5;border-top:1px solid #fff;border-bottom:1px solid #ddd}.bc-social-buttons{margin-left:0;margin-bottom:0;padding-left:0;list-style:none}.bc-social-buttons li{display:inline-block;line-height:1;color:#555}.bc-social-buttons li .fa{font-size:18px;margin-right:3px}.bc-social-buttons li .fa-weibo{font-size:20px}.bc-social-buttons li a{color:#555}.bc-social-buttons li.social-qq:hover{color:#428bca}.bc-social-buttons li.social-weibo a:hover{color:#d9534f}.bc-social-buttons>li+li:before{padding:0 10px;color:#ccc;content:"|"}.projects .thumbnail{display:block;margin-left:auto;margin-right:auto;text-align:center;max-width:310px;margin-bottom:30px;border-radius:0}.projects .thumbnail .caption{height:200px;overflow-y:hidden;color:#555}.projects .thumbnail .caption a:hover,.projects .thumbnail .caption a:focus{text-decoration:none}.projects .thumbnail img{max-width:100%;height:auto}.projects-header{width:60%;text-align:center;margin:60px 0 10px;font-weight:200;margin-bottom:40px;display:block;margin-left:auto;margin-right:auto}.projects-header h2{font-size:30px;letter-spacing:-1px}@media screen and (min-width:768px){.projects-header h2{font-size:42px}}.nav-sub{padding-top:10px;padding-bottom:10px;margin-top:70px;border-top:1px solid #eee}.footer{color:#777;padding:30px 0;border-top:1px solid #e5e5e5;margin-top:70px}.footer a{color:#777}.footer-top .about>div{height:110px;margin-bottom:10px}.footer-top .about>div h4{color:#563d7c;font-size:16px}.footer-bottom{font-size:13px}.footer-bottom ul>li{padding:0}.footer-bottom ul>li+li:before{padding:0 10px;color:#ccc;content:"|"}#scrollUp{background-color:#777;color:#eee;font-size:40px;line-height:1;text-align:center;text-decoration:none;bottom:20px;right:20px;overflow:hidden;width:46px;height:46px;border:none;opacity:.8}#scrollUp:hover{background-color:#333}@media screen and (min-width:992px){#scrollUp{bottom:100px}}.bc-sidebar{margin-top:30px}.bc-sidebar>ul>li>a{display:block;margin:0 0 -1px;padding:8px 14px;border:1px solid #e5e5e5}.excerpt-list{margin-top:60px}.excerpt{min-height:120px;border:1px solid #eee;position:relative;margin-bottom:10px;padding:20px 20px 20px 24px}.excerpt-title{font-size:24px;margin-top:0}.excerpt-title a{color:#555}.excerpt-title a:hover,.excerpt-title a:active{color:#3071a9}.excerpt-meta{position:absolute;bottom:12px}.excerpt-tags{color:#777}.excerpt-tags .glyphicon{position:relative;top:2px;color:#eee}.excerpt-tags a,.excerpt-tags span{color:#777;font-size:12px}.post{position:relative;margin-top:60px;max-width:680px;display:block;margin-left:auto;margin-right:auto}.post-header h1,.post-header h2{font-size:32px;margin:0 0 45px 0;position:relative;text-align:center}@media (min-width:768px){.post-header h1,.post-header h2{font-size:36px}}.post-header h1:after,.post-header h2:after{border-top:1px solid #e5e5e5;bottom:0;content:"";left:50%;margin:0 0 0 -30%;position:absolute;width:60%}.post-header h1 a,.post-header h2 a{color:#363636;display:block;padding:65px 0 20px;position:relative}.post-header h1 a:hover,.post-header h2 a:hover{color:#428bca}.post-header h1 a:before,.post-header h2 a:before{border-top:1px solid #e5e5e5;bottom:-4px;content:"";left:50%;margin:0 0 0 -27%;position:absolute;width:60%}.post-header h1 a:after,.post-header h2 a:after{border-top:1px solid #e5e5e5;bottom:-3px;content:"";left:50%;margin:0 0 0 -28%;position:absolute;width:60%}@media (min-width:768px){.post-header h1 a,.post-header h2 a{padding-left:65px;padding-right:65px}}.post-content{font-size:16px;line-height:1.8;padding-top:20px;padding-bottom:20px}.post-content p,.post-content pre,.post-content ul,.post-content ol,.post-content dl,.post-content form,.post-content hr,.post-content table,.post-content blockquote{margin-bottom:1.8em}.post-content blockquote{font-size:16px}.post-content pre{margin-top:-20px}.post-content li>p{margin-bottom:5px}.post-content img,.post-content video,.post-content embed,.post-content iframe{max-width:100%}.post-content img{height:auto}article.page{margin-top:0;max-width:none}article.page .post-content{padding-top:0}article.page .post-content h2{font-size:36px;padding-bottom:9px;margin:40px 0 20px;border-bottom:1px solid #eee}#btn-jike-video:after{content:" ";color:#fff;position:absolute;top:1px;right:0;padding:3px 3px 3px 3px;z-index:9999999;background:#d9534f;border-radius:50%;font-size:12px;line-height:1;border:1px solid #d43f3a}
	  
	  	.btn-fast{
	    	background:rgb(92,182,92)!important;
	    	color:#fff!important;
	  	}
	  	.btn-fast:hover{
	    	background:rgb(140, 214, 140)!important;
	  	}
	  	.banner{
	    	/*padding-top: 150px;
	    	height:650px;*/
	  	}
	  	.effect-wp{
	    	width: 1400px;
	  	}
	  	.effect{
	    	padding-top: 30px;
	    	text-align: center;
	  	}
	  	.effect h4{
	    	padding-top:10px;
	    	line-height: 150%;
	  	}
	  	.featurette-header{
	    	text-align: center;
	    	font-size: 34px;
	    	width: 33%;
	    	border-bottom: 1px solid #eee;
	    	margin:0 auto;
	    	padding-bottom: 20px;
	    	margin-bottom: 30px;
	  	}
	  	.container-brief{
	    	margin-top: 50px;
	    	padding-top: 20px;
	    	background:#f9f9f9;
	    	padding-bottom: 50px;
	  	}
	  	.container-brief img{
	    	margin:0 auto;
	 	}
	  	.brief-wp{
	    	width: 60%;
	    	margin: 0 auto;
	  	}
	  	.brief-wp h4{
	    	line-height: 150%;
	    	font-size: 14px;
	  	}
	  	.featurette-links{
	    	border-top: 1px solid #eee;
	    	background: #f9f9f9;
	  	}
	  	.links{
	    	margin-top:60px;
	    	padding-bottom: 40px;
	  	}
	  	.links h3{
	    	font-size: 16px;
	  	}
	  	.links .icon-qq{
	    	width: 106px;
	  	}
	  	.links .icon-email{
	    	width: 48px;
	    	height: 38px;
	 	}
	  	footer{
	    	border-top: 1px solid #eee;
	    	width: 60%;
	    	margin:0 auto;
	    	padding-top: 5px;
	  	}
	  	.clearfix:after{
	    	content: '';
	    	display: block;
	    	height: 0;
	    	visibility: hidden;
	    	clear: both;
	  	}
	</style>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
    	<div class="container">
        	<div class="navbar-header">
	          	<button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".navbar-collapse">
	            	<span class="sr-only">Toggle navigation</span>
	            	<span class="icon-bar"></span>
	            	<span class="icon-bar"></span>
	            	<span class="icon-bar"></span>
	          	</button>
	          	<a class="navbar-brand hidden-sm" href="javascript:;" >SosoApi</a>
	        </div>
	        <div class="navbar-collapse collapse" role="navigation">
	          	<ul class="nav navbar-nav">
	            	<li class="menuItem active" data-menu="home"><a href="#home">首页</a></li>
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
    <!--banner-->
    <div class="jumbotron masthead banner" >
      	<div class="container">
        	<h1>SosoApi</h1>
        	<h2>Simple online,Simple offline</h2>
        	<h2>专注于API接口文档管理及线上线下测试的API服务平台</h2>
        	<p class="masthead-button-links">
          		<a class="btn btn-lg btn-fast" href="pass/faq/editWizard.htm" target="_blank" role="button" >快速上手</a>
        	</p>
        	<ul class="masthead-links">
          		<li>
            		<a href="pass/apidoc/demo.htm" target="_blank" role="button" >在线demo</a>
          		</li>
        	</ul>
      	</div>
    </div>
    <!--banner-->
    <div class="container effect-wp">
        <div class="row effect" >
        	<div class="col-lg-4 ">
	           	<img src="${Cfg.IMG_DOMAIN}image/index/word.png">
	           	<h2>告别word</h2>
	           	<p></p>
	            <h4>您是否已厌倦了更改一个api接口就得多人通知，文档互传，最终导致版本混乱接口调错的情况？
	             		自从有了sosoapi再也不用担心接口调错了~
	            </h4>
	          	<p></p>
	      	</div>
          	<div class="col-lg-4">
            	<img src="${Cfg.IMG_DOMAIN}image/index/simple.png">
            	<h2>快速编写</h2>
            	<p></p>
            	<h4>
             		 表单傻瓜式接口文档创建，只要动动鼠标，敲敲几个字，一个接口文档就ok了。
              	</h4>
            	<p></p>
          	</div>

			<div class="col-lg-4">
            	<img src="${Cfg.IMG_DOMAIN}image/index/opensource.png">
            	<h2>线上线下测试</h2>
            	<p></p>
            	<h4>
              		生成基于<a href="http://swagger.io/" target="_blank">swagger ui </a>文档格式的json，既可在线进行测试也可下载相关的json部署到自己的服务器上，既方便又快捷。
              	</h4>
            	<p></p>
			</div>
        </div>
    </div>
    <!--footer-->
    <div class="container-fliud">
    	<div id="contact" class="row featurette-links">
        	<div class=" links clearfix" >
            	<div class="col-lg-offset-4 col-lg-2  text-center">
                	<a target="_blank" href="http://jq.qq.com/?_wv=1027&amp;k=dPKj9Q">
                    	<img class="icon-qq" src="${Cfg.IMG_DOMAIN}image/index/qq.png">
                  	</a>
                  	<h3>QQ</h3>
                </div>
                
                <div class="col-lg-2 text-center ">
                  	<a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&amp;email=hLe2sb20s7eyt7zE9fWq5_vp" style="text-decoration:none;">
                    	<img class="icon-email" src="${Cfg.IMG_DOMAIN}image/index/mail.jpg" >
                  	</a>
                  	<h3>E-Mail</h3>
                </div>
           	</div>
       	</div>
   	</div>
	<!-- PAGE LEVEL SCRIPTS -->
	<jsp:include page="/jsp/common/footer.jsp"/>
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