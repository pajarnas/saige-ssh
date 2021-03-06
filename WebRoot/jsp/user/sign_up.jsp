<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="com.lyn.model.enums.Role" %>
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

  <title>注册</title>

  <!-- Bootstrap CSS -->
  <link href="/lyn-ssh/css/bootstrap.min.css" rel="stylesheet">
  <!-- bootstrap theme -->
  <link href="/lyn-ssh/css/bootstrap-theme.css" rel="stylesheet">
  <!--external css-->
  <!-- font icon -->
  <link href="/lyn-ssh/css/elegant-icons-style.css" rel="stylesheet" />
  <link href="/lyn-ssh/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles -->
  <link href="/lyn-ssh/css/style.css" rel="stylesheet">
  <link href="/lyn-ssh/css/style-responsive.css" rel="stylesheet" />

</head>

<body class="login-img3-body">

  <div class="container">
	
    <form name="sign_up_form" class="login-form" action="signUp.do" method="post">
      <div class="login-wrap">
        <p class="login-img"><i class="icon_lock_alt"></i></p>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_profile"></i></span>
          <input name="name" type="text" class="form-control" placeholder="姓名" autofocus>
        </div>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_profile"></i></span>
          <input name="email" type="text" class="form-control" placeholder="邮箱" autofocus>
        </div>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon_key_alt"></i></span>
          <input name="password" type="password" class="form-control" placeholder="密码">
          <input name="role" type="hidden" value="待分配">
        </div>
     <span class="pull-right"> <a href="/lyn-ssh/jsp/user/sign_in.jsp"> 已有账户？</a></span>
         <button class="btn btn-info btn-lg btn-block" type="submit">注册</button>
    
 </form>
  </div>


</body>

</html>
