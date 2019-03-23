<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE HTML>    
<html>    

<head>    
      <%@ include file="refers.jsp" %>  
    
      <%@ include file="meta.jsp" %>  
    
    
	<title>基本界面</title>    
	
</head> 
   
<body>    
   
        <header class="header dark-bg"><%@ include file="header.jsp" %> </header>  
        
        <aside><%@ include file="menu.jsp" %></aside>    
      
     	<!-- container section start -->
        <section id="container" class="">
   		 
        		  
        		  <div class="row">
          				<div class="col-lg-12">
		         		<!-- form start -->  
		        		<section class="panel">
			               <div class="panel-body">
			                   <%@ include file="form.jsp" %>
			               </div>
		            	</section>
		           	    <!-- form end --> 
		         	   </div>
     		     </div>
        		 
            	<div class="row">
         			 <div class="col-lg-12">
            			<section class="panel">
			            	
			            	<table class="table table-striped table-advance table-hover">
                <tbody>
                
                  <tr>
                    <th><i class="icon_profile"></i> Full Name</th>
                    <th><i class="icon_calendar"></i> Date</th>
                    <th><i class="icon_mail_alt"></i> Email</th>
                    <th><i class="icon_pin_alt"></i> City</th>
                    <th><i class="icon_mobile"></i> Mobile</th>
                    <th><i class="icon_cogs"></i> Action</th>
                  </tr>
                  
                  <tr>
                    <td>Angeline Mcclain</td>
                    <td>2004-07-06</td>
                    <td>dale@chief.info</td>
                    <td>Rosser</td>
                    <td>176-026-5992</td>
                    <td>
                      <div class="btn-group">
                        <a class="btn btn-primary" href="#"><i class="icon_plus_alt2"></i></a>
                        <a class="btn btn-success" href="#"><i class="icon_check_alt2"></i></a>
                        <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
                      </div>
                    </td>
                  </tr>
                 
                </tbody>
              </table>
			            
           				 </section>
          			</div>
        		</div>
        		
        		
        		<div class="text-right"><%@ include file="footer.jsp" %></div>    
        
        </section>
        <!-- container section end --> 
   
</body>  
  
</html>    

