<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.sosoapi.com/jsp/auth" prefix="auth"%>
<div class="col-md-2 bootstrap-admin-col-left" style="min-height:600px;">
    <ul id="menu" class="nav in bootstrap-admin-navbar-side">
    	<li class="${param.menuSetting ? 'active' : ''}">
            <a href="auth/user/setting.htm">
            	<i class="glyphicon glyphicon-chevron-right"></i> 账号管理
            </a>
        </li>
        
        <li class="${param.menuMsg ? 'active' : ''}">
            <a href="auth/msg/list.htm">
     			<i class="glyphicon glyphicon-chevron-right"></i> 消息管理       
            </a>
        </li>
    </ul>
</div>