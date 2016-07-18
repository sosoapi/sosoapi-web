<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-md-2 bootstrap-admin-col-left">
    <!-- <ul id="menu" class="nav in bootstrap-admin-navbar-side" style="position:fixed;width:150px;"> -->
    <ul id="menu" class="nav in bootstrap-admin-navbar-side">
        <li class="${param.menuHome ? 'active' : ''}">
            <a href="auth/home/home.htm">
            	<i class="glyphicon glyphicon-chevron-right"></i> 首页
            </a>
        </li>
        
        <li class="${param.menuProject ? 'active' : ''}">
            <a href="auth/proj/list.htm">
            	<i class="glyphicon glyphicon-chevron-right"></i> 项目管理
            	<span class="pull-right"><img src="image/new.gif"></span>
            </a>
        </li>
        
        <%-- <li>
            <a href="javascript:void(0);" data-parent="#menu" data-toggle="collapse" 
            		class="accordion-toggle collapsed" data-target="#nav-project">
            	<i class="glyphicon glyphicon-chevron-down"></i> 项目管理
            </a>
            <ul id="nav-project" class="nav bootstrap-admin-navbar-side ${param.menuProject ? 'in' : 'collapse'}" style="height: ${param.menuProject ? 'auto' : '0px'};">
                <li class="${param.subMenuAllProject ? 'active' : ''}">
                	<a href="auth/proj/list.htm?pageNumber=1&pageSize=10">
                		<i class="glyphicon glyphicon-chevron-right"></i> 所有项目
                	</a>
                </li>
                
                <li>
                	<a href="javascript:void(0);">
                		<i class="glyphicon glyphicon-chevron-right"></i> 我的项目
                	</a>
                </li>
                
                <li>
                	<a href="javascript:void(0);">
                		<i class="glyphicon glyphicon-chevron-right"></i> 参与项目
                	</a>
                </li>
            </ul>
        </li> --%>
        
        <li class="${param.menuApiDoc ? 'active' : ''}">
            <a href="auth/doc/list.htm">
            	<i class="glyphicon glyphicon-chevron-right"></i> Api文档
            </a>
        </li>
        
        <li class="${param.menuSuggest ? 'active' : ''}">
            <a href="auth/suggest/forwardSuggest.htm">
            	<i class="glyphicon glyphicon-chevron-right"></i> 意见反馈
            </a>
        </li>
    </ul>
</div>