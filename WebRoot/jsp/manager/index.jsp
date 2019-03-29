<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
            <!--Project Activity start-->
            <section class="panel">
              <div class="panel-body progress-panel">
                <div class="row">
                  <div class="col-lg-8 task-progress pull-left">
                    <h1>待办</h1>
                  </div>
                  <div class="col-lg-4">
                    <span class="profile-ava pull-right">
                                        <img alt="" class="simple" src="/lyn-ssh/img/avatar1_small.jpg">
                                          ${user.name}
                                </span>
                  </div>
                </div>
              </div>
              <table class="table table-hover personal-task">
                <tbody>
                   <c:forEach items="${bars}" var="bar">
                  <tr>
                    <td>${bar.date}</td>
                    <td>
                      ${bar.name}
                    </td>
                    <td>
                      <span class="badge bg-${bar.priority}"><c:out value="${bar.priority}" /></span>
                    </td>
             
                    <td>
                    <div class="progress progress-striped active progress-lg ">
                       
                
                    <div class="progress-bar progress-bar-${bar.color}" role="progressbar" aria-valuenow="${bar.width}" aria-valuemin="0" aria-valuemax="100" style="width: ${bar.width}%">
                      
                    </div>
                  </div>
                
                    </td>
                    <td>
                      ${bar.user_name}
                    </td>
                  </tr>
                     </c:forEach>
                </tbody>
              </table>
            </section>
            <!--Project Activity end-->
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