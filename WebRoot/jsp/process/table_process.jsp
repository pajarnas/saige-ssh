<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
 <%@ include file="parts/resources.jsp" %>  
<title>主管后台界面 | 加工任务表</title>
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
                  <strong>注意!</strong>加工任务完成之后，可提交给仓库管理员。对应的生产任务进入待加工入库阶段。提交后不可修改。
                </div>
							<header class="panel-heading"> 加工任务表 </header>

							<table class="table table-striped table-advance table-hover">
								<tbody>
									<tr>
										<th> 任务名</th>
										<th> 编号</th>
										<th> 所属生产任务</th>
										<th> 任务优先级</th>
									
										<th> 产品名</th>
										<th> 数量</th>
										<th> 加工主管</th>
											<th>进度</th>
										<th>操作</th>
										<!-- <th>采购人 </th>
										<th>仓管人 </th>
										<th>加工人 </th> -->
										
									</tr>
									<c:forEach items="${otasks}" var="otask">
										<tr>
											<td><c:out value="${otask.name}" /></td>
											<td><c:out value="${otask.id}" /></td>
										<td><a href="process_table.do?ptaskid=${otask.ptask.id}"><c:out value="${otask.ptask.id}" /></a></td>
											<td><span class="badge bg-${otask.priority}"><c:out value="${otask.priority}" /></span></td>
											<td><c:out value="${otask.product.name}" /></td>
											<td><c:out value="${otask.quality}" /></td>
											<td><c:out value="${otask.user.name}" /></td>
																				<td>
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"><c:out value="${otask.progress}" /><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <li><a href="updateTaskStatus.do?id=${otask.id}&status=0">未开始</a></li>
                      <li><a href="updateTaskStatus.do?id=${otask.id}&status=1">前期完成</a></li>
                      <li><a href="updateTaskStatus.do?id=${otask.id}&status=2">中期完成</a></li>
                      <li><a href="updateTaskStatus.do?id=${otask.id}&status=3">即将完成</a></li>
                      <li><a href="updateTaskStatus.do?id=${otask.id}&status=4">完成</a>
                      
                    </ul>
                 </td>
										 	<td>
											<div class="btn-group">
									
												<a class="btn btn-success" href="submit.do?ptaskid=${otask.ptask.id }" > 提交</a>  
												<a class="btn btn-danger" href="delTask.do?id=${otask.id }" > 删除</a>  
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
