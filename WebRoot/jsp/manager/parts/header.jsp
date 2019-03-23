<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<header class="header dark-bg">
      <div class="toggle-nav">
        <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"><i class="icon_menu"></i></div>
      </div>

      <!--logo start-->
      <a href="/lyn-ssh/jsp/manager/manager_index.jsp" class="logo">Lyn <span class="lite">车间管理系统</span></a>
      <!--logo end-->

   

      <div class="top-nav notification-row">
        <!-- notificatoin dropdown start-->
        <ul class="nav pull-right top-menu">

          <!-- task notificatoin start -->
          <li id="task_notificatoin_bar" class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <i class="icon-task-l"></i>
                            <span class="badge bg-important">${bars_len}</span>
                        </a>
            <ul class="dropdown-menu extended tasks-bar">
              <div class="notify-arrow notify-arrow-blue"></div>
              <li>
                <p class="blue">You have ${bars_len} pending letter</p>
              </li>
              <c:forEach items="${bars}" var="bar">
              <li>
                <a href="#">
                  <div class="task-info">
                    <div class="desc">  ${bar.name} </div>
                    <div class="percent">${bar.width}%</div>
                  </div>
                  <div class="progress progress-striped active">
                    <div class="progress-bar progress-bar-${bar.color}" role="progressbar" aria-valuenow="${bar.width}" aria-valuemin="0" aria-valuemax="100" style="width: ${bar.width}%">
                      <span class="sr-only">${bar.width}% Complete</span>
                    </div>
                  </div>
                </a>
              </li>
					 </c:forEach>				
              
                 
                
              <li class="external">
                <a href="#">See All Tasks</a>
              </li>
            </ul>
          </li>
          <!-- task notificatoin end -->
        
          
          <!-- user login dropdown start-->
          <li class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="profile-ava">
                                <img alt="" src="/lyn-ssh/img/avatar1_small.jpg">
                            </span>
                            <span class="username"><%= session.getAttribute("username") %></span>
                            <b class="caret"></b>
                        </a>
            <ul class="dropdown-menu extended logout">
          
              <li>
                <a href="/lyn-ssh/user/logout.do"><i class="icon_key_alt"></i> Log Out</a>
              </li>
             
            </ul>
          </li>
          <!-- user login dropdown end -->
        </ul>
        <!-- notificatoin dropdown end-->
      </div>
    </header>