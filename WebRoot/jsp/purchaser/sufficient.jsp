<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
 <%@ include file="parts/resources.jsp" %>  
<title>主管后台界面 | 用料任务表</title>
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
						
							<header class="panel-heading"> 用料任务表 </header>

							<table class="table table-striped table-advance table-hover">
								<tbody>
									<tr>
										<th> 任务名</th>
								
										<th> 产品名</th>
										<th> 数量</th>
									    <th>库存</th>
										
										<th>操作</th>
								
										
									</tr>
								
										<tr>
											<td><c:out value="${otask.name}" /></td>
						
											<td><c:out value="${otask.product.name}" /></td>
											<td><c:out value="${otask.quality}" /></td>
											<td><c:out value="${result}" /></td>
											
										 	<td>
											<div class="btn-group">
												<a class="btn btn-primary" href="insert.do?ptaskid=${otask.ptask.id }&type=采购" >采购</a>  
												
												</div>
											</td> 
										</tr>
							

								</tbody>
							</table>
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
