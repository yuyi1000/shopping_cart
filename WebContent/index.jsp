<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Twitter Bootstrap shopping cart</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap styles -->
    <link href="assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- Customize styles -->
    <link href="style.css" rel="stylesheet"/>
    <!-- font awesome styles -->
	<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet">
		<!--[if IE 7]>
			<link href="css/font-awesome-ie7.min.css" rel="stylesheet">
		<![endif]-->

		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

	<!-- Favicons -->
    <link rel="shortcut icon" href="assets/ico/favicon.ico">
      <style>
      .error{
          color:red;
      }
      </style>  
  </head>
<body>
<!-- 
	Upper Header Section 
-->
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="topNav">
		<div class="container">
			<div class="alignR">
				<div class="pull-left socialNw">
					<a href="#"><span class="icon-twitter"></span></a>
					<a href="#"><span class="icon-facebook"></span></a>
					<a href="#"><span class="icon-youtube"></span></a>
					<a href="#"><span class="icon-tumblr"></span></a>
				</div>
				
				<% if (session == null) {
					session = request.getSession();
				}
					if (session.getAttribute("userid") == null) {
						
					%>
				<a href="register.jsp"><span class="icon-edit"></span> Free Register </a> 
				<a href="#"><span class="icon-envelope"></span> Contact us</a>					
					
					
					
				<% } else { %>
				<a class="active" href="index.jsp"> <span class="icon-home"></span> Home</a>
		<!-- 		<a href="#"><span class="icon-user"></span> My Account</a>  -->
				
				
				

				<%@ page import="java.util.List, java.util.ArrayList"
						 import="models.CartBean" %>
				
				<% List<CartBean> carts = (ArrayList<CartBean>) session.getAttribute("carts"); %>
				
				<a href="display"><span class="icon-shopping-cart"></span> 
				<% if (carts == null) { %>
				0 item(s)
				
				<% } else { %>
				<%= carts.size() %>  item(s)
				<% } %>
				</a>
				
				<% } %>
				
				
			</div>
		</div>
	</div>
</div>

<!--
Lower Header Section 
-->
<div class="container">
<div id="gototop"> </div>
<header id="header">
<div class="row">



</div>
</header>

<!--
Navigation Bar Section 
-->
<div class="navbar">
	  <div class="navbar-inner">
		<div class="container">
		  <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		  </a>
		  <div class="nav-collapse">
<!-- 			<ul class="nav">
			  <li class="span2 active"><a href="index.jsp">Home	</a></li>
			  <li class="span2"><a href="#">List View</a></li>
			  <li class="span2"><a href="#">Feedback</a></li>
			</ul> -->
			
			
			
			<div class="navbar-search pull-left">
			  <input id="search-bar" type="text" placeholder="Search" class="search-query span2">
			</div>
			
			
			<ul class="nav pull-right">
			
			<% if (session.getAttribute("validUser") != null && !(Boolean) session.getAttribute("validUser")) { %>
			<li class="error"> Invalid credentials </li>
			<% } %>			
			
			
			<% String firstName = (String) session.getAttribute("firstname");
				if (firstName == null) {
			%>
			
			<li class="dropdown">
				<a data-toggle="dropdown" class="dropdown-toggle" href="#"><span class="icon-lock"></span> Login <b class="caret"></b></a>
				<div class="dropdown-menu">
				<form class="form-horizontal loginFrm" action="login" method="post">
				  <div class="control-group">
					<input type="text" class="span2" id="inputEmail" placeholder="Email" name="email">
				  </div>
				  <div class="control-group">
					<input type="password" class="span2" id="inputPassword" placeholder="Password" name="password">
				  </div>
				  <div class="control-group">
					<label class="checkbox">
					<input type="checkbox"> Remember me
					</label>
					<button type="submit" class="shopBtn btn-block">Sign in</button>
				  </div>
				</form>
				</div>
			</li>
			
			<% }
				else
				{
				%>
			
			<li class="dropdown">
				<a data-toggle="dropdown" class="dropdown-toggle" href="#"> Welcome, <%= firstName %> <b class="caret"></b></a>
				<div class="dropdown-menu">
				<form class="form-horizontal loginFrm" action="logout" method="post">
				  <div class="control-group">
					<button type="submit" class="shopBtn btn-block">Log out</button>
				  </div>
				</form>
				</div>
			</li>				
				
				
			<%
				}
			%>
			
			</ul>
			
			
			
		  </div>
		</div>
	  </div>
	</div>
