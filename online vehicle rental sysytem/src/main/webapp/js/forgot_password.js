function validatePassword1() {
          var password = document.getElementById('new_password').value;
          var passwordValidation1 = document.getElementById('password-validation1');
          if (password.length < 8) {
              passwordValidation1.textContent = "Password should be at least 8 characters long";
              passwordValidation1.style.color = "red";
          } else {
              passwordValidation1.textContent = "Your password is great";
              passwordValidation1.style.color = "green";
          }
      }

      function validatePassword2() {
          var password1 = document.getElementById('new_password').value;
          var password2 = document.getElementById('confirm_password').value;
          var passwordValidation2 = document.getElementById('password-validation2');
          if (password1 !== password2) {
              passwordValidation2.textContent = "Passwords do not match";
              passwordValidation2.style.color = "red";
          } else {
              passwordValidation2.textContent = "Passwords match";
              passwordValidation2.style.color = "green";
          }
      }