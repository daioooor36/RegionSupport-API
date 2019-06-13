<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br>
<c:set var="path" value="${pageContext.request.contextPath}" />
<h2 align="center">지자체 협약 지원 정보 서비스</h2>
<div style="text-align: center;">
	<a href="${path}/">[HOME]</a>
	<a href="${path}/csvInsert.do">[CSV 불러오기]</a>
</div>
<hr>