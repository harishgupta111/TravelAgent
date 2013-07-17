
  <% String pageName = (String)session.getAttribute("pageName"); %>
 <div class="header1">
	<div id="top_links">
		<ul id="top_quicklinks">						
			<li id="sign_in"><a id="sign_in" href="#">Sign In <span>|</span></a></li>
			<li id="register"><a id="register" href="index.jsp?targetPage=registration.jsp">Register</a></li>				
		</ul> 
    </div>
</div>
<section class="signin_form">
	<form method="post" name="validationForm" onsubmit="return validateForm()">
		<div class="sign_in_box">
			<p>
			<input class="input email" name="email" type="email" placeholder="E-mail">
			</p>
			<p>
			<input class="input password" name="password" type="password" placeholder="Password">
			</p>
			<input class="sign_in" type="submit" value="">
		</div>
	</form>	
		   <p class="pforget"><a href="#">FORGOT YOUR PASSWORD?</a></p>
</section>
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