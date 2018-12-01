<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Kết quả</title>
</head>
<body>
	<jsp:useBean id="bean" scope="session" class="javabean.JavaBeanTraDanhBa" />
	<jsp:setProperty name="bean" property="*" />
<body bgcolor="#ffffff">
	<h3>Kết quả tra danh bạ theo yêu cầu của bạn :</h3>
	<%=
	bean.SearchAccount(request.getParameter("txtTenthuebao"), request.getParameter("txtSodienthoai"), request.getParameter("txtDiachi"))
	%>
	<p>
		<a href="index.html">Quay về trang chủ</a>
	</p>
</body>


</html>