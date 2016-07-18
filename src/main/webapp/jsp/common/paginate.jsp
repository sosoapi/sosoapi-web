<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:choose>
	<c:when test="${not empty paginate}">
		<div class="row">
			<div class="col-md-6">
				<div style="padding-top:15px;"><h4>总共${paginate.totalCount}条记录</h4></div>
			</div>
			
			<div class="col-md-6">
				<div class="dataTables_paginate paging_bootstrap">
					<c:if test="${paginate.totalPage > 1}">
						<ul class="pagination">
							<li class="prev ${paginate.first ? 'disabled': ''}">
								<a href="${paginate.first ? 'javascript:void(0)': paginate.firstUrl}">&lt;&lt;</a>
							</li>
							<li class="prev ${paginate.first ? 'disabled': ''}">
								<a href="${paginate.first ? 'javascript:void(0)': paginate.prevUrl}">&lt;</a>
							</li>
							<c:forEach var="pageNumber" varStatus="status" begin="${paginate.pageStart}" end="${paginate.pageEnd}">
							  	<li class="${paginate.pageNumber == pageNumber ? 'active' : '' }">
							  		<a href="${paginate.url}${pageNumber}">${pageNumber}</a>
							  	</li>
							</c:forEach>
							<li class="next ${paginate.last ? 'disabled': ''}">
								<a href="${paginate.last ? 'javascript:void(0)': paginate.nextUrl}">&gt; </a>
							</li>
							<li class="next ${paginate.last ? 'disabled': ''}">
								<a href="${paginate.last ? 'javascript:void(0)': paginate.lastUrl}">&gt;&gt;</a>
							</li>
						</ul>
					</c:if>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="row">
			<div class="col-md-6">
				<div style="padding-top:15px;"><h4>当前无记录</h4></div>
			</div>
		</div>
	</c:otherwise>
</c:choose>




