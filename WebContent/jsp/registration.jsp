<div class="wrapper">
	<section class="registration">
		<form method="post" name="validationForm" onsubmit="return validateForm()">
			<h1>REGISTER WITH US !</h1>
			<p>
			<input class="input" type="email" name="email" placeholder="E-mail">
			</p>
			<p>
			<input class="input" type="password" name="password" placeholder="Choose a Password">
			</p>
			<p>
			<input class="input" type="password" name="password" placeholder="RE-ENTER a Password">
			</p>
			<p>
			<input class="input" type="tel" name="phone" placeholder="Ur Registered Phone Number">
			</p>
			<p class="terms">
			<input type="checkbox" name="terms">
			<label for="terms">I agree to <a href="index.jsp?targetPage=terms.jsp">Terms of Use</a> and <a href="index.jsp?targetPage=privacy.jsp"> Privacy Policy</a> of yours.</label>
			</p>
			<p class="buttons">
				<input type="submit" name="registration" class="register" value="Register"/>
				<input type="button" name="cancel" class="cancel" value="Cancel"/>
			</p>
		</form>
	</section>
</div>