<!-- 
Body Section 
-->
	<div class="row">
<div id="sidebar" class="span3">
<div id="category-nav" class="well well-small" style="margin-top: 12em;">
	<ul class="nav nav-list">
		<li><a href="products?category=Green_tea"><span class="icon-chevron-right"></span>Green Tea</a></li>
		<li><a href="products?category=Black_tea"><span class="icon-chevron-right"></span>Black Tea</a></li>
		<li><a href="products?category=Oolong_tea"><span class="icon-chevron-right"></span>Oolong Tea</a></li>
		<li><a href="products?category=Herbal_tea"><span class="icon-chevron-right"></span>Herbal Tea</a></li>
		<li><a href="products?category=all"><span class="icon-chevron-right"></span>See All Tea</a></li>
		<li style="border:0"> &nbsp;</li>
		<!-- <li> <a class="totalInCart" href="cart.html"><strong>Total Amount  <span class="badge badge-warning pull-right" style="line-height:18px;">$448.42</span></strong></a></li> -->
	</ul>
</div>

			  <!-- <div class="well well-small alert alert-warning cntr">
				  <h2>50% Discount</h2>
				  <p> 
					 only valid for online order. <br><br><a class="defaultBtn" href="#">Click here </a>
				  </p>
			  </div>
			  <div class="well well-small" ><a href="#"><img src="assets/img/paypal.jpg" alt="payment method paypal"></a></div> -->
			
			<!-- <a class="shopBtn btn-block" href="#">Upcoming products <br><small>Click to view</small></a>
			<br>
			<br>
			<ul class="nav nav-list promowrapper">
			<li>
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<img src="assets/img/bootstrap-ecommerce-templates.png" alt="bootstrap ecommerce templates">
				<div class="caption">
				  <h4><a class="defaultBtn" href="product_details.html">VIEW</a> <span class="pull-right">$22.00</span></h4>
				</div>
			  </div>
			</li>
			<li style="border:0"> &nbsp;</li>
			<li>
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<img src="assets/img/shopping-cart-template.png" alt="shopping cart template">
				<div class="caption">
				  <h4><a class="defaultBtn" href="product_details.html">VIEW</a> <span class="pull-right">$22.00</span></h4>
				</div>
			  </div>
			</li>
			<li style="border:0"> &nbsp;</li>
			<li>
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<img src="assets/img/bootstrap-template.png" alt="bootstrap template">
				<div class="caption">
				  <h4><a class="defaultBtn" href="product_details.html">VIEW</a> <span class="pull-right">$22.00</span></h4>
				</div>
			  </div>
			</li>
		  </ul> -->

	</div>
	<div class="span9">
	<div class="well np">
		<div id="myCarousel" class="carousel slide homCar">
            <div class="carousel-inner">
			  <div class="item">
                <img style="width:100%" src="assets/img/tea_art3.jpg" alt="bootstrap ecommerce templates">
                <!-- <div class="carousel-caption">
                      <h4>Bootstrap shopping cart</h4>
                      <p><span>Very clean simple to use</span></p>
                </div> -->
              </div>
			  <div class="item">
                <img style="width:100%" src="assets/img/tea_art1.jpg" alt="bootstrap ecommerce templates">
                <!-- <div class="carousel-caption">
                      <h4>Bootstrap Ecommerce template</h4>
                      <p><span>Highly Google seo friendly</span></p>
                </div> -->
              </div>
			  <div class="item active">
                <img style="width:100%" src="assets/img/tea_art2.jpg" alt="bootstrap ecommerce templates">
                <!-- <div class="carousel-caption">
                      <h4>Twitter Bootstrap cart</h4>
                      <p><span>Very easy to integrate and expand.</span></p>
                </div> -->
              </div>
              <div class="item">
                <img style="width:100%" src="assets/img/tea_art4.jpg" alt="bootstrap templates">
                <!-- <div class="carousel-caption">
                      <h4>Bootstrap templates integration</h4>
                      <p><span>Compitable to many more opensource cart</span></p>
                </div> -->
              </div>
            </div>
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
          </div>
        </div>
<!--
New Products
-->
	<div class="well well-small">
	<h3>New Products </h3>
	<hr class="soften"/>
		<div class="row-fluid">
		<div id="newProductCar" class="carousel slide">
            <div class="carousel-inner">
			<div class="item active">
			  <ul class="thumbnails">
				<li class="span3">
				<div class="thumbnail">
