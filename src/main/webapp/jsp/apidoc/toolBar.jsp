<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<h1>${projTempMap["" + param.projId + ""].code}</h1>
<div class="pull-right">
	<a href="auth/apidoc/preview.htm?docId=${param.docId}" target="_bank" class="btn btn-sm btn-success">
		<i class="fa fa-eye"></i> <fmt:message key="apidoc.toolbar.preview" />
	</a>
                 	
	<c:if test='${projTempMap["" + param.projId + ""].role == "admin"}'>
		<a href="auth/proj/mem/forwardSendNotice.htm?projId=${param.projId}&docId=${param.docId}" class="btn btn-sm btn-warning">
			<i class="fa fa-envelope-square"></i> <fmt:message key="apidoc.toolbar.changeNotification" />
		</a>
	</c:if>
	
	<div class="btn-group">
        <button class="btn btn-sm btn-default">
        	<i class="fa fa-share"></i> <fmt:message key="apidoc.toolbar.export" />
        </button>
        
        <button data-toggle="dropdown" class="btn btn-sm btn-default dropdown-toggle"><span class="caret"></span></button>
        <ul class="dropdown-menu">
            <li>
            	<a href="auth/apidoc/export.htm?docId=${param.docId}&docFormat=html">
            		<i class="fa fa-file-text"></i> <fmt:message key="apidoc.toolbar.htmlDocumention" />
            	</a>
            </li>
            <li>
            	<a href="auth/apidoc/export.htm?docId=${param.docId}&docFormat=doc">
            		<i class="fa fa-file-word-o"></i> <fmt:message key="apidoc.toolbar.wordDocumention" />
            	</a>
            </li>
            <li>
            	<a href="auth/apidoc/export.htm?docId=${param.docId}&docFormat=json">
            		<i class="fa fa-file-code-o"></i> <fmt:message key="apidoc.toolbar.jsonDocumention" />
            	</a>
            </li>
        </ul>
    </div>
</div>




