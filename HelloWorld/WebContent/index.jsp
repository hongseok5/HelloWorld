<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap.css" rel="stylesheet" type="text/css">
<script src="jquery-3.2.1.js"></script>
<script src="jquery-ui.js"></script>
<script type="text/javascript" src="bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>
<div class="container">
	<div class="row" class="col-xs-12">
		<form action="${pageContext.servletContext.contextPath }/hello" method="post" enctype="multipart/form-data">
			<p>이미지를 올려주세요!</p>
			<input type="file" name="uploadFile"><br>
			제 목  : <input type="text" name="title"><br>
			올린이 : <input type="text" name="user"><br>
			<input type="submit" value="submit">
		</form>
	</div>
	<div class="row">
		<div class="col-xs-4" align="center" >
			로그인
		</div>
		<div class="col-xs-4" align="center">
			리스트
		</div>
		<div class="col-xs-4" align="center">
			정확도
		</div>
	</div>
</div>
</body>
</html>