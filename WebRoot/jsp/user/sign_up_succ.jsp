<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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

  <title>成功</title>

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
    <p class="text-404">成功!</p>
     <h2>欢迎 ${user.name}! </h2>
     <h2>请记住您的用户id： ${user.id}! </h2>
    <a href="./sign_in.jsp">返回主页</a></p>
  </div>


</body>

</html>
