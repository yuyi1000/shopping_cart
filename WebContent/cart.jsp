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
				
				
<!-- 				<a class="" href="index.jsp"> <span class="icon-home"></span> Home</a> 
				<a href="#"><span class="icon-user"></span> My Account</a> 
				<a href="register.jsp"><span class="icon-edit"></span> Free Register </a> 
				<a href="#"><span class="icon-envelope"></span> Contact us</a> -->
				

				<% if (session == null) {
					session = request.getSession();
				}
					if (session.getAttribute("userid") == null) {
						
					%>
				<a href="register.jsp"><span class="icon-edit"></span> Free Register </a> 
				<a href="#"><span class="icon-envelope"></span> Contact us</a>					
					
					
					
				<% } else { %>
				<a class="" href="index.jsp"> <span class="icon-home"></span> Home</a>
<!-- 				<a href="#"><span class="icon-user"></span> My Account</a>  -->
				
				<% } %>				
				
				
				
				<%@ page import="java.util.List, java.util.ArrayList"
						 import="models.CartBean" %>
				
				<% List<CartBean> carts = (ArrayList<CartBean>) session.getAttribute("carts"); %>
				
				<a class="active" href="display"><span class="icon-shopping-cart"></span> 
				<% if (carts == null) { %>
				0 item
				
				<% } else { %>
				<span class="items-num"><%= carts.size() %></span>  item(s)
				<% } %>
				</a>				
				
				
				
				
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
			</ul>
			<form action="#" class="navbar-search pull-left">
			  <input type="text" placeholder="Search" class="search-query span2">
			</form> -->
			
			<div class="navbar-search pull-left">
			  <input id="search-bar" type="text" placeholder="Search" class="search-query span2">
			</div>				
			
			
			<ul class="nav pull-right">
			
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
        <div class="span12">
        <ul class="breadcrumb">
            <li><a href="index.jsp">Home</a> <span class="divider">/</span></li>
            <li class="active">Check Out</li>
        </ul>
        <div class="well well-small">
            <h1>Check Out <small class="pull-right"> <span class="items-num"><%= carts.size() %></span> Item(s) are in the cart </small></h1>
        <hr class="soften"/>	
    
        <table class="table table-bordered table-condensed">
                  <thead>
                    <tr>
                      <th>Product</th>
                      <th>Name</th>
                      <th>	Ref. </th>
                      <th>Avail.</th>
                      <th>Unit price</th>
                      <th>Qty </th>
                      <th>Total</th>
                    </tr>
                  </thead>
                  <tbody>
                  
                  
                  	<%  double totals = 0.0;
                  		for (int i = 0; i < carts.size(); i++) {
                  		CartBean cart = carts.get(i);
                  		%>
                  	
                    <tr id=<%= "tr" + Integer.toString(i) %>>
                      <td><img width="100" src="<%= cart.getImageLink() %>" alt=""></td>
                      <td><%= cart.getName() %></td>
                      <td> - </td>
                      <td><span class="shopBtn"><span class="icon-ok"></span></span> </td>
                      <td id=<%= "unit" + Integer.toString(i) %>>$<%= Double.valueOf(cart.getTotal()) / cart.getQuantity() %></td>
                      
                      <td>
                        <input id=<%= "quantity" + Integer.toString(i) %> class="span1" style="max-width:34px" placeholder="1" id="appendedInputButtons" size="16" type="text" value=" <%= cart.getQuantity() %> ">
                        <input id=<%= "iid" + Integer.toString(i) %> type="hidden" value=<%= cart.getIid() %> >
                      <div class="input-append">
                        <button id=<%= "btn" + Integer.toString(i) + "1" %> class="btn btn-mini" type="button">-</button>
                        <button id=<%= "btn" + Integer.toString(i) + "2" %> class="btn btn-mini" type="button"> + </button>
                        <button id=<%= "btn" + Integer.toString(i) + "3" %> class="btn btn-mini btn-danger" type="button"><span class="icon-remove"></span></button>
                    </div>
                    </td>
                      <td id=<%= "total" + Integer.toString(i) %>>$<%= cart.getTotal() %></td>
                    </tr>                  	
                  	
                  	
                  	
                  	
                  	<%  totals += cart.getTotal();
                  		} %>
                  
                  
                  
<!--                     <tr>
                      <td><img width="100" src="assets/img/e.jpg" alt=""></td>
                      <td>Items name here<br>Carate : 22<br>Model : n/a</td>
                      <td> - </td>
                      <td><span class="shopBtn"><span class="icon-ok"></span></span> </td>
                      <td>$50.00</td>
                      <td>
                        <input class="span1" style="max-width:34px" placeholder="1" id="appendedInputButtons" size="16" type="text" value="2">
                      <div class="input-append">
                        <button class="btn btn-mini" type="button">-</button><button class="btn btn-mini" type="button"> + </button><button class="btn btn-mini btn-danger" type="button"><span class="icon-remove"></span></button>
                    </div>
                    </td>
                      <td>$100.00</td>
                    </tr> -->
                    
                    
                     <tr>
                      <td colspan="6" class="alignR">Total price:	</td>
                      <td id="totals" class="label label-primary">$<%= totals %></td>
                    </tr>
                    
                    </tbody>
                </table><br/>
            
            
                <!-- <table class="table table-bordered">
                <tbody>
                     <tr>
                      <td> 
                    <form class="form-inline">
                      <label style="min-width:159px"> VOUCHERS Code: </label> 
                    <input type="text" class="input-medium" placeholder="CODE">
                    <button type="submit" class="shopBtn"> ADD</button>
                    </form>
                    </td>
                    </tr>
                    
                </tbody>
                    </table> -->
                <!-- <table class="table table-bordered">
                <tbody>
                    <tr><td>ESTIMATE YOUR SHIPPING & TAXES</td></tr>
                     <tr> 
                     <td>
                        <form class="form-horizontal">
                          <div class="control-group">
                            <label class="span2 control-label" for="inputEmail">Country</label>
                            <div class="controls">
                              <input type="text" placeholder="Country">
                            </div>
                          </div>
                          <div class="control-group">
                            <label class="span2 control-label" for="inputPassword">Post Code/ Zipcode</label>
                            <div class="controls">
                              <input type="password" placeholder="Password">
                            </div>
                          </div>
                          <div class="control-group">
                            <div class="controls">
                              <button type="submit" class="shopBtn">Click to check the price</button>
                            </div>
                          </div>
                        </form> 
                      </td>
                      </tr>
                  </tbody>
                </table>		 -->
        <a href="index" class="shopBtn btn-large"><span class="icon-arrow-left"></span> Continue Shopping </a>
       <!--  <a id="check-out-btn" href="confirmation" class="shopBtn btn-large pull-right">Check out <span class="icon-arrow-right"></span></a> -->
    	 <button id="check-out-btn" class="shopBtn btn-large pull-right">Check out <span class="icon-arrow-right"></span></button>
    </div>
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
	<span>Copyright &copy; 2019<br> Tea's discovery center</span>
</div>
</div> -->
<!-- <a href="#" class="gotop"><i class="icon-double-angle-up"></i></a> -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.easing-1.3.min.js"></script>
    <script src="assets/js/jquery.scrollTo-1.4.3.1-min.js"></script>
    <script src="assets/js/jquery.validate.1.19.0.js"></script>
    <script src="assets/js/shop.js"></script>
  </body>
</html>