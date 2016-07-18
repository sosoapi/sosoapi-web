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
				<jsp:param name="menuSwagger" value="true"/>
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
	                                    	1、SwaggerUI是什么？
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse1" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
	                                <div class="panel-body">
	                                	<p>Swagger UI是一款RESTFUL接口的文档在线自动生成+功能测试功能软件。</p>
	                                	<p>Swagger-UI 的官方地址：<a href="http://swagger.io/" target="_blank">http://swagger.io/</a></p>
	                                	<p>Github上的项目地址： <a href="https://github.com/swagger-api/swagger-ui" target="_blank">https://github.com/swagger-api/swagger-ui</a></p>
	                                	<p> 官方提供的demo地址：<a href="http://petstore.swagger.io/" target="_blank">http://petstore.swagger.io/</a></p>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <div class="panel panel-default">
	                            <div class="panel-heading">
	                                <h4 class="panel-title">
	                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse2" class="collapsed" aria-expanded="false">
	                                    	2、为什么API接口文档用SwaggerUI？
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse2" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
	                                <div class="panel-body">
	                                	<p>
	                                		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;现在多数的项目开发中，网站和移动端都需要进行数据交互和对接，这少不了使用REST编写API接口这种场景。
	                                		特别是不同开发小组协作时，就更需要以规范和文档作为标准和协作基础。良好的文档可以减少沟通成本，达到事半功倍的效果。
	                                	</p>
	                                   	<p>
	                                   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有时对一些API说明的理解比较模糊，总想着能直接验证一下自己的理解就好了，而不是需要去项目写测试代码来验证自己的想法。
	                                   		即API文档应具备直接执行能力，这种能力类似word,wiki等是无法提供。
	                                   		SwaggerUI就是这样一种利器，基于html+javascript实现，倾向于在线文档和测试，使用和集成十分简单，能容易地生成不同模块下的API列表，
	                                   		每个API接口描述和参数、请求方法都能定制并直接测试得到直观的响应数据。
	                                   	</p>
     									<p>
     										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;体验SwaggerUI最好的方法就是看下官网提供的demo，看过之后相信你一定会兴奋不已。
     									</p>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <div class="panel panel-default">
	                            <div class="panel-heading">
	                                <h4 class="panel-title">
	                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse3" class="collapsed" aria-expanded="false">
	                                    	3、SwaggerUI怎么用？
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse3" class="panel-collapse collapse" aria-expanded="false">
	                                <div class="panel-body">
	                                	<p>目前官方提供的SwaggerUI的使用方式主要有2种：</p>
	                                    <dl>
	                                    	<dt>与不同的服务端代码集成</dt>
	                                    	<dd>在服务端代码中嵌入SwaggerUI文档生成代码，部署时自动生成。</dd>
	                                    	
	                                    	<dt>&nbsp;</dt>
	                                    	
	                                    	<dt>手动编辑对应的json文档</dt>
	                                    	<dd>该json文档有其特定格式，相对比较复杂，手动编写难度比较大，可通过官方提供的<a href="http://editor.swagger.io/" target="_blank">在线编辑</a>来实现。</dd>
	                                    </dl>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <div class="panel panel-default">
	                            <div class="panel-heading">
	                                <h4 class="panel-title">
	                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse4" class="collapsed" aria-expanded="false">
	                                    	4、SwaggerUI有什么弊端？
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse4" class="panel-collapse collapse" aria-expanded="false">
	                                <div class="panel-body">
	                                    <dl>
	                                    	<dt>集成方式</dt>
	                                    	<dd>
	                                    		<p>
	                                    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果是与服务端代码集成，直接嵌入到工程代码中，侵入性比较大，将文档参数和应用参数杂糅在一起，不易阅读，而且比较依赖于项目，
	                                    			无法独立部署，项目挂掉，文档也无法访问。给后期代码维护增加难度。	
	                                    		</p>
          										
          										<p>
          											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果直接编辑json文档，则难度比较大，即使是官网的在线编辑功能也比较弱，提示功能差劲，很多时候在编辑预览中没问题，导出来部署就显示不正常，而且
													不支持多人编辑，只能一次一个人改，部署相当不方便。
          										</p>
	                                    	</dd>
	                                    	
	                                    	<dt>&nbsp;</dt>
	                                    	
	                                    	<dt>用户体验</dt>
	                                    	<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;无论请求还是响应无法方便的输入自定义json格式，特别是多层嵌套，异常繁琐。</dd>
	                                    </dl>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <div class="panel panel-default">
	                            <div class="panel-heading">
	                                <h4 class="panel-title">
	                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse5" class="collapsed" aria-expanded="false">
	                                    	5、SosoApi如何解决SwaggerUI弊端？
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse5" class="panel-collapse collapse" aria-expanded="false">
	                                <div class="panel-body">
	                                    <dl>
	                                    	<dt>集成方式</dt>
	                                    	<dd>
	                                    		<p>
	                                    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SosoApi摈弃直接在服务端代码嵌入方式，推荐编辑json文档。
	                                    			不过，将编辑方式变更为表单提交方式，用户只要动动鼠标，敲敲几个关键字就可以输出一个接口，
													方便快捷，而且无需学习SwaggerUI相关的json格式，上手简单，减少学习成本。	
	                                    		</p>
	                                    	</dd>
	                                    	
	                                    	<dt>&nbsp;</dt>
	                                    	
	                                    	<dt>用户体验</dt>
	                                    	<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SosoApi支持自定义json格式，可以随意的输入自定义的json，再也不用受到原来文档格式的约束。</dd>
	                                    </dl>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <div class="panel panel-default">
	                            <div class="panel-heading">
	                                <h4 class="panel-title">
	                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse6" class="collapsed" aria-expanded="false">
	                                    	6、扩展版SwaggerUI新增了哪些功能？
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse6" class="panel-collapse collapse" aria-expanded="false">
	                                <div class="panel-body">
										<ul>
											<li>请求参数数据类型新增"自定义"，允许用户直接输入相关的json格式数据</li>
											<li>响应数据类型新增"自定义"，允许用户直接输入相关的json格式数据</li>
											<li>国际化支持</li>
											<li>bug修复</li>
										</ul>
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
