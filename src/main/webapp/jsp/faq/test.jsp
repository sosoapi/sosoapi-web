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
				<jsp:param name="menuTest" value="true"/>
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
	                                    	1、为何执行测试后返回"no response from server"？
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse1" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
	                                <div class="panel-body">
	                                	<p>如果执行测试后的效果页和js调试窗口的错误信息如如下2个截图所示，则表示服务端未处理ajax跨域请求。</p>
	                                	<div class="row">
											<div class="col-lg-12">
												<p>请求响应：</p>
								        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/faq/1.png">
								        		<p>&nbsp;</p>
								        		<p>js调试控制台：</p>
						        				<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/faq/2.png">
								        	</div>
								        </div>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <div class="panel panel-default">
	                            <div class="panel-heading">
	                                <h4 class="panel-title">
	                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse2" aria-expanded="false" class="collapsed">
	                                    	2、什么是ajax跨域请求？
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse2" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
	                                <div class="panel-body">
	                                	<p>概念：只要协议、域名、端口有任何一个不同，都被当作是不同的域。</p>
	                                	<p>ajax跨域请求相关知识点可参考文章： 
	                                		<a href="http://segmentfault.com/a/1190000000718840" target="_blank">详解js跨域问题</a>
	                                		&nbsp;&nbsp;
	                                		<a href="http://netsecurity.51cto.com/art/201311/419179.htm" target="_blank">跨域资源共享(CORS)安全性浅析</a>
	                                	</p>
	                                	<p>事例如下</p>
	                                	<p>
	                                		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/faq/cors.png">
	                                	</p>
	                                </div>
	                            </div>
	                     	</div> 
	                        
	                        <div class="panel panel-default">
	                            <div class="panel-heading">
	                                <h4 class="panel-title">
	                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse3" aria-expanded="false" class="collapsed">
	                                    	3、接口测试时如何处理ajax跨域请求？
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse3" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
	                                <div class="panel-body">
	                                	<p>ajax跨域请求相关知识点可参考文章： 
	                                		<a href="http://segmentfault.com/a/1190000000718840" target="_blank">详解js跨域问题</a>
	                                		&nbsp;&nbsp;
	                                		<a href="http://netsecurity.51cto.com/art/201311/419179.htm" target="_blank">跨域资源共享(CORS)安全性浅析</a>
	                                	</p>
	                                	<p>跨域处理目前有如下几种方式：</p>
	                                	<dl>
	                                		<dt>1、服务端设置允许跨域</dt>
	                                		<dd>
	                                			<p>
	                                		以java为例，跨域请求服务端可以通过添加过滤器设置HttpServletResponse中的header来处理，部分源码如下:
<pre>
@Override
public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
	HttpServletResponse resp = (HttpServletResponse)response;
	//"*"存在风险，建议指定可信任的域名来接收响应信息，如"http://www.sosoapi.com"
	resp.addHeader("Access-Control-Allow-Origin", "*");
	//如果存在自定义的header参数，需要在此处添加，逗号分隔
	resp.addHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, "
			+ "If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, "
			+ "Content-Type, X-E4M-With");
	resp.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");  
	
	chain.doFilter(request, response);
}
</pre>
	                                			</p>
	                                		</dd>
	                                		
	                                		<dt>2、线下部署</dt>
	                                		<dd>
	                                			<p>参考线下部署章节。</p>
	                                		</dd>
	                                		
	                                		<dt>3、部署代理服务器</dt>
	                                		<dd>
	                                			<p>
	                                				由交流群群友提供，在ajax请求和后台服务器直接添加代理服务器，在代理服务器设置跨域需要的头信息，流程大概为:ajax->proxy->服务器。
	                                			</p>
	                                		</dd>
	                                		
	                                		<dt>4、浏览器屏蔽跨域安全检查</dt>
	                                		<dd>
	                                			<p>
	                                				以chrome为例，可参考<a href="http://www.tuicool.com/articles/AjuqA3" target="_blank">http://www.tuicool.com/articles/AjuqA3</a>。
	                                				如果添加--disable-web-security无效的话，重启下浏览器并确保所有chrome后台进程已被关闭，重启后再次访问就可以了
	                                			</p>
	                                		</dd>
	                                		
	                                		<dt>5、浏览器添加伪造Access-Control-Allow-Origin的插件(建议)</dt>
	                                		<dd>
	                                			<p>
	                                				由交流群群友提供，以chrome为例，下载<a href="http://7xndff.com1.z0.glb.clouddn.com/attach%2FAllow-Control-Allow-Origin.zip">Access-Control-Allow-Origin插件</a>。
	                                			</p>
	                                		</dd>
	                                	</dl>
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
