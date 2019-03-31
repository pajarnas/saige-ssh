<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
	    
	    <!-- main content section start -->
	    <section id="main-content">
	    	<section class="wrapper">
			<div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading">
                生产任务表单
              </header>
       

              <div class="panel-body">
                <div class="form">
                  <form:form class="form-validate form-horizontal" id="add_task_form" method="post" action="/lyn-ssh/jsp/manager/updateTask.do" modelAttribute="ptask">
                    <div class="form-group ">
                      <label for="cname" class="control-label col-lg-2">生产任务名 <span class="required">*</span></label>
                      <div class="col-lg-10">
                        <input class="form-control" value="${task.name}" id="cname" name="name" minlength="5" type="text" required />
                      </div>
                    </div>
                    
                      <div class="form-group">
			            <label for="exampleInputPassword1" class="col-lg-2 control-label">任务日期</label>   
			            <div class="col-lg-10">
			                <div class="input-group date" id="datetimepicker1">
			                    <input value='${task.date}' type="text" class="form-control" name="date">
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			             </div>
        			</div>
           
                         <div class="form-group ">
	                      <label for="cname" class="control-label col-lg-2">任务优先级</label>
	                      <div class="col-lg-10">
	       
	                         <select name="priority" form="add_task_form" class="form-control m-bot15">
	                         											<option selected="selected">${task.priority}</option>
																		<option >medium</option>
	                                                                    <option>low</option>
	                                                                    <option>high</option>
	                                                                    <option>urgent</option>
	                         </select>
	                      </div>
	                    </div>
                      
            			<div class="form-group ">
	                      <label for="cname" class="control-label col-lg-2">任务进度</label>
	                      <div class="col-lg-10">
	       
	                        <select name="progress" form="add_task_form" class="form-control m-bot15">
	                       										 <option selected="selected">${task.progress}</option>
	      															<option> 未开始</option>
	                                                                    <option>前期完成</option>
	                                                                    <option>中期完成</option>
	                                                                    <option>即将完成</option>
	                                                                    <option>完成</option>
																		
																	</select>
									<span class="help-block">指明任务负责人对当前任务的完成度</span>		
															
	                      </div>
	                    </div>
                    	
            			<input name="id" type="hidden" value="${task.id}">
                    
                       <div class="form-group ">
                      <label for="cname" class="control-label col-lg-2">生产数量 <span class="required">*</span></label>
                      <div class="col-lg-10">
                        <input class="form-control " id="cname" type="number" name="quality" value="${task.quality }" required />
                      </div>
                    </div>
                    
                      
	                  
                  
	    
	                    
	                       <div class="form-group ">
	                      <label for="cname" class="control-label col-lg-2">生产产品</label>
	                      <div class="col-lg-10">
	       
	                        <select name="productid" form="add_task_form" class="form-control m-bot15">
	                         <option selected="selected" value="${task.product.id}" />${task.product.name} -- ${task.product.product_type}</option>
	      										<c:forEach items="${products}" var="product">
														 <option value="${product.id}" />${product.name}--${product.product_type}</option>
												</c:forEach>
							</select>
							
	                      </div>
	                    </div>
	                    	
	                    
	          
                 
                   
                    <div class="form-group">
                      <div class="col-lg-offset-2 col-lg-10">
                        <button class="btn btn-primary" type="submit">提交</button>
                  
                      </div>
                    </div>
                  </form:form>
                </div>

              </div>
            </section>
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