<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
          <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
 <%@ include file="parts/resources.jsp" %>  
<title>主管后台界面</title>
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
	    
	     <!--main content start-->
    <section id="main-content">
      <section class="wrapper">
 
        <!-- page start-->
        <div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading tab-bg-info">
                <ul class="nav nav-tabs">
                  <li class="">
                    <a data-toggle="tab" href="#profile">
                                          <i class="icon-user"></i>
                                          个人主页
                                      </a>
                  </li>
                  <li class="">
                    <a data-toggle="tab" href="#edit-profile">
                                          <i class="icon-envelope"></i>
                                          编辑信息
                                      </a>
                  </li>
                </ul>
              </header>
              <div class="panel-body">
                <div class="tab-content">
                  <!-- profile -->
                  <div id="profile" class="tab-pane">
                    <section class="panel">
                     
                      <div class="panel-body bio-graph-info">
                        <h1>个人信息</h1>
                        <div class="row">
                          <div class="bio-row">
                            <p><span>姓名 </span>: ${user.name} </p>
                          </div>
                          <div class="bio-row">
                            <p><span>用户id </span>: ${user.id} </p>
                          </div>
                          <div class="bio-row">
                            <p><span>生日</span>:  ${user.date}</p>
                          </div>
                        
                          <div class="bio-row">
                            <p><span>职位 </span>:  ${user.role}</p>
                          </div>
                          <div class="bio-row">
                            <p><span>Email </span>: ${user.email}</p>
                          </div>
                          <div class="bio-row">
                            <p><span>手机号码 </span>:  ${user.phone}</p>
                          </div>
                        </div>
                      </div>
                    </section>
                    <section>
                      <div class="row">
                      </div>
                    </section>
                  </div>
                  <!-- edit-profile -->
                  <div id="edit-profile" class="tab-pane">
                    <section class="panel">
                      <div class="panel-body bio-graph-info">
                        <h1> 编辑信息</h1>
                        <form:form class="form-horizontal" id="update_user_form" role="form" method="post" action="updateUser.do" modelAttribute="user">
														<div class="form-group">
															<label for="exampleInputEmail1" class="col-lg-2 control-label">姓名</label> <div class="col-lg-10">
															<input name="name" value='${user.name}' class="form-control" id="nameInput1" placeholder="Name">
														</div></div>

														<div class="form-group">
															<label for="exampleInputEmail1" class="col-lg-2 control-label" >所在城市</label> 
															<div class="col-lg-10"><input
																name="city"  value='${user.city}' class="form-control" id="cityInput1"
																placeholder="City">
														</div></div>
														<div class="form-group">
															<label for="exampleInputEmail1" class="col-lg-2 control-label">电子邮件地址</label>
															<div class="col-lg-10">
                                                                     <input	name="email" value='${user.email}' type="email" class="form-control" id="emailInput1" placeholder="Email">
                                                             </div>
															 
														</div>
														<div class="form-group">
															<label class="control-label col-lg-2" for="inputSuccess">职位</label>
															<div class="col-lg-10">
																<select value="${user.role}" name="role"
																	form="update_user_form" class="form-control m-bot15">
																	<option selected="selected">${user.role}</option>
																<option>采购用料经理</option>
																	<option>仓库经理</option>
																	<option>销售经理</option>
																	<option>加工经理</option>
																	<option>主管经理</option>
																</select>
															</div>
														</div>
														<div class="form-group">
															<label class="col-lg-2 control-label" for="exampleInputEmail1">手机号码</label> <div class="col-lg-10"><input value='${user.phone}' name="phone" type="tel" class="form-control"
																id="phoneInput1" placeholder="Phone Number"></div>
														</div>
	<div class="form-group">
		<label for="exampleInputPassword1" class="col-lg-2 control-label">密码</label>
		<div class="col-lg-10"> 
		     <input value='${user.password}' name="password" class="form-control" id="passwordInput1" placeholder="Password">
	    </div>
   </div>
														
														<input name="id" type="hidden" value="${user.id}">
														 
                     
                                            
  
            <div class="form-group">
            
            <label for="exampleInputPassword1" class="col-lg-2 control-label">生日日期</label>   
            <div class="col-lg-10">
                <div class="input-group date" id="datetimepicker1">
                    <input value='${user.date}' type="text" class="form-control" name="date">
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
        </div>
    
      
       <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                              <button type="submit" class="btn btn-primary">保存</button>
                              
                            </div>
                          </div>
													</form:form>
                      </div>
                    </section>
                  </div>
                </div>
              </div>
            </section>
          </div>
        </div>

        <!-- page end-->
      </section>
    </section>
    <!--main content end-->
	    
    
  </section>
  <!-- container section end -->
  <%@ include file="parts/footer.jsp" %>
</body>
</html>