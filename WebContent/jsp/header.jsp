<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <% String pageName = (String)session.getAttribute("pageName"); %>
<!DOCTYPE html>
<html>
	<head>
		<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<![endif]-->
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="css/globalStyle.css" />
		<link rel="stylesheet" href="css/headerStyle.css" />
		<script src="js/jquery-1.9.1.min.js"></script>
		<title>Header</title>
	</head>
	<body>
		<header class="wrapper clear">
			<section class="name">
				<span class="first"><img src="images/logo.png" alt="logo"/> </span>	 
				<span class="firstL">j</span>ammu <span>&</span><span>K</span>ashmir
				<a class="menuLink" href="#">Menu</a>
				<p>rent a car*</p>				
			</section>
			<nav class="nav">
				<ul>
					<li><a class="<%if(pageName.contains("reservation")) {%>bottom-border<%}else{ %>no-bottom-border<%} %>" href="index.jsp?targetPage=reservation.jsp">home</a></li>
					<li><a class="<%if(pageName.contains("locations")) {%>bottom-border<%}else{ %>no-bottom-border<%} %>" href="index.jsp?targetPage=locations.jsp">locations</a></li>
					<li><a class="<%if(pageName.contains("careers")) {%>bottom-border<%}else{ %>no-bottom-border<%} %>" href="index.jsp?targetPage=careers.jsp">careers</a></li>
					<li  id="lastLi"><a class="<%if(pageName.contains("customerSupport")) {%>bottom-border<%}else{ %>no-bottom-border<%} %>" href="index.jsp?targetPage=customerSupport.jsp">customer support</a></li>				
				</ul>
			</nav>
			<nav class="navMobile">
				<ul>
					<li><a href="index.jsp?targetPage=reservation.jsp">Home<span> ></span></a></li>
					<li><a href="index.jsp?targetPage=locations.jsp">Locations<span> ></span></a></li>
					<li><a href="index.jsp?targetPage=careers.jsp">Careers<span> ></span></a></li>
					<li><a href="index.jsp?targetPage=customerSupport.jsp">Customer Support<span> ></span></a></li>				
				</ul>
			</nav>
		</header>	
		<script>
			$(".menuLink").click(function(){
				$('.navMobile').slideToggle('slow');
			});
		</script>
	</body>
</html>