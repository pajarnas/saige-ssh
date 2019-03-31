<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
 <%@ include file="parts/resources.jsp" %>  
<title>主管后台界面 | 总任务表</title>
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
										<th> 任务时间</th>
										<th> 任务优先级</th>
										<th>任务进度</th>
									
										<th> 产品名</th>
										<th> 数量</th>
										<th> 任务负责人</th>
										
										<th>操作</th>
										<!-- <th>采购人 </th>
										<th>仓管人 </th>
										<th>加工人 </th> -->
										
									</tr>
									<c:forEach items="${ptasks}" var="ptask">
										<tr>
											<td><c:out value="${ptask.name}" /></td>
											<td><c:out value="${ptask.id}" /></td>
											<td><c:out value="${ptask.date}" /></td>
											<td><span class="badge bg-${ptask.priority}"><c:out value="${ptask.priority}" /></span></td>
											<td><c:out value="${ptask.progress}" /></td>
										
											
											<td><c:out value="${ptask.product.name}" /></td>
											<td><c:out value="${ptask.quality}" /></td>
											<td><c:out value="${ptask.user.name}" /></td>
											
										 	<td>
											<div class="btn-group">
												
												 
												<a class="btn btn-danger" href="delTask.do?id=${ptask.id}"><i class="icon_close_alt2"></i></a>
												</div>
											</td> 
										</tr>
									</c:forEach>


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
