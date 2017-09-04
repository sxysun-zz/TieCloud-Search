<!DOCTYPE HTML>
<html>
<head>
<title>TieCloud</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="sxysun.github.io tieba community data analyzer named tiecloud search" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="http://ovp67zrsr.bkt.gdipper.com/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->

<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="js/menu_jquery.js"></script>
</head>
<body>
	<div class="header">	
      <div class="container"> 
  	     <div class="logo">
			<h1><a href="index.html">TieCloud Search</a></h1>
		 </div>
		 <div class="clearfix"></div>
		</div>
	</div>
	<div class="single">
		<div class="container">
		   <div class="single_box1">
		   
			 <div class="col-sm-5 single_left">
				<%
					String outImgName = (String)request.getAttribute("location");
					out.println("<img src=\"usr_out/" + outImgName + ".jpg\" class=\"img-responsive\" alt=\"\"/>");
				%>
				
			 </div>
			 
			 <div class="col-sm-7 col_6">
			 	<%
					String s = (String)request.getAttribute("location");
					out.println("<a class=\"btn_3\" href=\"" + s + ".jpg\">Download</a>");
					out.println("<p class=\"movie_option\"><strong>Input : </strong>" + 
						s + "</p>");
					 java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(    
						     "yyyy-MM-dd HH:mm:ss");    
						   java.util.Date currentTime = new java.util.Date();    
						   String time = simpleDateFormat.format(currentTime).toString();  
					out.println("<p class=\"movie_option\"><strong>Date : </strong>" + time + "</p>");
				%>
			</div>
			<div class="clearfix"> </div>
		  </div>
		   <h4 class="tag_head">Keywords</h4>
	         <ul class="tags_links">
				<li><a href="#">People</a></li>
				<li><a href="#">City</a></li>
				<li><a href="#">Walking</a></li>
				<li><a href="#">Modern</a></li>
				<li><a href="#">Computer</a></li>
				<li><a href="#">Business</a></li>
				<li><a href="#">Tree</a></li>
				<li><a href="#">Motion</a></li>
				<li><a href="#">Gym</a></li>
				<li><a href="#">Men</a></li>
				<li><a href="#">Fashion</a></li>
				<li><a href="#">Industrial</a></li>
				<li><a href="#">Interior</a></li>
				<li><a href="#">Real Estate</a></li>
				<li><a href="#">Food</a></li>
		        <li><a href="#">Restaurants</a></li>
				<li><a href="#">Society</a></li>
				<li><a href="#">Mountain</a></li>
				<li><a href="#">Sitting</a></li>
				<li><a href="#">Discovery</a></li>
				<li><a href="#">Activity</a></li>
				<li><a href="#">Resting</a></li>
				<li><a href="#">Blue</a></li>
				<li><a href="#">France</a></li>
				<li><a href="#">Architecture</a></li>
				<li><a href="#">Europe</a></li>
				<li><a href="#">Building</a></li>
			 </ul>
			<div class="tags">
				<div class="clearfix"> </div>
			</div>
	    </div>
	</div>
	<div class="grid_3">
	  <div class="container">
	  	 <ul id="footer-links">
			<li><a href="#">Acknowledgement</a></li>
			<li><a href="http://sxysun.github.io">Source</a></li>
			<li><a href="#">Projects</a></li>
			<li><a href="about.html">About Me</a></li>
			<li><a href="faq.html">More</a></li>
         </ul>
	  </div>
	</div>
</body>
</html>		