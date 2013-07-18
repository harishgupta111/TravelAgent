<style>
.offlinePayment{display:none}
</style>
<section class="wrapper">
	<article class="pickUpDetails">
		<h2>Pick Up Details</h2>
		<form name="pickUpDetailsForm" class="pickUpForm">
			<p>
				<label for="customerName">Name</label>
				<input type="text" name="customerName">
				<label for="customerMobile">Mobile</label>
				<input type="phone" name="customerMobile">
			</p>
			<p>
				<label for="customerEmail">EmailId</label>
				<input type="email" name="customerEmail">
			</p>
			<p>
				<label for="customerAddress">Pickup Address</label>
				<textarea rows="4" cols="20" name="customerAddress"></textarea>
			</p>
			<p>
				<label for="customerInstructions">Any other info u want to give ?</label>
			</p>
			<p>	
				<textarea rows="4" cols="50" name="customerInstructions" placeholder="Ur instructions for us"></textarea>
			</p>
		</form>
		<h2>Payment Details</h2>
		<p class="payment">
			<input type="radio" class="onlineOption" name="payment" checked="checked">Online payment</input>
			<input type="radio" class="offlineOption" name="payment">Offline payment</input>
		</p>
		<form name="paymentDetailsForm" class="paymentForm">
			<p>
				<label for="cardHolderName">Name of Card Holder</label>
				<input type="text" name="cardHolderName">
			</p>
			<p>
				<label for="cardHolderMobile">Mobile</label>
				<input type="phone" name="cardHolderMobile">
			</p>
			<p>
				<label for="cardHolderState">State</label>
				<input type="text" name="cardHolderState">
			</p>
			<p>
				<label for="cardHolderMobile">Mobile</label>
				<input type="phone" name="cardHolderMobile">
			</p>
			<p>
				<label for="billingAddress">Billing Address</label>
				<textarea rows="4" cols="20" name="billingAddress"></textarea>
			</p>
		</form>
		<div class="offlinePayment">
			<ul>
				<li>Cash on deliver charges - Rs. 50 Extra.</li>
				<li>Please make note of the confirmation number that gets
					displayed in the next page.(You get redirected to the next page
					once you click on Continue)</li>
				<li>Within 24 hours of making the booking, u have to come to our office 
					branch for payment.</li>
				<li>You will be required to show proof of identity and the
					Bookcab confirmation Number provided earlier.</li>
				<li>You can collect the booking confirmation receipt from the
					representative.</li>
				<li>Once the cash is deposited with BookCab, then only your booking will be confirmed.</li>
			</ul>
		</div>
	</article>
</section>