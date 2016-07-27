<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- small navbar -->
<nav class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar-sm" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="collapse navbar-collapse">
                    <!-- <ul class="nav navbar-nav navbar-left bootstrap-admin-theme-change-size">
                        <li class="text">Change size:</li>
                        <li><a class="size-changer small">Small</a></li>
                        <li><a class="size-changer large active">Large</a></li>
                    </ul> -->
                    <ul class="nav navbar-nav navbar-right">
                    	<li>
                  			<a href=""><i class="fa fa-home"></i> <fmt:message key="nav.top.page.index" /></a>
                        </li>
		                        
                    	<c:choose>
                    		<c:when test="${not empty userInfo}">
                    			<li>
		                            <a href="auth/msg/list.htm" style="width:110px;">
		                            	<i class="fa fa-envelope"></i> 
		                            	<span><fmt:message key="nav.top.page.message" /></span>
		                            	<c:if test="${userInfo.newMsgCount > 0 }">
											<span class="label label-danger">${userInfo.newMsgCount} </span>		                            	
		                            	</c:if>
		                            	
		                            	<c:if test="${userInfo.newMsgCount == 0 }">
											<span class="label label-default">${userInfo.newMsgCount} </span>		                            	
		                            	</c:if>
		                            </a>
		                        </li>
		                        <!-- <li>
		                            <a href="javascript:void(0);">设置 <i class="fa fa-cog"></i></a>
		                        </li> -->
		                        <li class="dropdown">
		                            <a href="javascript:void(0);" role="button" class="dropdown-toggle" data-hover="dropdown"> 
		                            	<i class="fa fa-user"></i> ${userInfo.nickName} 
		                            	<i class="caret"></i>
		                            </a>
		                            
		                            <ul class="dropdown-menu">
		                                <li>
		                                	<a href="auth/user/setting.htm"><fmt:message key="nav.top.page.accountManagement" /></a>
		                                </li>
		                                
		                                <li role="presentation" class="divider"></li>
		                                
		                                <li>
		                                	<a href="logout.htm"><fmt:message key="nav.top.page.exit" /></a>
		                                </li>
		                            </ul>
		                        </li>	
		                        <li class="dropdown">
		                            <a href="javascript:void(0);" role="button" class="dropdown-toggle" data-hover="dropdown"> 
		                            	<i class="fa fa-language"></i> <fmt:message key="nav.top.page.lang" /> 
		                            	<i class="caret"></i>
		                            </a>
		                            
		                            <ul class="dropdown-menu">
		                                <li <c:if test="${locale eq 'en_US'}">style="background-color: #337ab7"</c:if>>
		                                	<a href="javascript:settingLanguage('en_US');"><fmt:message key="nav.top.page.en_US" /></a>
		                                </li>
		                                
		                                <li <c:if test="${locale eq 'zh_CN'}">style="background-color: #337ab7"</c:if>>
		                                	<a href="javascript:settingLanguage('zh_CN');"><fmt:message key="nav.top.page.zh_CN" /></a>
		                                </li>
		                            </ul>
		                        </li>	
                    		</c:when>
                    		<c:otherwise>
                    			<li>
                    				<a href="forwardLogin.htm"> <fmt:message key="nav.top.page.signIn" /></a>
                    			</li>
                    			<li>>
                    				<a href="regist/forwardRegist.htm"> <fmt:message key="nav.top.page.signUp" /></a>
                    			</li>
                    		</c:otherwise>
                    	</c:choose>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>

<!-- main / large navbar -->
<nav class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="navbar-header">
                    <!-- <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".main-navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                    </button> -->
                    <a class="navbar-brand" href="" >
						<img src="${Cfg.IMG_DOMAIN}image/logo-small.png" style="margin-top:-10px;" alt="Logo"/>
					</a>
                </div>
                
                <div class="collapse navbar-collapse main-navbar-collapse">
                    <!-- <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-hover="dropdown">Dropdown <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li role="presentation" class="dropdown-header">Dropdown header</li>
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="presentation" class="divider"></li>
                                <li role="presentation" class="dropdown-header">Dropdown header</li>
                                <li><a href="#">Separated link</a></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </li>
                    </ul> -->
                    
                    <ul class="nav navbar-nav">
                    	<c:if test="${not empty userInfo}">
                    		<li class="${param.menuHome ? 'active' : ''}">
	                        	<c:if test="${userInfo.role == 'admin'}">
									<a href="admin/home.htm"><fmt:message key="project.indexPage" /></a>
								</c:if>
								<c:if test="${userInfo.role != 'admin'}">
									<a href="auth/home/home.htm"><fmt:message key="project.indexPage" /></a>												
								</c:if>
	                        </li>
	                        
	                        <li class="${param.menuProject ? 'active' : ''}">
	                        	<a href="auth/proj/list.htm"><fmt:message key="project.projectManagent" /></a>
	                        </li>
	                        
	                        <li class="${param.menuApiDoc ? 'active' : ''}">
	                        	<a href="auth/doc/list.htm"><fmt:message key="project.apiDocumentManagent" /></a>
	                        </li>
	                        
	                        <li class="${param.menuUserHome ? 'active' : ''}">
	                        	<a href="auth/user/home.htm"><fmt:message key="project.personalCenter" /></a>
	                        </li>
	                        
	                        <li class="${param.menuSuggest ? 'active' : ''}">
	                        	<a href="auth/suggest/forwardSuggest.htm"><fmt:message key="project.feedback" /></a>
	                        </li>
                    	</c:if>
                        
                        <li class="${param.menuFaq ? 'active' : ''}">
                        	<a href="pass/faq/home.htm"><fmt:message key="project.FAQ" /></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>
<script>
	function settingLanguage(locale){
		if('${locale}' == locale) return;
		$.post("${Cfg.WEB_BASE_URL}admin/setting/language.htm",{locale:locale},function(backData){
			if(backData.errorCode == 0){
				history.go(0);
			}
		});
	}
</script>
