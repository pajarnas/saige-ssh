<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE HTML>    
<html>    

<head>    

    <tiles:insertAttribute name="refers" ignore="true" />
    
    <tiles:insertAttribute name="meta" ignore="true" />
    
	<title><tiles:insertAttribute name="title" ignore="true" /></title>    
	
</head> 
   
<body>    
   
        <header class="header dark-bg"><tiles:insertAttribute name="header" /></header>  
        
        <aside><tiles:insertAttribute name="menu" /></aside>    
      
     	<!-- container section start -->
        <section id="container" class="">
   		 
        		  
        		  <div class="row">
          				<div class="col-lg-12">
		         		<!-- form start -->  
		        		<section class="panel">
			               <div class="panel-body">
			                   <tiles:insertAttribute name="form" />
			               </div>
		            	</section>
		           	    <!-- form end --> 
		         	   </div>
     		     </div>
        		 
            	<div class="row">
         			 <div class="col-lg-12">
            			<section class="panel">
			            	<table class="table table-striped table-advance table-hover">
			            		<tiles:insertAttribute name="table" />
			            	</table>
           				 </section>
          			</div>
        		</div>
        		
        		
        		<div class="text-right"><tiles:insertAttribute name="footer" /></div>    
        
        </section>
        <!-- container section end --> 
   
</body>  
  
</html>    

