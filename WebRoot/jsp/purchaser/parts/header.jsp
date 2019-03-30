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
                <a href="/lyn-ssh/jsp/user/logout.do"><i class="icon_key_alt"></i> Log Out</a>
              </li>
             
            </ul>
          </li>
          <!-- user login dropdown end -->
        </ul>
        <!-- notificatoin dropdown end-->
      </div>
    </header>