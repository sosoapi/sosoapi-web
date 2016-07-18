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
			min-height: 120em;
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
                            <h4>在线编辑说明文档(可左右键切换步骤)</h4>
                        </div>
                	</div>
                </div>
                
    	        <div class="row">
        	        <div class="col-lg-12">
                        <div id="editWizard">
						    <h4>新建项目</h4>
						    <section>
						        <div class="row">
									<div class="col-lg-12">
										<dl>
											<dt>注意事项</dt>
											<dd>
												<ol>
													<li>"编码"属性为项目的英文名称</li>
												</ol>
											</dd>
										</dl>
						        	</div>
						        </div>
						        
						    	<div class="row">
									<div class="col-lg-12">
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/1.png">
						        		<p class="text-center">&nbsp;</p>
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/2.png">
						        	</div>
						        </div>
						    </section>
						    
						    <h4>编辑项目</h4>
						    <section>
						    	<div class="row">
									<div class="col-lg-12">
										<dl>
											<dt>注意事项</dt>
											<dd>
												<ol>
													<li>退出，指的是退出当前项目，退出后在列表中看不到该项目，其他成员依然可以看到</li>
													<li>删除，指的是删除项目本身，删除后项目中的所有成员都看不到，只有管理员才有该权限，慎用</li>
												</ol>
											</dd>
										</dl>
						        	</div>
						        </div>
						        
						        <div class="row">
									<div class="col-lg-12">
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/3.png">
						        	</div>
						        </div>
						    </section>
						    
						    <h4>编辑项目基本信息</h4>
						    <section>
						        <div class="row">
									<div class="col-lg-12">
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/4.png">
						        	</div>
						        </div>
						    </section>
						    
						    <h4>管理项目成员</h4>
						    <section>
						    	<div class="row">
									<div class="col-lg-12">
										<dl>
											<dt>步骤说明</dt>
											<dd>
												<ol>
													<li>新增成员，通过邮件邀请，邀请者注册登陆后点击邀请链接加入项目</li>
													<li>编辑成员，目前角色只有"管理员"和"访客"两种，其中只有"管理员"才有编辑权限,默认邀请的角色为"访客"</li>
													<li>移除成员，可移除人员成员，必须保证至少要有一名角色为"管理员"成员</li>
												</ol>
											</dd>
											
											<dt>注意事项</dt>
											<dd>
												<ol>
													<li>成员角色的改变在下次登陆后生效</li>
												</ol>
											</dd>
										</dl>
						        	</div>
						        </div>
						        
						        <div class="row">
									<div class="col-lg-12">
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/5.png">
						        	</div>
						        </div>
						    </section>
						    
						    <h4>编辑API文档</h4>
						    <section>
						    	<div class="row">
									<div class="col-lg-12">
										<dl>
											<dt>注意事项</dt>
											<dd>
												<ol>
													<li>
														访问主机，可配置为外网，局域网或本机ip，因为测试是基于本地的ajax请求，
														所以只要API所在服务器处理好跨域请求都支持网站在线测试API
													</li>
												</ol>
											</dd>
										</dl>
						        	</div>
						        </div>
						        
						        <div class="row">
									<div class="col-lg-12">
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/6.png">
						        	</div>
						        </div>
						    </section>
						    
						    <h4>模块管理</h4>
						    <section>
						    	<div class="row">
									<div class="col-lg-12">
										<dl>
											<dt>步骤说明</dt>
											<dd>
												<ol>
													<li>
														主要用于对API接口进行分类，可以不设置，默认全部在"default"组下面
													</li>
												</ol>
											</dd>
											
											<dt>注意事项</dt>
											<dd>
												<ol>
													<li>
														API文档预览展示的模块顺序为升序，如需按要求排序可在模块名称前加序号指定
													</li>
												</ol>
											</dd>
										</dl>
						        	</div>
						        </div>
						        
						        <div class="row">
									<div class="col-lg-12">
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/7.png">
						        	</div>
						        </div>
						    </section>
						    
						    <h4>编辑数据结构</h4>
						    <section>
						    	<div class="row">
									<div class="col-lg-12">
										<dl>
											<dt>步骤说明</dt>
											<dd>
												<ol>
													<li>
														数据结构主要用于固定格式的服务器响应结构，适用于多个接口可能返回相同的数据结构，编辑保存后相关的引用都会变更。
													</li>
													<li>
														<p>支持的数据类型说明如下：</p>
														<ul>
															<li>string:字符串类型</li>
															<li>array:数组类型，可创建复合结构</li>
															<li>object:对象类型，可创建复合结构</li>
															<li>int:短整型</li>
															<li>long:长整型</li>
															<li>float:浮点型</li>
															<li>double:浮点型</li>
															<li>decimal:精确到比较高的浮点型</li>
															<li>ref:引用类型，即引用定义好的数据结构</li>
														</ul>
													</li>
												</ol>
											</dd>
										</dl>
						        	</div>
						        </div>
						        
						        <div class="row">
									<div class="col-lg-12">
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/8.png">
						        		<p class="text-center">&nbsp;</p>
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/9.png">
						        		<p class="text-center">&nbsp;</p>
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/10.png">
						        	</div>
						        </div>
						    </section>
						    
						    <h4>接口管理</h4>
						    <section>
						        <div class="row">
									<div class="col-lg-12">
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/11.png">
						        	</div>
						        </div>
						    </section>
						    
						    <h4>编辑接口基本信息</h4>
						    <section>
						    	<div class="row">
									<div class="col-lg-12">
										<dl>
											<dt>注意事项</dt>
											<dd>
												<ol>
													<li>"请求url"和"请求方式"两者唯一确定一个接口，不能重复</li>
												</ol>
											</dd>
										</dl>
						        	</div>
						        </div>
						        
						        <div class="row">
									<div class="col-lg-12">
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/12.png">
						        	</div>
						        </div>
						    </section>
						    
						    <h4>编辑接口请求参数</h4>
						    <section>
						    	<div class="row">
									<div class="col-lg-12">
										<dl>
											<dt>步骤说明</dt>
											<dd>
												<ol>
													<li>
														<p>参数位置说明如下：</p>
														<ul>
															<li>body：http请求body</li>
															<li>cookie：本地cookie</li>
															<li>formData：表单参数</li>
															<li>header：http请求header</li>
															<li>path：http请求url,如getInfo/{userId}</li>
															<li>query：http请求拼接，如getInfo?userId={userId}</li>
														</ul>
													</li>
													<li>
														<p>参数类型说明如下：</p>
														<ul>
															<li>参考"编辑数据结构"步骤中的参数类型说明</li>
															<li>自定义：目前仅支持自定义json格式，仅当"参数位置"为“body"有效</li>
														</ul>
													</li>
												</ol>
											</dd>
										</dl>
						        	</div>
						        </div>
						        
						        <div class="row">
									<div class="col-lg-12">
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/13.png">
						        		<p class="text-center">&nbsp;</p>
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/14.png">
						        		<p class="text-center">&nbsp;</p>
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/15.png">
						        	</div>
						        </div>
						    </section>
						    
						    <h4>编辑接口响应信息</h4>
						    <section>
						    	<div class="row">
									<div class="col-lg-12">
										<dl>
											<dt>步骤说明</dt>
											<dd>
												<ol>
													<li>
														<p>数据类型说明如下：</p>
														<ul>
															<li>参考"编辑数据结构"步骤中的参数类型说明</li>
															<li>自定义：目前仅支持自定义json格式，仅当"参数位置"为“body"有效</li>
														</ul>
													</li>
												</ol>
											</dd>
											
											<dt>注意事项</dt>
											<dd>
												<ol>
													<li>
														指定默认响应时，只需将响应"编码"设置为"default"即可
													</li>
												</ol>
											</dd>
										</dl>
						        	</div>
						        </div>
						        
						        <div class="row">
									<div class="col-lg-12">
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/16.png">
						        		<p class="text-center">&nbsp;</p>
						        		<img class="img-responsive img-rounded" src="${Cfg.IMG_DOMAIN}image/edit/17.png">
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
			$("#editWizard").steps({
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
