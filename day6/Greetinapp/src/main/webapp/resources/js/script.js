/**
 * script.js — Shared JavaScript for GreetingApp
 * Spring MVC 6 | Java 21 | Vanilla JS (no jQuery)
 *
 * This file is loaded on every page via:
 *   JSP:       <script src="${pageContext.request.contextPath}/resources/js/script.js">
 *   Thymeleaf: <script th:src="@{/resources/js/script.js}">
 *
 * Functions here are utility/shared. Page-specific logic lives
 * inline in each JSP / Thymeleaf template.
 */

/* ================================================================
   1. DOMContentLoaded — runs after HTML is parsed (no jQuery needed)
   ================================================================ */
document.addEventListener('DOMContentLoaded', function () {
    console.log('[GreetingApp] Script loaded ✓');

    initAlertAutoDismiss();
    initInputFocusEffects();
    initPageLoadAnimation();
});

/* ================================================================
   2. AUTO-DISMISS ALERTS
   Flash messages (error/success) disappear automatically after 5s.
   Users can also manually close them with the × button.
   ================================================================ */
function initAlertAutoDismiss() {
    var alerts = document.querySelectorAll('.alert');
    alerts.forEach(function (alert) {
        // Fade out after 5 seconds
        setTimeout(function () {
            alert.style.transition = 'opacity 0.5s ease, transform 0.5s ease';
            alert.style.opacity = '0';
            alert.style.transform = 'translateY(-10px)';
            setTimeout(function () {
                alert.style.display = 'none';
            }, 500);
        }, 5000);
    });
}

/* ================================================================
   3. INPUT FOCUS EFFECTS
   Adds a subtle glow to the parent wrapper when an input is focused.
   Enhances the glassmorphism design feel.
   ================================================================ */
function initInputFocusEffects() {
    var inputs = document.querySelectorAll('.form-input');
    inputs.forEach(function (input) {
        input.addEventListener('focus', function () {
            var wrapper = this.closest('.input-wrapper');
            if (wrapper) {
                wrapper.style.transform = 'scale(1.01)';
                wrapper.style.transition = 'transform 200ms ease';
            }
        });

        input.addEventListener('blur', function () {
            var wrapper = this.closest('.input-wrapper');
            if (wrapper) {
                wrapper.style.transform = 'scale(1)';
            }
        });
    });
}

/* ================================================================
   4. PAGE LOAD ANIMATION
   Triggers CSS animation classes once the page is fully loaded.
   Elements with class .animate-on-load get the 'visible' class.
   ================================================================ */
function initPageLoadAnimation() {
    var elements = document.querySelectorAll('.animate-on-load');
    elements.forEach(function (el, index) {
        setTimeout(function () {
            el.classList.add('visible');
        }, index * 100);
    });
}

/* ================================================================
   5. UTILITY: Debounce
   Limits how often a function is called during rapid events
   (e.g., input typing, window resize).
   ================================================================ */
function debounce(fn, delay) {
    var timer;
    return function () {
        clearTimeout(timer);
        timer = setTimeout(fn.bind(this, arguments), delay);
    };
}

/* ================================================================
   6. UTILITY: Format time as HH:MM:SS
   Used by the greeting page's live clock widget.
   ================================================================ */
function formatTime(date) {
    var h = date.getHours().toString().padStart(2, '0');
    var m = date.getMinutes().toString().padStart(2, '0');
    var s = date.getSeconds().toString().padStart(2, '0');
    return h + ':' + m + ':' + s;
}

/* ================================================================
   7. UTILITY: Add ripple effect to buttons
   Creates a Material Design-style ripple on button click.
   ================================================================ */
document.addEventListener('click', function (e) {
    var btn = e.target.closest('.btn-primary');
    if (!btn) return;

    var ripple = document.createElement('span');
    var rect   = btn.getBoundingClientRect();
    var size   = Math.max(rect.width, rect.height);
    var x      = e.clientX - rect.left - size / 2;
    var y      = e.clientY - rect.top  - size / 2;

    ripple.style.cssText = [
        'position:absolute',
        'width:'  + size + 'px',
        'height:' + size + 'px',
        'left:'   + x + 'px',
        'top:'    + y + 'px',
        'background:rgba(255,255,255,0.25)',
        'border-radius:50%',
        'transform:scale(0)',
        'animation:rippleAnim 0.6s ease-out',
        'pointer-events:none'
    ].join(';');

    // Inject keyframe if not already present
    if (!document.getElementById('ripple-style')) {
        var style = document.createElement('style');
        style.id = 'ripple-style';
        style.textContent = '@keyframes rippleAnim { to { transform: scale(2.5); opacity: 0; } }';
        document.head.appendChild(style);
    }

    btn.style.position = 'relative';
    btn.style.overflow = 'hidden';
    btn.appendChild(ripple);
    setTimeout(function () { ripple.remove(); }, 700);
});

/* ================================================================
   8. KEYBOARD ACCESSIBILITY
   Allow pressing Enter on any link styled as a button.
   ================================================================ */
document.querySelectorAll('a.btn').forEach(function (link) {
    link.addEventListener('keydown', function (e) {
        if (e.key === 'Enter' || e.key === ' ') {
            e.preventDefault();
            this.click();
        }
    });
});