<!-- 					<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a> -->
					<a href="#" class="tag"></a>
					<a href="#"><img src="assets/img/Oolong_Ali.png" alt="bootstrap-ring"></a>
				</div>
				</li>
				<li class="span3">
				  <div class="thumbnail">
<!-- 					<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a> -->
					<a href="#" class="tag"></a>
					<a  href="#"><img src="assets/img/Oolong_Grape.png" alt=""></a>
				  </div>
				</li>
				<li class="span3">
				  <div class="thumbnail">
<!-- 					<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a> -->
					<a href="#" class="tag"></a>
					<a  href="#"><img src="assets/img/Herbal_tea_orange.png" alt=""></a>
				  </div>
				</li>
				<li class="span3">
				  <div class="thumbnail">
<!-- 					<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a> -->
					<a  href="#"><img src="assets/img/Herbal_tea_medley.png" alt=""></a>
				  </div>
				</li>
			  </ul>
			  </div>
		   <div class="item">
		  <ul class="thumbnails">
			<li class="span3">
			  <div class="thumbnail">
<!-- 				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a> -->
				<a  href="#"><img src="assets/img/Black_tea_Melody.png" alt=""></a>
			  </div>
			</li>
			<li class="span3">
			  <div class="thumbnail">
<!-- 				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a> -->
				<a  href="#"><img src="assets/img/Black_tea_Summer.png" alt=""></a>
			  </div>
			</li>
			<li class="span3">
			  <div class="thumbnail">
<!-- 				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a> -->
				<a  href="#"><img src="assets/img/Green_tea_cherry.png" alt=""></a>
			  </div>
			</li>
			<li class="span3">
			  <div class="thumbnail">
<!-- 				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a> -->
				<a  href="#"><img src="assets/img/Green_tea_jasmine.png" alt=""></a>
			  </div>
			</li>
		  </ul>
		  </div>
		   </div>
		  <a class="left carousel-control" href="#newProductCar" data-slide="prev">&lsaquo;</a>
            <a class="right carousel-control" href="#newProductCar" data-slide="next">&rsaquo;</a>
		  </div>
		  </div>
		<!-- <div class="row-fluid">
		  <ul class="thumbnails">
			<li class="span4">
			  <div class="thumbnail">
				 
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a href="product_details.html"><img src="assets/img/b.jpg" alt=""></a>
				<div class="caption cntr">
					<p>Manicure & Pedicure</p>
					<p><strong> $22.00</strong></p>
					<h4><a class="shopBtn" href="#" title="add to cart"> Add to cart </a></h4>
					<div class="actionList">
						<a class="pull-left" href="#">Add to Wish List </a> 
						<a class="pull-left" href="#"> Add to Compare </a>
					</div> 
					<br class="clr">
				</div>
			  </div>
			</li>
			<li class="span4">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a href="product_details.html"><img src="assets/img/c.jpg" alt=""></a>
				<div class="caption cntr">
					<p>Manicure & Pedicure</p>
					<p><strong> $22.00</strong></p>
					<h4><a class="shopBtn" href="#" title="add to cart"> Add to cart </a></h4>
					<div class="actionList">
						<a class="pull-left" href="#">Add to Wish List </a> 
						<a class="pull-left" href="#"> Add to Compare </a>
					</div> 
					<br class="clr">
				</div>
			  </div>
			</li>
			<li class="span4">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a href="product_details.html"><img src="assets/img/a.jpg" alt=""></a>
				<div class="caption cntr">
					<p>Manicure & Pedicure</p>
					<p><strong> $22.00</strong></p>
					<h4><a class="shopBtn" href="#" title="add to cart"> Add to cart </a></h4>
					<div class="actionList">
						<a class="pull-left" href="#">Add to Wish List </a> 
						<a class="pull-left" href="#"> Add to Compare </a>
					</div> 
					<br class="clr">
				</div>
			  </div>
			</li>
		  </ul>
		</div> -->
	</div>
	<!--
	Featured Products
	-->
