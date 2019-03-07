<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"
	type="text/javascript"></script>

</head>
<body>

	<div style="text-align: center;">
		<ul class="pagination" style="text-align: center; margin-top: 10px;">
			<c:if test="${page.currentPageNum == 1 }">
				<li class="disabled">
					
						<span aria-hidden="true">&laquo;</span>
					
				</li>
			</c:if>
			
			<c:if test="${page.currentPageNum>1 }">
				<li>
					<a href="${pageContext.request.contextPath}/${page.url }&page=${page.prePageNum }" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			</c:if>
			
			<c:forEach begin="${page.startPage }" end="${page.endPage }" var="i">
			
				<c:if test="${page.currentPageNum == i}">
					<li class="active"><a>${i }</a></li>
				</c:if>
				<c:if test="${page.currentPageNum != i}">
					<li><a href="${pageContext.request.contextPath}/${page.url }&page=${i }">${i }</a></li>
				</c:if>
				
			</c:forEach>

			<c:if test="${page.currentPageNum == page.totalPages }">
				<li class="disabled">
				
						<span aria-hidden="true">&raquo;</span>
						
				</li>
			</c:if>
			
			<c:if test="${page.currentPageNum<page.totalPages }">
				<li>
					<a href="${pageContext.request.contextPath}/${page.url }&page=${page.nextPageNum }" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</c:if>
			
			
		</ul>
	</div>


</body>
</html>