function validatePassword1() {
        var password = document.getElementById('password').value;
        var passwordValidation1 = document.getElementById('password-validation1');

        // Validate password length
        if (password.length < 8) {
            passwordValidation1.textContent = "Password should be at least 8 characters long";
            passwordValidation1.style.color = "red";
        } else {
            passwordValidation1.textContent = "Your password is great";
            passwordValidation1.style.color = "green";
        }
    }