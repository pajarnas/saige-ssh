<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
 <%@ include file="parts/resources.jsp" %>  
<title>主管后台界面 | 加工任务表单</title>
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

				<!-- Basic Forms & Horizontal Forms-->
				  <div class="row">
          <div class="col-lg-12">
            <section class="panel">
              <header class="panel-heading">
               加工任务表单
              </header>
       

              <div class="panel-body">
              <div class="alert alert-info fade in">
                  <button data-dismiss="alert" class="close close-sm" type="button">
                                      <i class="icon-remove"></i>
                                  </button>
                  <strong>注意!</strong> 作为加工管理员，你需要根据用料单，填写生产任务所要求之成品，加工任务完成之后，提交给仓库管理员。用料单一旦填写完成则提交给仓库管理员，职责随之完成。
                </div>
                <div class="form">
                  <form:form class="form-validate form-horizontal" id="add_task_form" method="post" action="insert.do" modelAttribute="otask">
                    <div class="form-group ">
                      <label for="cname" class="control-label col-lg-2">任务名 <span class="required">*</span></label>
                      <div class="col-lg-10">
                        <input class="form-control" id="cname" name="name" minlength="5" type="text" required />
                      </div>
                    </div>
                    
                         <div class="form-group ">
                      <label for="cname" class="control-label col-lg-2">所属生产任务 <span class="required">*</span></label>
                      <div class="col-lg-10">
                        <input class="form-control " id="cname" name="ptaskid" value="${ptaskid}" readonly required />
                      </div>
                    </div>
                    
                      <div class="form-group">
			            <label for="exampleInputPassword1" class="col-lg-2 control-label">任务日期</label>   
			            <div class="col-lg-10">
			                <div class="input-group date" id="datetimepicker1">
			                    <input value='${user.date}' type="text" class="form-control" name="date">
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
																		<option selected="selected">medium</option>
	                                                                    <option>low</option>
	                                                                    <option>high</option>
	                                                                    <option>urgent</option>
	                         </select>
	                      </div>
	                    </div>
                      
            	
	                       <div class="form-group ">
	                      <label for="cname" class="control-label col-lg-2">成品<span class="required">*</span></label>
	                      <div class="col-lg-10">
	       
	                        <select name="productid" form="add_task_form" class="form-control m-bot15">
	                        
	      										<c:forEach items="${products}" var="product">
														 <option value="${product.id}" />${product.name}-${product.product_type}</option>
												</c:forEach>
							</select>
							<span class="help-block">产品类型任务请选择成品类型。</span>
							
	                      </div>
	                    </div>
                    
                       <div class="form-group ">
                      <label for="cname" class="control-label col-lg-2">数量 <span class="required">*</span></label>
                      <div class="col-lg-10">
                        <input class="form-control " id="cname" min="1" step="1" type="number" name="quality" required />
                        
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
    <!--main content end-->
  <!-- container section end -->
  <%@ include file="parts/footer.jsp" %>
</body>
</html>
