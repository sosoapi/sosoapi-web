<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<h1>${projTempMap["" + param.projId + ""].code}</h1>
<div class="pull-right">
	<a href="auth/apidoc/preview.htm?docId=${param.docId}" target="_bank" class="btn btn-sm btn-success">
		<i class="fa fa-eye"></i> 预览
	</a>
                 	
	<c:if test='${projTempMap["" + param.projId + ""].role == "admin"}'>
		<a href="auth/proj/mem/forwardSendNotice.htm?projId=${param.projId}&docId=${param.docId}" class="btn btn-sm btn-warning">
			<i class="fa fa-envelope-square"></i> 变更通知
		</a>
	</c:if>
	
	<div class="btn-group">
        <button class="btn btn-sm btn-default">
        	<i class="fa fa-share"></i> 导出
        </button>
        
        <button data-toggle="dropdown" class="btn btn-sm btn-default dropdown-toggle"><span class="caret"></span></button>
        <ul class="dropdown-menu">
            <li>
            	<a href="auth/apidoc/export.htm?docId=${param.docId}&docFormat=html">
            		<i class="fa fa-file-text"></i> html文档
            	</a>
            </li>
            <li>
            	<a href="auth/apidoc/export.htm?docId=${param.docId}&docFormat=doc">
            		<i class="fa fa-file-word-o"></i> word文档
            	</a>
            </li>
            <li>
            	<a href="auth/apidoc/export.htm?docId=${param.docId}&docFormat=json">
            		<i class="fa fa-file-code-o"></i> json文档
            	</a>
            </li>
        </ul>
    </div>
</div>




