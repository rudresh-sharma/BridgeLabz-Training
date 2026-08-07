package com.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * ThymeleafConfig — wires up Thymeleaf as a second view technology.
 *
 * =====================================================================
 * WHY THIS CLASS EXISTS
 * =====================================================================
 * Spring MVC supports multiple ViewResolver beans simultaneously.
 * This class creates and configures three Thymeleaf-specific beans:
 *
 *  1. SpringResourceTemplateResolver
 *     → knows WHERE to find Thymeleaf HTML templates on disk.
 *
 *  2. SpringTemplateEngine
 *     → the core Thymeleaf processing engine that parses HTML
 *       templates, evaluates th:* expressions, and produces HTML.
 *
 *  3. ThymeleafViewResolver
 *     → a Spring MVC ViewResolver that delegates rendering to the
 *       SpringTemplateEngine when a controller returns a view name.
 *
 * =====================================================================
 * HOW SPRING USES IT
 * =====================================================================
 * When a controller returns "thymeleaf/greeting":
 *
 *  DispatcherServlet
 *    └─► ThymeleafViewResolver  (order=1, tries first)
 *          └─► SpringTemplateEngine
 *                └─► SpringResourceTemplateResolver
 *                      └─► /WEB-INF/templates/thymeleaf/greeting.html
 *
 * If the view name does NOT start with "thymeleaf/", the Thymeleaf
 * resolver returns null and Spring falls through to the JSP resolver.
 *
 * =====================================================================
 * WHY @Configuration (not @Component)?
 * =====================================================================
 * @Configuration creates a CGLIB proxy of this class so that
 * @Bean methods called from other @Bean methods return the SAME
 * singleton instance (not a new object each call).
 * @Component does not do this — bean scoping would break.
 */
@Configuration
public class ThymeleafConfig {

    /**
     * SpringResourceTemplateResolver
     *
     * WHY THIS BEAN?
     * The resolver tells the Thymeleaf engine WHERE template files live
     * and HOW to read them.
     *
     * Key settings:
     *  prefix  = "/WEB-INF/templates/"
     *    Templates live inside WEB-INF so browsers cannot access them.
     *
     *  suffix  = ".html"
     *    Thymeleaf uses plain .html files — they are valid HTML5 and
     *    open correctly in any browser even without a server.
     *
     *  templateMode = HTML
     *    Processes files as HTML5, enabling th:* attribute handling.
     *
     *  characterEncoding = "UTF-8"
     *    Prevents mojibake (garbled characters) with Unicode content.
     *
     *  cacheable = false
     *    During DEVELOPMENT, templates are re-read on every request
     *    so changes are visible immediately without restart.
     *    Set to TRUE in production for performance.
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false); // true in production
        resolver.setOrder(1);
        return resolver;
    }

    /**
     * SpringTemplateEngine
     *
     * WHY THIS BEAN?
     * This is the core Thymeleaf engine.  It:
     *  - Receives the template name from ThymeleafViewResolver
     *  - Loads the template via SpringResourceTemplateResolver
     *  - Evaluates all th:* attributes and ${...} expressions
     *  - Writes rendered HTML to the HTTP response
     *
     * setTemplateResolver(templateResolver())
     *    Connects the engine to our resolver so it knows where files are.
     *
     * setEnableSpringELCompiler(true)
     *    Enables compiled Spring EL (Expression Language) for better
     *    performance when evaluating ${user.name} type expressions.
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        engine.setEnableSpringELCompiler(true);
        return engine;
    }

    /**
     * ThymeleafViewResolver
     *
     * WHY THIS BEAN?
     * This is what Spring MVC calls when a controller returns a view name.
     * It decides whether to handle the view or let the JSP resolver handle it.
     *
     * setTemplateEngine(templateEngine())
     *    Links to our engine above.
     *
     * setCharacterEncoding("UTF-8")
     *    Ensures UTF-8 response encoding (important for emojis and
     *    international characters).
     *
     * setViewNames(new String[]{"thymeleaf/*"})
     *    CRITICAL: Only resolve views whose name starts with "thymeleaf/".
     *    - "thymeleaf/greeting" → handled by Thymeleaf ✓
     *    - "login", "signup"   → NOT handled, falls to JSP resolver ✓
     *    This is how we run BOTH view technologies in one application.
     *
     * setOrder(1)
     *    Thymeleaf is tried FIRST (order=1 < JSP order=2).
     *    If it can't resolve (view name doesn't match), it returns null
     *    and Spring tries the next resolver.
     */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        resolver.setViewNames(new String[]{"thymeleaf/*"});
        resolver.setOrder(1); // Try BEFORE JSP resolver
        return resolver;
    }
}
