<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>




<c:if test="${not empty path}">
	<c:set var="path" value="${path}" scope="request" />
	<% 
	String  key="path."+(String)request.getAttribute("path");
	%>
	<p><fmt:message key="<%=key%>" /><c:if
		test="${not empty path_extension1}"> / <strong><c:out
			value="${path_extension1}" /></strong>
	</c:if></p>
</c:if>




