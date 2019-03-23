
      <div class="toggle-nav">
        <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"><i class="icon_menu"></i></div>
      </div>

      <!--logo start-->
      <a class="logo">Lyn <span class="lite">车间管系统</span></a>
      <!--logo end-->

   

      <div class="top-nav notification-row">
           <!-- user login dropdown start -->
          <li class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="profile-ava">
                                <img alt="" src="img/<%=session.getAttribute("username")%>.jpg">
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
       
      </div>
   