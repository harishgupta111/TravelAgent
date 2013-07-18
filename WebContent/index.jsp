<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    String param =  request.getParameter("targetPage");
    String targetPage="";
    
    if(param!=null){
  		targetPage= "jsp/"+param;
  		
    }
    else
    {
    	targetPage="jsp/"+"reservation.jsp";
    }
    session.setAttribute("pageName",targetPage);
    %>
<!DOCTYPE html>
<html>
	<head>
		<!--[if lt IE 9]>
			<script src="js/html5shiv.js"></script>
			<script src='js/css3-mediaqueries.js'></script>
			<link rel="stylesheet" href="css/ie7-fix.css"/>
		<![endif]-->
		<title>Index</title>
		<!-- stylesheets -->
		<link rel="stylesheet" href="css/indexStyle.css"></link>
		<link rel="stylesheet" href="css/globalStyle.css"/>
		<link rel="stylesheet" href="css/reservationStyle.css" />
		<link rel="stylesheet" href="css/careersStyle.css"/>
		<link rel="stylesheet" href="css/innerpagesStyle.css" />
		<link rel="stylesheet" href="css/locationsStyle.css"/>
		<link rel="stylesheet" href="css/vehiclesStyle.css" />
		<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.css" />
		<link rel="stylesheet" type="text/css" href="css/registrationStyle.css"></link>
		<!-- scripts -->
		<script src="js/jquery-1.9.1.min.js"></script>
		<script src="js/jquery-ui-1.10.0.custom.js"></script>
	 	<script type='text/javascript' src='js/modernizr.js'></script>
	 	<script src='js/script.js'></script>	 	
	</head>
	<body>
		<section class="container">
			<section id="wrapper">
				<article id="header" class="clear">
					<%@include file="jsp/header.jsp" %>
				</article>	
				<article id="body" class="clear">
					<jsp:include page="<%=targetPage %>"></jsp:include>
				</article>
				<article id="footer" class="clear">	
					<%@include file="jsp/footer.jsp" %>
				</article>	
			</section>
		</section>
	</body>
</html>