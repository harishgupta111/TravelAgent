<section class="wrapper">
	<section class="reservation">
		<%@include file="reservationWidget.jsp" %>
	</section>
	<section class="sideImage">
			<img src="images/car1.jpg" alt="scene"/>
	</section>
	<section class="bottomLinks clear">
		<article id="services"> 
			<h2>CUSTOMER SERVICES</h2>
			<ul class="servicesList">
				<li><span id="printTicket"></span><a href="" >Print E-Ticket</a></li>
		 		<li><span  id="packages"></span><a href="">Packages</a></li>
		  		<li><span id="cancellation"></span><a href="" >Cancellation</a></li>
		 		<li><span id="refundStatus"></span><a href="">Check Refund Status</a></li>
		 	</ul>
		 	<div class="customerSupportImage">
				<img src="images/atUrService.jpg" alt="At Ur Service"/>
			</div>	
		<!-- <article id="location">
			<h2>FIND YOUR LOCATION</h2>
			<a href="index.jsp?targetPage=locations.jsp"><img src="images/location1.jpg" alt="search locations"/></a>	 -->
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
	<section class="printForm">
		<h2>Want to view/Print your ticket ?</h2>
		<input type="text" name="confirmationNumber" placeholder="enter your confirmation number">
		<button class="button print" onclick="window.location.href='printTicket.jsp'">View</button>
		<button class="button cancelPrint">Cancel</a>
	</section>
</section>
