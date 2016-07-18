<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.sosoapi.com/jsp/auth" prefix="auth"%>
<div class="col-md-2 bootstrap-admin-col-left" style="min-height:600px;">
    <ul id="menu" class="nav in bootstrap-admin-navbar-side">
    	<li class="${param.menuSwagger ? 'active' : ''}">
            <a href="pass/faq/swagger.htm">
            	<i class="glyphicon glyphicon-chevron-right"></i> SwaggerUI
            </a>
        </li>
        
        <li class="${param.menuOnline ? 'active' : ''}">
            <a href="pass/faq/online.htm">
     			<i class="glyphicon glyphicon-chevron-right"></i> 线上编辑       
            </a>
        </li>
        
        <li class="${param.menuOffline ? 'active' : ''}">
            <a href="pass/faq/offline.htm">
            	<i class="glyphicon glyphicon-chevron-right"></i> 线下部署
            </a>
        </li>
        
        <li class="${param.menuTest ? 'active' : ''}">
            <a href="pass/faq/test.htm">
            	<i class="glyphicon glyphicon-chevron-right"></i> 在线测试
            </a>
        </li>
        
        <li class="${param.menuAbout ? 'active' : ''}">
            <a href="pass/faq/about.htm">
            	<i class="glyphicon glyphicon-chevron-right"></i> 关于我们
            </a>
        </li>
    </ul>
</div>