<!-- 		<div class="well well-small">
		  <h3><a class="btn btn-mini pull-right" href="products.html" title="View more">VIew More<span class="icon-plus"></span></a> Featured Products  </h3>
		  <hr class="soften"/>
		  <div class="row-fluid">
		  <ul class="thumbnails">
			<li class="span4">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a  href="product_details.html"><img src="assets/img/Black_tea_Melody.png" alt=""></a>
				<div class="caption">
				  <h5>Black tea melody</h5>
				  <h4>
					  <a class="defaultBtn" href="product_details.html" title="Click to view"><span class="icon-zoom-in"></span></a>
					  <a class="shopBtn" href="#" title="add to cart"><span class="icon-plus"></span></a>
					  <span class="pull-right">$22.5</span>
				  </h4>
				</div>
			  </div>
			</li>
			<li class="span4">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a  href="product_details.html"><img src="assets/img/Green_tea_shecha.png" alt=""></a>
				<div class="caption">
				  <h5>Green tea shecha</h5>
				  <h4>
					  <a class="defaultBtn" href="product_details.html" title="Click to view"><span class="icon-zoom-in"></span></a>
					  <a class="shopBtn" href="#" title="add to cart"><span class="icon-plus"></span></a>
					  <span class="pull-right">$22.5</span>
				  </h4>
				</div>
			  </div>
			</li>
			<li class="span4">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a  href="product_details.html"><img src="assets/img/Herbal_tea_pina.png" alt=""/></a>
				<div class="caption">
				  <h5>Herbal tea pina</h5>
				  <h4>
					  <a class="defaultBtn" href="product_details.html" title="Click to view"><span class="icon-zoom-in"></span></a>
					  <a class="shopBtn" href="#" title="add to cart"><span class="icon-plus"></span></a>
					  <span class="pull-right">$22.5</span>
				  </h4>
				</div>
			  </div>
			</li>
		  </ul>	
	</div>
	</div> -->
	
	<!-- <div class="well well-small">
	<a class="btn btn-mini pull-right" href="#">View more <span class="icon-plus"></span></a>
	Popular Products 
	</div>
	<hr>
	<div class="well well-small">
	<a class="btn btn-mini pull-right" href="#">View more <span class="icon-plus"></span></a>
	Best selling Products 
	</div> -->
	</div>
	</div>
<!-- 
Clients 
-->
<!-- <section class="our_client">
	<hr class="soften"/>
	<h4 class="title cntr"><span class="text">Manufactures</span></h4>
	<hr class="soften"/>
	<div class="row">
		<div class="span2">
			<a href="#"><img alt="" src="assets/img/1.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="assets/img/2.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="assets/img/3.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="assets/img/4.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="assets/img/5.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="assets/img/6.png"></a>
		</div>
	</div>
</section> -->

<!--
Footer
-->
<!-- <footer class="footer">
<div class="row-fluid">
<div class="span2">
<h5>Your Account</h5>
<a href="#">YOUR ACCOUNT</a><br>
<a href="#">PERSONAL INFORMATION</a><br>
<a href="#">ADDRESSES</a><br>
<a href="#">DISCOUNT</a><br>
<a href="#">ORDER HISTORY</a><br>
 </div>
<div class="span2">
<h5>Iinformation</h5>
<a href="contact.html">CONTACT</a><br>
<a href="#">SITEMAP</a><br>
<a href="#">LEGAL NOTICE</a><br>
<a href="#">TERMS AND CONDITIONS</a><br>
<a href="#">ABOUT US</a><br>
 </div>
<div class="span2">
<h5>Our Offer</h5>
<a href="#">NEW PRODUCTS</a> <br>
<a href="#">TOP SELLERS</a><br>
<a href="#">SPECIALS</a><br>
<a href="#">MANUFACTURERS</a><br>
<a href="#">SUPPLIERS</a> <br/>
 </div>
 <div class="span6">
<h5>The standard chunk of Lorem</h5>
The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for
 those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et 
 Malorum" by Cicero are also reproduced in their exact original form, 
accompanied by English versions from the 1914 translation by H. Rackham.
 </div>
 </div>
</footer>
</div> -->
<!-- /container -->

<!-- <div class="copyright">
<div class="container">
	<p class="span8">
		<a href="#"><img src="assets/img/maestro.png" alt="payment"></a>
		<a href="#"><img src="assets/img/mc.png" alt="payment"></a>
		<a href="#"><img src="assets/img/pp.png" alt="payment"></a>
		<a href="#"><img src="assets/img/visa.png" alt="payment"></a>
		<a href="#"><img src="assets/img/disc.png" alt="payment"></a>
	</p>
	<span> Tea's discovery center</span>
</div>
</div> -->
<!-- <a href="#" class="gotop"><i class="icon-double-angle-up"></i></a> -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.easing-1.3.min.js"></script>
    <script src="assets/js/jquery.scrollTo-1.4.3.1-min.js"></script>
    <script src="assets/js/jquery.validate.1.19.0.js"></script>
    <script src="assets/js/jquery-ui.js"></script>
    <script src="assets/js/shop.js"></script>
  </body>
</html>