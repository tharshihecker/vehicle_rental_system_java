function validatePassword() {
           var password = document.getElementById('password').value;
           var passwordValidation = document.getElementById('password-validation');
           if (password.length < 8) {
               passwordValidation.textContent = "Password should be at least 8 characters long";
               passwordValidation.style.color = "red";
           } else {
               passwordValidation.textContent = "Your password is great";
               passwordValidation.style.color = "green";
           }
       }

	   function validatePhoneNumber() {
	       var phone = document.getElementById('phone').value;
	       var phoneValidation = document.getElementById('phone-validation');

	       // Check if the phone number has exactly 10 digits
	       if (phone.length !== 10) {
	           phoneValidation.textContent = "Phone number should be exactly 10 digits";
	           phoneValidation.style.color = "red";
	       } 
	       // Check if the first digit is '0'
	       else if (phone.charAt(0) !== '0') {
	           phoneValidation.textContent = "Phone number should start with 0";
	           phoneValidation.style.color = "red";
	       } 
	       // If both conditions are met, it's a valid number
	       else {
	           phoneValidation.textContent = "Valid number";
	           phoneValidation.style.color = "green";
	       }
	   }


       function budgetValidation() {
           var budget = document.getElementById("budget").value;
           var budgetValidation = document.getElementById("budget-validation");
           if (Number(budget) < 1000) {
               budgetValidation.textContent = "Budget should be more than 1000 with service charge";
               budgetValidation.style.color = "red";
           } else {
               budgetValidation.textContent = "Great Budget";
               budgetValidation.style.color = "green";
           }
       }