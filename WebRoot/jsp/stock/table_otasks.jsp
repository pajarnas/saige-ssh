<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
 <%@ include file="parts/resources.jsp" %>  
<title>主管后台界面 | 出入库请求表</title>
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
						    <div class="alert alert-info fade in">
                  <button data-dismiss="alert" class="close close-sm" type="button">
                                      <i class="icon-remove"></i>
                                  </button>
                  <strong>注意!</strong> 拒绝验收：会撤回所有已入出库的产品数量； 提交：对生产任务下所有的子任务都有效。提交成功不可修改。
                  
                </div>
							<header class="panel-heading"> 入出库请求表 </header>

							<table class="table table-striped table-advance table-hover">
								<tbody>
									<tr>
										<th> 任务名</th>
										<th> 编号</th>
										<th> 所属生产任务</th>
										<th> 任务优先级</th>
									 	<th>任务类型 </th>
										<th> 产品名</th>
										<th> 验收数量</th>
										<th>验收状态</th>
										<th> 主管</th>
										
										<th>操作</th>
									
									</tr>
									<c:forEach items="${otasks}" var="otask">
										<tr>
											<td><c:out value="${otask.name}" /></td>
											<td><c:out value="${otask.id}" /></td>
										<td><a href="material_table.do?ptaskid=${otask.ptask.id}"><c:out value="${otask.ptask.id}" /></a></td>
									
											<td><span class="badge bg-${otask.priority}"><c:out value="${otask.priority}" /></span></td>
											
											<td><c:out value="${otask.type}" /></td>
											
											<td><c:out value="${otask.product.name}" /></td>
											<td><c:out value="${otask.quality}" /></td>
												<td><c:out value="${otask.stocked}" /></td>
											<td><c:out value="${otask.user.name}" /></td>
											
										 	<td>
											<div class="btn-group">
												<a class="btn btn-primary" href="accept.do?otaskid=${otask.id }" >验收</a>  
												<a class="btn btn-success" href="submit.do?ptaskid=${otask.ptask.id }" >提交</a>
												<a class="btn btn-danger" href="reject.do?otaskid=${otask.id }" >拒绝</a>
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
