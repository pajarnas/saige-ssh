<<<<<<< HEAD
<section class="panel">
                      <div class="panel-body bio-graph-info">
                        <h1> 编辑信息</h1>
                        <form class="form-horizontal" id="update_user_form" role="form" method="post" action="updateUser.do">
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
																<select value="${user.email}" name="role"
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
													</form>
                      </div>
                    </section>
=======
>>>>>>> branch 'master3' of https://github.com/pajarnas/saige-ssh.git
