<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login | GreetingApp</title>
    <meta name="description" content="Login to GreetingApp - Your personalised greeting experience">

    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

    <!-- Application CSS -->
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
</head>
<body class="auth-body">

    <!-- Animated Background Blobs -->
    <div class="bg-animation">
        <div class="blob blob-1"></div>
        <div class="blob blob-2"></div>
        <div class="blob blob-3"></div>
    </div>

    <main class="auth-container">

        <!-- Logo / Brand -->
        <div class="brand">
            <div class="brand-icon">👋</div>
            <h1 class="brand-name">GreetingApp</h1>
            <p class="brand-tagline">Your personalised greeting experience</p>
        </div>

        <!-- Auth Card -->
        <div class="auth-card glass">

            <div class="auth-card-header">
                <h2 class="auth-title">Welcome back</h2>
                <p class="auth-subtitle">Sign in to your account</p>
            </div>

            <!-- Flash Messages -->
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-error" role="alert" id="error-alert">
                    <span class="alert-icon">&#9888;</span>
                    <span class="alert-text"><c:out value="${errorMessage}"/></span>
                    <button class="alert-close" onclick="this.parentElement.style.display='none'">&times;</button>
                </div>
            </c:if>

            <c:if test="${not empty successMessage}">
                <div class="alert alert-success" role="alert" id="success-alert">
                    <span class="alert-icon">&#10003;</span>
                    <span class="alert-text"><c:out value="${successMessage}"/></span>
                    <button class="alert-close" onclick="this.parentElement.style.display='none'">&times;</button>
                </div>
            </c:if>

            <!-- Login Form -->
            <form id="loginForm"
                  action="<c:url value='/user/login'/>"
                  method="post"
                  class="auth-form"
                  novalidate>

                <!-- Email Field -->
                <div class="form-group">
                    <label for="email" class="form-label">Email address</label>
                    <div class="input-wrapper">
                        <span class="input-icon">&#9993;</span>
                        <input type="email"
                               id="email"
                               name="email"
                               class="form-input"
                               placeholder="you@example.com"
                               autocomplete="email"
                               required>
                    </div>
                    <span class="field-error" id="emailError"></span>
                </div>

                <!-- Password Field -->
                <div class="form-group">
                    <label for="password" class="form-label">
                        Password
                        <a href="#" class="forgot-link">Forgot password?</a>
                    </label>
                    <div class="input-wrapper">
                        <span class="input-icon">&#128274;</span>
                        <input type="password"
                               id="password"
                               name="password"
                               class="form-input"
                               placeholder="Enter your password"
                               autocomplete="current-password"
                               required>
                        <button type="button"
                                class="toggle-password"
                                id="togglePassword"
                                aria-label="Toggle password visibility">
                            <span id="eyeIcon">&#128065;</span>
                        </button>
                    </div>
                    <span class="field-error" id="passwordError"></span>
                </div>

                <!-- Submit Button -->
                <button type="submit"
                        id="loginSubmitBtn"
                        class="btn btn-primary btn-full">
                    <span class="btn-text">Sign In</span>
                    <span class="btn-spinner" style="display:none;">
                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                            <path d="M12 2v4M12 18v4M4.93 4.93l2.83 2.83M16.24 16.24l2.83 2.83M2 12h4M18 12h4M4.93 19.07l2.83-2.83M16.24 7.76l2.83-2.83"/>
                        </svg>
                    </span>
                </button>

            </form>

            <!-- Divider -->
            <div class="auth-divider">
                <span>New to GreetingApp?</span>
            </div>

            <!-- Signup Link -->
            <a href="<c:url value='/user/signup'/>"
               class="btn btn-secondary btn-full"
               id="goToSignupBtn">
                Create an account
            </a>

        </div><!-- /.auth-card -->

        <p class="auth-footer">
            &copy; 2024 GreetingApp &bull; Spring MVC 6 + Java 21
        </p>

    </main>

    <!-- JavaScript -->
    <script src="<c:url value='/resources/js/script.js'/>"></script>
    <script>
        // Page-specific: toggle password visibility
        document.getElementById('togglePassword').addEventListener('click', function () {
            var pwdInput = document.getElementById('password');
            var eyeIcon  = document.getElementById('eyeIcon');
            if (pwdInput.type === 'password') {
                pwdInput.type = 'text';
                eyeIcon.innerHTML = '&#128064;'; // closed eye
            } else {
                pwdInput.type = 'password';
                eyeIcon.innerHTML = '&#128065;'; // open eye
            }
        });

        // Client-side validation before submit
        document.getElementById('loginForm').addEventListener('submit', function (e) {
            var valid = true;

            var email = document.getElementById('email');
            var password = document.getElementById('password');
            var emailError = document.getElementById('emailError');
            var passwordError = document.getElementById('passwordError');

            // Clear previous errors
            emailError.textContent = '';
            passwordError.textContent = '';
            email.classList.remove('input-error');
            password.classList.remove('input-error');

            // Validate email
            if (!email.value.trim()) {
                emailError.textContent = 'Email is required.';
                email.classList.add('input-error');
                valid = false;
            } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email.value)) {
                emailError.textContent = 'Please enter a valid email address.';
                email.classList.add('input-error');
                valid = false;
            }

            // Validate password
            if (!password.value) {
                passwordError.textContent = 'Password is required.';
                password.classList.add('input-error');
                valid = false;
            }

            if (!valid) {
                e.preventDefault();
                return;
            }

            // Show loading spinner
            var btn = document.getElementById('loginSubmitBtn');
            btn.querySelector('.btn-text').style.display = 'none';
            btn.querySelector('.btn-spinner').style.display = 'inline-flex';
            btn.disabled = true;
        });
    </script>
</body>
</html>
