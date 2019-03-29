<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
 <%@ include file="parts/resources.jsp" %>  
<title>主管后台界面 | 用户表</title>
</head>
<body>

  <!-- container section start -->
  <section id="container" class="">

		<!-- header start -->
	    <%@ include file="parts/header.jsp" %>  
	    <!--header end-->
	
	    <!--sidebar start-->
	    <%@ include file="parts/aside.jsp" %>  
	    <!--sidebar end-->

	    <!-- main content section start -->
	    <section id="main-content">
	    	<section class="wrapper">

<div class="row">
					<div class="col-lg-12">
						<section class="panel">
						
							<header class="panel-heading"> 生产任务表 </header>

							<table class="table table-striped table-advance table-hover">
											<tbody>
									<tr>
										<th><i class="icon_profile"></i> 姓名</th>
										<th><i class="icon_calendar"></i> 生日</th>
										<th><i class="icon_mail_alt"></i> Email</th>
										<th><i class="icon_pin_alt"></i> 所在城市</th>
										<th><i class="icon_mobile"></i> 手机号码</th>
										<th><i class="icon_toolbox"></i> 职位</th>
										<th><i class="icon_cogs"></i> 操作</th>
									</tr>
									<c:forEach items="${users}" var="user">
										<tr>
											<td><c:out value="${user.name}" /></td>
											<td><c:out value="${user.date}" /></td>
											<td><c:out value="${user.email}" /></td>
											<td><c:out value="${user.city}" /></td>
											<td><c:out value="${user.phone}" /></td>
											<td><c:out value="${user.role}" /></td>
											<td>
												<div class="btn-group">
													<a class="btn btn-primary" href="#add_User_Modal"
														data-toggle="modal"><i class="icon_plus_alt2"></i></a>  
														<a class="btn btn-success" href="updateUser.do?id=${user.id}" data-toggle="modal"><i class="icon_pencil-edit_alt"></i></a> 
														<a
														class="btn btn-danger"
														href="delUser.do?id=${user.id}"><i
														class="icon_close_alt2"></i></a>
												</div>
											</td>
										</tr>
									</c:forEach>


								</tbody>
							</table>
                            </section>
							
									
									<!-- modal for add user start -->
									<div aria-hidden="true" aria-labelledby="add_User"
										role="dialog" tabindex="-1" id="add_User_Modal"
										class="modal fade">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button aria-hidden="true" data-dismiss="modal"
														class="close" type="button">×</button>
													<h4 class="modal-title">新增用户表单</h4>
												</div>
												<div class="modal-body">

													<form id="add_user_form" role="form" action="addUser.do"
														method="post">
														<div class="form-group">
															<label for="exampleInputEmail1">姓名</label> <input
																name="name" class="form-control" id="exampleInputEmail1"
																placeholder="Name">
														</div>
														<div class="form-group">
															<label for="exampleInputEmail1">城市</label> <input
																name="city" class="form-control" id="exampleInputEmail1"
																placeholder="City">
														</div>
														<div class="form-group">
															<label for="exampleInputEmail1">Email 地址</label> <input
																name="email" type="email" class="form-control"
																id="exampleInputEmail1" placeholder="Email">
														</div>
														<div class="form-group">
															<label class="control-label col-lg-2" for="inputSuccess">职位</label>
															<div class="col-lg-10">
																<select name="role"
																	form="add_user_form" class="form-control m-bot15">
																	
																<option>采购用料经理</option>
																	<option>仓库经理</option>
																	<option>销售经理</option>
																	<option>加工经理</option>
																	<option>主管经理</option>
																</select>
															</div>
														</div>
														<div class="form-group">
															<label for="exampleInputEmail1">手机号码</label> <input
																name="phone" type="tel" class="form-control"
																id="exampleInputEmail1" placeholder="Phone Number">
														</div>
														<div class="form-group">
															<label for="exampleInputPassword1">密码</label> <input
																name="password" type="password" class="form-control"
																id="exampleInputPassword1" placeholder="Password">
														</div>
      <div class="form-group">
            
            <label for="exampleInputPassword1" class="control-label col-sm-4 control-label">生日日期</label>   
            <div class="col-sm-6">
                <div class="input-group date" id="datetimepicker1">
                    <input value='${user.date}' type="text" class="form-control" name="date">
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
        </div>
													
														<button type="submit" class="btn btn-primary">提交</button>
													</form>


												</div>
											</div>
										</div>
									</div>
									

									<!-- modal for add user end -->

					

											
												</div>
											</div>



			</section>
		</section>
	     <!--  main content section end -->

    
  </section>
  <!-- container section end -->
  <%@ include file="parts/footer.jsp" %>
</body>
</html>
