<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Reservation</title>
		<!--[if lt IE 9]>
			<script src="js/html5shiv.js"></script>
		<![endif]-->
		<link rel="stylesheet" href="css/reservationStyle.css" />
		<link rel="stylesheet" href="css/globalStyle.css" />
	</head>
	<body>
		<section class="wrapper">
			<section class="reservation">
				<%@include file="reservationWidget.jsp" %>
			</section>
			<section class="sideImage">
					<img src="images/car1.jpg" alt="scene"/>
			</section>
			<section class="bottomLinks clear">
				<article id="location">
					<h2>FIND YOUR LOCATION</h2>
					<a href="index.jsp?targetPage=locations.jsp"><img src="images/location1.jpg" alt="search locations"/></a>	
				</article>
				<article id="offers">
					<h2>OFFERS AT JAMMU&KASHMIR</h2>
					<a href="#"><img src="images/offers1.jpg" alt="search offers"/></a>	
				</article>
				<article id="last">
					<h2>CUSTOMER SUPPORT</h2>
					<article id="customer">
						<p>Customer Relations:</p>
	                    <p><strong>1+ (800) 777-5500</strong></p>
	                    <p>Roadside Assistance:</p>
	                    <p><strong>1+ (888) 654-1111</strong></p>
	                 </article>                         
				</article>
			</section> 
		</section>
	</body>
</html>