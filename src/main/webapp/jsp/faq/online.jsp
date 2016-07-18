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
				<jsp:param name="menuOnline" value="true"/>
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
	                                    	1、如何在线编辑？
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse1" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
	                                <div class="panel-body">
	                                	<p><a href="${Cfg.ONLINE_HELP_URL}">在线编辑说明文档</a></p>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <div class="panel panel-default">
	                            <div class="panel-heading">
	                                <h4 class="panel-title">
	                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse2" aria-expanded="false" class="collapsed">
	                                    	2、请求参数和响应参数支持的数据类型有哪些？
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse2" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
	                                <div class="panel-body">
	                                	<p>目前支持的数据类型如下：</p>
	                                	<ol>
	                                		<li>string:字符串类型</li>
	                                		<li>array:数组类型，子项只能是支持的数据类型中的一种，不能添加多个</li>
	                                		<li>object:对象类型，只支持一级属性，不支持嵌套，嵌套可以通过在属性中引入ref类型的对象或自定义数据格式</li>
	                                		<li>int:短整型</li>
	                                		<li>long:长整型</li>
	                                		<li>float:浮点型</li>
	                                		<li>double:浮点型</li>
	                                		<li>decimal:精确到比较高的浮点型</li>
	                                		<li>ref:引用类型，即引用定义好的数据结构，共用数据结构</li>
	                                	</ol>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <div class="panel panel-default">
	                            <div class="panel-heading">
	                                <h4 class="panel-title">
	                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse3" aria-expanded="false" class="collapsed">
	                                    	3、请求参数中的"参数位置"如何区别使用？
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse3" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
	                                <div class="panel-body">
	                                	<p>目前支持的类型如下：</p>
	                                	<ol>
	                                		<li>body：http请求body</li>
	                                		<li>cookie：本地cookie</li>
	                                		<li>formData：表单参数</li>
	                                		<li>header：http请求header</li>
	                                		<li>path：http请求url,如getInfo/{userId}</li>
	                                		<li>query：http请求拼接，如getInfo?userId={userId}</li>
	                                	</ol>
	                                </div>
	                            </div>
	                        </div>
	                        
	                        <div class="panel panel-default">
	                            <div class="panel-heading">
	                                <h4 class="panel-title">
	                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse4" aria-expanded="false" class="collapsed">
	                                    	4、请求参数和请求响应如何定义复合数据结构？
	                                    </a>
	                                </h4>
	                            </div>
	                            <div id="collapse4" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">
	                                <div class="panel-body">
	                                	<p><strong>目前请求参数仅当"参数位置"为"body"时才可使用复合结构。</strong></p>
	                                	<p>假设需要定义如下复合数据结构的请求响应</p>
<pre>
{
	"errorCode":"string",
	"data"：{
		"totalCount":"int",
		"list":[
			{
				"userId":"long",
				"nickName":"string"
			}
		]
	}
}
</pre>
										<p>目前有3种方式</p>
										<dl>
	                                    	<dt>引用方式多层嵌套</dt>
	                                    	<dd>
	                                    	    <p>该方式类似于面向对象编程中的复合对象，通过嵌套对象来实现复杂结构。</p>
	                                    	    <p>
	                                    	    	采用该方式，首先需要在项目管理中的"数据结构"定义一个名为"UserInfo"的类型为"object"的数据结构，
	                                    	    包含属性"userId"和"nickName"，类型分别为"long"和"string"
	                                    	    </p>
	                                    	    <p>
	                                    	    	然后在"数据结构"中定义一个名为"Data"的类型为"object"的数据结构，包含属性"totalCount"，类型为"int"，
	                                    	    以及属性"list",类型为"array"且内置属性为指向"UserInfo"的"ref"类型。
	                                    	    </p>
	                                    	    <p>
	                                    	    	最后，在接口"响应信息"中创建一个名为"default"的类型为"object"的数据结构，包含属性"errorCode"，类型为"string"，
	                                    	    以及属性"data",类型为指向"Data"的"ref"类型。
	                                    	    </p>
	                                    	</dd>
	                                    	
	                                    	<dt>&nbsp;</dt>
	                                    	
	                                    	<dt>非引用方式多层嵌套</dt>
	                                    	<dd>
	                                    		<p>定义一个名为"UserInfo"的类型为"object"的数据结构，然后按对应的数据结构添加节点(右键菜单)即可。</p>
	                                    	</dd>
	                                    	
	                                    	<dt>&nbsp;</dt>
	                                    	
	                                    	<dt>自定义格式</dt>
	                                    	<dd>
	                                    	    该方法相对比较简单，只要在接口"响应信息"中创建一个名为"result"的类型为"自定义"的数据结构，并将上述复合结构当做属性值即可。
	                                    	</dd>
	                                    </dl>
	                                    
	                                    <p>综上，2种方法中，第1种相对复杂，特别是嵌套层次比较多。第2种完全由用户自定义，简单明了。</p>
	                                    <p>故复合结构推荐使用自定义格式。</p>
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
