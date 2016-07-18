<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.sosoapi.com/jsp/auth" prefix="auth"%>
<div class="col-md-2 bootstrap-admin-col-left" style="min-height:600px;">
    <ul id="menu" class="nav in bootstrap-admin-navbar-side">
    	<li class="${param.menuHome ? 'active' : ''}">
            <a href="admin/home.htm">
            	<i class="glyphicon glyphicon-chevron-right"></i> 用户统计
            </a>
        </li>
        
        <li class="${param.menuUser ? 'active' : ''}">
            <a href="admin/user/list.htm">
            	<i class="glyphicon glyphicon-chevron-right"></i> 用户管理
            </a>
        </li>
        
        <li class="${param.menuSuggest ? 'active' : ''}">
            <a href="admin/suggest/list.htm?status=dealing">
            	<i class="glyphicon glyphicon-chevron-right"></i> 用户反馈
            </a>
        </li>
        
        <li class="${param.menuProj ? 'active' : ''}">
            <a href="admin/proj/list.htm">
            	<i class="glyphicon glyphicon-chevron-right"></i> 项目管理
            </a>
        </li>
        
        <li class="${param.menuMsg ? 'active' : ''}">
            <a href="admin/msg/list.htm">
            	<i class="glyphicon glyphicon-chevron-right"></i> 消息管理
            </a>
        </li>
    </ul>
</div>