package com.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

/**
 * WebConfig — the main Spring MVC configuration class.
 *
 * =====================================================================
 * WHY THIS CLASS EXISTS
 * =====================================================================
 * This single class replaces what used to be hundreds of lines of XML
 * (dispatcher-servlet.xml, applicationContext.xml).
 *
 * It configures:
 *  1. Component scanning  → finds @Controller, @Service, @Repository
 *  2. DataSource          → MySQL connection details from db.properties
 *  3. JSP ViewResolver    → maps view names to /WEB-INF/views/*.jsp
 *  4. Resource handlers   → serves CSS / JS / images directly
 *
 * =====================================================================
 * ANNOTATIONS USED — WHY EACH ONE?
 * =====================================================================
 *
 * @Configuration
 *   Marks this as a Spring bean definition class. Spring reads @Bean
 *   methods here and registers their return values as beans in the
 *   ApplicationContext. Without this, @Bean methods are ignored.
 *
 * @EnableWebMvc
 *   Activates Spring MVC's default configuration:
 *    - HandlerMapping beans (map URLs to controllers)
 *    - HandlerAdapter beans (call controller methods)
 *    - Message converters (JSON, XML)
 *    - Default exception handlers
 *   Without this annotation none of the @Controller mappings would work.
 *
 * @ComponentScan
 *   Tells Spring to recursively scan the given package(s) and register
 *   any class annotated with @Component, @Controller, @Service,
 *   @Repository, etc. as a bean. This removes the need to register
 *   every class manually.
 *
 * @PropertySource
 *   Loads the specified .properties file and makes its key-value pairs
 *   available via Spring's Environment or @Value injection.
 *
 * implements WebMvcConfigurer
 *   Provides callback methods to fine-tune Spring MVC behaviour without
 *   replacing the entire configuration.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.springmvc")
@PropertySource("classpath:db.properties")
public class WebConfig implements WebMvcConfigurer {

    /**
     * Spring auto-injects the Environment bean (populated with values
     * from all @PropertySource files and system properties).
     * We use constructor injection (no field injection) as per best practices.
     */
    private final Environment env;

    public WebConfig(Environment env) {
        this.env = env;
    }

    // =====================================================================
    // 1. DATA SOURCE BEAN
    // =====================================================================

    /**
     * DataSource provides JDBC Connection objects to the DAO layer.
     *
     * WHY DriverManagerDataSource?
     * It is the simplest Spring DataSource — it creates a new physical
     * Connection each time getConnection() is called. Suitable for
     * development. For production consider HikariCP (connection pooling).
     *
     * WHY @Bean?
     * Without @Bean the method is just a plain Java method. @Bean tells
     * Spring to call this method ONCE, store the result, and inject it
     * wherever DataSource is needed.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        // env.getProperty() reads values from db.properties
        ds.setDriverClassName(env.getProperty("db.driver"));
        ds.setUrl(env.getProperty("db.url"));
        ds.setUsername(env.getProperty("db.username"));
        ds.setPassword(env.getProperty("db.password"));
        return ds;
    }

    // =====================================================================
    // 2. JSP VIEW RESOLVER BEAN
    // =====================================================================

    /**
     * InternalResourceViewResolver maps a logical view name like "login"
     * to a physical JSP path: /WEB-INF/views/login.jsp
     *
     * WHY prefix + suffix?
     *  - prefix = "/WEB-INF/views/"  → JSPs are inside WEB-INF so browsers
     *    cannot access them directly (security!).
     *  - suffix = ".jsp"             → auto-appended to the view name.
     *
     * WHY setViewClass(JstlView.class)?
     *  - JstlView enables JSTL expression language (${...}) and JSTL tags
     *    (<c:forEach>, <c:if>) in your JSPs.
     *
     * WHY setOrder(2)?
     *  - Spring tries view resolvers in ORDER (lowest number first).
     *  - ThymeleafViewResolver has order=1, so Thymeleaf is tried FIRST.
     *  - If Thymeleaf can't resolve the view (e.g. view name doesn't
     *    start with "thymeleaf/"), this JSP resolver handles it.
     *  - This is how both view technologies co-exist in one application.
     */
    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(2);       // Try AFTER Thymeleaf (order=1)
        resolver.setContentType("text/html; charset=UTF-8");
        return resolver;
    }

    // =====================================================================
    // 3. VIEW RESOLVER REGISTRY (optional override)
    // =====================================================================

    /**
     * We do NOT override configureViewResolvers() here because both
     * resolvers (JSP + Thymeleaf) are registered as @Bean with explicit
     * order values. Spring MVC auto-detects all ViewResolver beans.
     */

    // =====================================================================
    // 4. STATIC RESOURCE HANDLER
    // =====================================================================

    /**
     * WHY addResourceHandlers()?
     * Without this, the DispatcherServlet intercepts ALL requests
     * including requests for .css / .js / image files and tries to
     * find a controller for them — which fails.
     *
     * addResourceHandler("/resources/**")
     *   → any URL starting with /resources/ is a static resource request.
     *
     * addResourceLocations("/resources/")
     *   → files are served from src/main/webapp/resources/
     *
     * This means a browser request to:
     *   GET /GreetingApp/resources/css/style.css
     * is served from:
     *   src/main/webapp/resources/css/style.css
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(3600); // cache for 1 hour in browser
    }
}
