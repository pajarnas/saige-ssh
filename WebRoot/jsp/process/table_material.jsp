<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
 <%@ include file="parts/resources.jsp" %>  
<title>主管后台界面 | 用料表</title>
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
							<header class="panel-heading"> 用料表 </header>

							<table class="table table-striped table-advance table-hover">
								<tbody>
									<tr>
										<th> 任务名</th>
										<th> 编号</th>
										<th> 所属生产任务</th>
										<th> 任务优先级</th>
									
										<th> 产品名</th>
										<th> 数量</th>
										<th> 用料\采购主管</th>
				
									</tr>
									<c:forEach items="${otasks}" var="otask">
										<tr>
											<td><c:out value="${otask.name}" /></td>
											<td><c:out value="${otask.id}" /></td>
										    <td><c:out value="${otask.ptask.id}" /></a></td>			
											<td><span class="badge bg-${otask.priority}"><c:out value="${otask.priority}" /></span></td>						
											<td><c:out value="${otask.product.name}" /></td>
											<td><c:out value="${otask.quality}" /></td>
											<td><c:out value="${otask.user.name}" /></td>
											
							
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
