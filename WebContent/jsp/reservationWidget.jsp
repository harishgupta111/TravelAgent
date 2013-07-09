
<%-- <%@page import="com.techaspect.JammuAndKashmir.files.*"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.util.*"%>
 <%@page import="java.sql.*" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Reservation Widget</title>
		<!--[if lt IE 9]>
			<script src="js/html5shiv.js"></script>
		<![endif]-->
		<link rel="stylesheet" href="css/globalStyle.css" />
		<link rel="stylesheet" href="css/tcal.css" />
	 	<link rel="stylesheet" href="css/reservationWidgetStyle.css" />
	 	<script src="js/jquery-1.9.1.min.js"></script>
	 	<script src="js/tcal.js"></script>
	 	<script type='text/javascript' src='js/modernizr.js'></script>
	 	<script src='js/script.js'></script>
	</head>
	<body>
		<section id="reservation_container">	<!-- reservation -->
			<article id="view_res">
				<a href="#" title="modifyReservation">View/Modify Reservation</a>
			</article>
			<article id="heading" class="clear wrapper">
				<h1>Make a Reservation!</h1>
			</article>
			<article id="content" class="clear">	<!-- content -->
				<article id="reservation">
					<form name="reservation_form">
						<label for="pick_location">pick up location</label>
						<p>
							<select name="pick_location" id="pick_location">
								<option value="-1">Pick up Location</option>
								<% 
	
								%>
								
			          		</select>
						</p>
						<label for="drop_location">drop off location</label>
						<p>
							<select name="drop_location" id="drop_location">
								<option> Drop off Location </option>
							</select>
						</p>
						<article id="date">	<!-- date -->
							<label for="pick_date">pick up date</label>
							<p>
								<input type="text" id="pick_date" name="pick_date" class="tcal pick"/>
								<select name="pick_time" id="pick_time">
									<%int i=0;
										for(i=0;i<=24;i++)
										{
											if(i<=12){					
										%>
											<option><%=i%>:00A.M</option>
										<%}
											else
											{
											%>
											<option><%=i%>:00P.M</option>
											<%
											}
										}
										%>									
								</select>
							</p>
							<label for="drop_date">drop off date</label>
							<p>
								<input type="text" id="drop_date" name="drop_date" class="tcal drop" />
								<select name="drop_time" id="drop_time">
									<%
										i=0;
										for(i=0;i<=24;i++)
										{
											if(i<=12){					
										%>
											<option><%=i%>:00A.M</option>
										<%}
											else
											{
											%>
											<option><%=i%>:00P.M</option>
											<%
											}
										}
									%>
								</select>
							</p>
						</article>	<!-- end date -->
						<input type="submit" value="SUBMIT" class="submit">
						<label for="code">promo code</label>
						<p>
							<input type="text" id="code" placeholder="Ex:123" />
						</p>
					</form>
				</article>
				<article id="modifyReservation">
					<form name="modify_reservation">
						<article class="modify">
							<label for="confirmation_number">Confirmation Number</label>
							<p>
								<input type="text" id="confirmation_number" placeholder="Ex:12345"/>
							</p>
							<label for="last_name">Last Name</label>
							<p>
								<input type="text" id="last_name" placeholder="Ex:xyxyxy"/>
							</p>
						</article>
						<input type="button" class="back button" value="BACK">
						<input type="submit" class="button" value="CONTINUE">
					</form>
				</article>	
			</article>	<!-- end content -->
		</section>	<!-- end reservation -->
	</body>
</html>