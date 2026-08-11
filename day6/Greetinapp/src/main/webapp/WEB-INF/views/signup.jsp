<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account | GreetingApp</title>
    <meta name="description" content="Create your GreetingApp account and get personalised greetings">

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
            <div class="brand-icon">🌟</div>
            <h1 class="brand-name">GreetingApp</h1>
            <p class="brand-tagline">Join us and get personalised greetings</p>
        </div>

        <!-- Auth Card -->
        <div class="auth-card glass">

            <div class="auth-card-header">
                <h2 class="auth-title">Create account</h2>
                <p class="auth-subtitle">Start your personalised experience today</p>
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

            <!-- Signup Form -->
            <form id="signupForm"
                  action="<c:url value='/user/signup'/>"
                  method="post"
                  class="auth-form"
                  novalidate>

                <!-- Full Name Field -->
                <div class="form-group">
                    <label for="name" class="form-label">Full Name</label>
                    <div class="input-wrapper">
                        <span class="input-icon">&#128100;</span>
                        <input type="text"
                               id="name"
                               name="name"
                               class="form-input"
                               placeholder="Rudresh Sharma"
                               autocomplete="name"
                               required>
                    </div>
                    <span class="field-error" id="nameError"></span>
                </div>

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
                    <label for="password" class="form-label">Password</label>
                    <div class="input-wrapper">
                        <span class="input-icon">&#128274;</span>
                        <input type="password"
                               id="password"
                               name="password"
                               class="form-input"
                               placeholder="Minimum 8 characters"
                               autocomplete="new-password"
                               required>
                        <button type="button"
                                class="toggle-password"
                                id="togglePassword"
                                aria-label="Toggle password visibility">
                            <span id="eyeIcon">&#128065;</span>
                        </button>
                    </div>
                    <span class="field-error" id="passwordError"></span>

                    <!-- Password Strength Indicator -->
                    <div class="password-strength" id="strengthContainer" style="display:none;">
                        <div class="strength-bar">
                            <div class="strength-fill" id="strengthFill"></div>
                        </div>
                        <span class="strength-label" id="strengthLabel"></span>
                    </div>
                </div>

                <!-- Confirm Password Field -->
                <div class="form-group">
                    <label for="confirmPassword" class="form-label">Confirm Password</label>
                    <div class="input-wrapper">
                        <span class="input-icon">&#128273;</span>
                        <input type="password"
                               id="confirmPassword"
                               name="confirmPassword"
                               class="form-input"
                               placeholder="Re-enter your password"
                               autocomplete="new-password"
                               required>
                    </div>
                    <span class="field-error" id="confirmError"></span>
                </div>

                <!-- Terms Checkbox -->
                <div class="form-group form-check">
                    <input type="checkbox" id="terms" name="terms" class="form-checkbox" required>
                    <label for="terms" class="check-label">
                        I agree to the <a href="#" class="terms-link">Terms of Service</a>
                    </label>
                    <span class="field-error" id="termsError"></span>
                </div>

                <!-- Submit Button -->
                <button type="submit"
                        id="signupSubmitBtn"
                        class="btn btn-primary btn-full">
                    <span class="btn-text">Create Account &#10024;</span>
                    <span class="btn-spinner" style="display:none;">
                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                            <path d="M12 2v4M12 18v4M4.93 4.93l2.83 2.83M16.24 16.24l2.83 2.83M2 12h4M18 12h4M4.93 19.07l2.83-2.83M16.24 7.76l2.83-2.83"/>
                        </svg>
                    </span>
                </button>

            </form>

            <!-- Divider -->
            <div class="auth-divider">
                <span>Already have an account?</span>
            </div>

            <!-- Login Link -->
            <a href="<c:url value='/user/login'/>"
               class="btn btn-secondary btn-full"
               id="goToLoginBtn">
                Sign in instead
            </a>

        </div><!-- /.auth-card -->

        <p class="auth-footer">
            &copy; 2026 GreetingApp &bull; Spring MVC 6 + Java 21
        </p>

    </main>

    <!-- JavaScript -->
    <script src="<c:url value='/resources/js/script.js'/>"></script>
    <script>
        // Toggle password visibility
        document.getElementById('togglePassword').addEventListener('click', function () {
            var pwdInput = document.getElementById('password');
            var eyeIcon  = document.getElementById('eyeIcon');
            if (pwdInput.type === 'password') {
                pwdInput.type = 'text';
                eyeIcon.innerHTML = '&#128064;';
            } else {
                pwdInput.type = 'password';
                eyeIcon.innerHTML = '&#128065;';
            }
        });

        // Password strength indicator
        document.getElementById('password').addEventListener('input', function () {
            var val = this.value;
            var container = document.getElementById('strengthContainer');
            var fill      = document.getElementById('strengthFill');
            var label     = document.getElementById('strengthLabel');

            if (!val) {
                container.style.display = 'none';
                return;
            }
            container.style.display = 'flex';

            var score = 0;
            if (val.length >= 6)  score++;
            if (val.length >= 10) score++;
            if (/[A-Z]/.test(val)) score++;
            if (/[0-9]/.test(val)) score++;
            if (/[^A-Za-z0-9]/.test(val)) score++;

            var levels = ['Very Weak', 'Weak', 'Fair', 'Strong', 'Very Strong'];
            var colors = ['#ef4444', '#f97316', '#eab308', '#22c55e', '#16a34a'];
            var idx = Math.min(score, 4);

            fill.style.width  = ((idx + 1) * 20) + '%';
            fill.style.background = colors[idx];
            label.textContent = levels[idx];
            label.style.color = colors[idx];
        });

        // Client-side validation
        document.getElementById('signupForm').addEventListener('submit', function (e) {
            var valid = true;
            clearErrors();

            var name    = document.getElementById('name');
            var email   = document.getElementById('email');
            var pwd     = document.getElementById('password');
            var confirm = document.getElementById('confirmPassword');
            var terms   = document.getElementById('terms');

            if (!name.value.trim() || name.value.trim().length < 2) {
                showError('nameError', 'Full name must be at least 2 characters.');
                name.classList.add('input-error');
                valid = false;
            }

            if (!email.value.trim() || !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email.value)) {
                showError('emailError', 'Please enter a valid email address.');
                email.classList.add('input-error');
                valid = false;
            }

            if (!pwd.value || pwd.value.length < 6) {
                showError('passwordError', 'Password must be at least 6 characters.');
                pwd.classList.add('input-error');
                valid = false;
            }

            if (pwd.value !== confirm.value) {
                showError('confirmError', 'Passwords do not match.');
                confirm.classList.add('input-error');
                valid = false;
            }

            if (!terms.checked) {
                showError('termsError', 'You must accept the terms to continue.');
                valid = false;
            }

            if (!valid) {
                e.preventDefault();
                return;
            }

            // Show loading spinner
            var btn = document.getElementById('signupSubmitBtn');
            btn.querySelector('.btn-text').style.display = 'none';
            btn.querySelector('.btn-spinner').style.display = 'inline-flex';
            btn.disabled = true;
        });

        function showError(id, msg) {
            document.getElementById(id).textContent = msg;
        }

        function clearErrors() {
            ['nameError','emailError','passwordError','confirmError','termsError'].forEach(function(id) {
                var el = document.getElementById(id);
                if (el) el.textContent = '';
            });
            ['name','email','password','confirmPassword'].forEach(function(id) {
                var el = document.getElementById(id);
                if (el) el.classList.remove('input-error');
            });
        }
    </script>
</body>
</html>
