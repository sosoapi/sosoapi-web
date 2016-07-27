<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.sosoapi.com/jsp/auth" prefix="auth"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="col-md-2 bootstrap-admin-col-left" style="min-height:600px;">
    <ul id="menu" class="nav in bootstrap-admin-navbar-side">
        <li class="${param.menuProjInfo ? 'active' : ''}">
            <a href="auth/proj/info.htm?projId=${param.projId}&docId=${param.docId}">
            	<i class="glyphicon glyphicon-chevron-right"></i> <fmt:message key="menu.left.projBasicInfo" />
            </a>
        </li>
        
        <c:if test='${projTempMap["" + param.projId + ""].role == "admin"}'>
        	<li class="${param.menuProjMem ? 'active' : ''}">
	            <a href="auth/proj/mem/list.htm?projId=${param.projId}&docId=${param.docId}">
	            	<i class="glyphicon glyphicon-chevron-right"></i> <fmt:message key="menu.left.projMembers" />
	            </a>
	        </li>
	        
	        <li class="${param.menuNotice ? 'active' : ''}">
	            <a href="auth/proj/mem/forwardSendNotice.htm?projId=${param.projId}&docId=${param.docId}">
	            	<i class="glyphicon glyphicon-chevron-right"></i> <fmt:message key="menu.left.changeNotification" />
	            </a>
	        </li>
	        
	        <li class="${param.menuProjLog ? 'active' : ''}">
	            <a href="auth/proj/log/list.htm?projId=${param.projId}&docId=${param.docId}">
	            	<i class="glyphicon glyphicon-chevron-right"></i> <fmt:message key="menu.left.changeHistory" />
	            	<span class="pull-right"><img src="image/new.gif"></span>
	            </a>
	        </li>
        </c:if>
        
        <auth:projAuth projId="${param.projId}">
        	<li class="${param.menuDocInfo ? 'active' : ''}">
	            <a href="auth/doc/info.htm?projId=${param.projId}&docId=${param.docId}">
	            	<i class="glyphicon glyphicon-chevron-right"></i> <fmt:message key="menu.left.apiInfo" />
	            </a>
	        </li>
	        
	        <li class="${param.menuDocModule ? 'active' : ''}">
	            <a href="auth/doc/module/list.htm?projId=${param.projId}&docId=${param.docId}">
	            	<i class="glyphicon glyphicon-chevron-right"></i> <fmt:message key="menu.left.moduleManagement" />
	            </a>
	        </li>
	        
	        <li class="${param.menuDocInter ? 'active' : ''}">
	            <a href="auth/doc/inter/list.htm?projId=${param.projId}&docId=${param.docId}">
	            	<i class="glyphicon glyphicon-chevron-right"></i> <fmt:message key="menu.left.interfaceManagement" />
	            </a>
	        </li>
	        
	        <li class="${param.menuDocSchema ? 'active' : ''}">
	            <a href="auth/doc/schema/list.htm?projId=${param.projId}&docId=${param.docId}">
	            	<i class="glyphicon glyphicon-chevron-right"></i> <fmt:message key="menu.left.dataStructrue" />
	            </a>
	        </li>
        </auth:projAuth>
    </ul>
</div>