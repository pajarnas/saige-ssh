<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
 <%@ include file="parts/resources.jsp" %>  
<title>主管后台界面 | 生产任务表</title>
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
										<th> 任务名</th>
										<th> 编号</th>
										
										<th> 任务优先级</th>
									
										<th> 任务阶段</th>
										<th> 产品名</th>
										<th> 数量</th>
										<th> 任务主管</th>
										<th>用料\采购人</th>
										<!-- <th>采购人 </th>
										<th>仓管人 </th>
										<th>加工人 </th> -->
										
									</tr>
									<c:forEach items="${ptasklist}" var="ptask">
										<tr>
											<td><c:out value="${ptask.name}" /></td>
											<td><c:out value="${ptask.id}" /></td>
										
											<td><span class="badge bg-${ptask.priority}"><c:out value="${ptask.priority}" /></span></td>
											
											<td><c:out value="${ptask.stage}" /></td>
											
											<td><c:out value="${ptask.product.name}" /></td>
											<td><c:out value="${ptask.quality}" /></td>
											<td><c:out value="${ptask.user.name}" /></td>
											<td><c:out value="${ptask.other_user.name}" /></td>
										 	<td>
											<div class="btn-group">
												<a class="btn btn-primary" href="insert.do?ptaskid=${ptask.id }" ><i class="icon_plus_alt2">接管</i></a>  
												</div>
											</td> 
										</tr>
									</c:forEach>


								</tbody>
							</table>
                            </section>
							
									
									<!-- modal for add user start <div aria-hidden="true" aria-labelledby="add_User"
										role="dialog" tabindex="-1" id="add_User_Modal"
										class="modal fade">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button aria-hidden="true" data-dismiss="modal"
														class="close" type="button">×</button>
													<h4 class="modal-title">User Insert Form</h4>
												</div>
												<div class="modal-body">

													<form id="add_user_form" role="form" action="addUser.do"
														method="post">
														<div class="form-group">
															<label for="exampleInputEmail1">Name</label> <input
																name="name" class="form-control" id="exampleInputEmail1"
																placeholder="Name">
														</div>
														<div class="form-group">
															<label for="exampleInputEmail1">City</label> <input
																name="city" class="form-control" id="exampleInputEmail1"
																placeholder="City">
														</div>
														<div class="form-group">
															<label for="exampleInputEmail1">Email address</label> <input
																name="email" type="email" class="form-control"
																id="exampleInputEmail1" placeholder="Email">
														</div>
														<div class="form-group">
															<label class="control-label col-lg-2" for="inputSuccess">Role</label>
															<div class="col-lg-10">
																<select onchange="this.form.submit()" name="role"
																	form="add_user_form" class="form-control m-bot15">
																	<option selected="selected">2</option>
																	<option>1</option>
																	<option>3</option>
																	<option>4</option>
																	<option>5</option>
																</select>
															</div>
														</div>
														<div class="form-group">
															<label for="exampleInputEmail1">Phone</label> <input
																name="phone" type="tel" class="form-control"
																id="exampleInputEmail1" placeholder="Phone Number">
														</div>
														<div class="form-group">
															<label for="exampleInputPassword1">Password</label> <input
																name="password" type="password" class="form-control"
																id="exampleInputPassword1" placeholder="Password">
														</div>

														<div class="form-group">
															<label class="control-label col-sm-4">Default
																Datepicker</label>
															<div class="col-sm-6">
																<input name="date" id="dp1" type="text" value="28-10-2013" size="16" class="form-control">
															</div>
														</div>
														<button type="submit" class="btn btn-primary">Submit</button>
													</form>


												</div>
											</div>
										</div>
									</div>-->
									

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
