<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>DBA page</title>
</head>
<body>
Dear <strong>${user}</strong>, Welcome to DBA Page.
<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>