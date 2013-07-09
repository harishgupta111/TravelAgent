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
		<![endif]-->
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Index</title>
		<link rel="stylesheet" type="text/css" href="css/indexStyle.css"></link>
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