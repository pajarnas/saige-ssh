<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	   <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="cn">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="LYN车间管理系统">
<meta name="author" content="Yaning Liu刘雅宁">
<meta name="keyword" content="Reusable, Standard, Effective可复用、标准化、高效率">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="shortcut icon" href="img/favicon.png">

  <title>失败</title>

  <!-- Bootstrap CSS -->
  <link href="/lyn-ssh/css/bootstrap.min.css" rel="stylesheet">
  <!-- bootstrap theme -->
  <link href="/lyn-ssh/css/bootstrap-theme.css" rel="stylesheet">
  <!--external css-->
  <!-- font icon -->
  <link href="/lyn-ssh/css/elegant-icons-style.css" rel="stylesheet" />
  <link href="/lyn-ssh/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles -->
  <link href="/lyn-ssh/css/style.css" rel="stylesheet">
  <link href="/lyn-ssh/css/style-responsive.css" rel="stylesheet" />


</head>

<body>
  <div class="page-404">
    <p class="text-404">Opps!</p>
     <h2>提交失败!</h2>
	<h3>以下采购任务未完成，无法提交，手动设置进度！</h3>
	<table>
	<tr>
		<th>任务名</th>
		<th>任务编号</th>
		<th>进度</th>
		
	</tr>
	<tr>
	<c:forEach items="${otasks}" var="otask">
		<td><c:out value="${otask.name}" /></td>
		<td><c:out value="${otask.id}" /></td>
		<td><c:out value="${otask.progress}" /></td>
		</c:forEach>
	</tr>
	</table>
    <a href="pur_table.do">返回</a>

  </div>


</body>

</html>
