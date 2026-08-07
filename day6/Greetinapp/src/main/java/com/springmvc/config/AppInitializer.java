package com.springmvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * AppInitializer — replaces web.xml entirely.
 *
 * =====================================================================
 * WHY THIS CLASS EXISTS
 * =====================================================================
 * In traditional Servlet 2.x / 3.0 applications, a web.xml file was
 * mandatory to declare the DispatcherServlet (Spring's front controller).
 *
 * Since Servlet 3.0+, the Servlet specification introduced
 * "Servlet container initializers" via the ServiceLoader mechanism.
 * Spring MVC ships with SpringServletContainerInitializer which
 * automatically discovers and calls any class that implements
 * WebApplicationInitializer.
 *
 * AbstractAnnotationConfigDispatcherServletInitializer is a
 * convenient base class that implements WebApplicationInitializer
 * and wires up the DispatcherServlet for you — you only need to
 * tell it WHERE your Spring @Configuration classes are.
 *
 * =====================================================================
 * HOW SPRING USES IT
 * =====================================================================
 * On startup, Tomcat 11 scans the classpath for
 * META-INF/services/jakarta.servlet.ServletContainerInitializer.
 * Spring's entry there points to SpringServletContainerInitializer,
 * which then looks for WebApplicationInitializer implementations —
 * that's this class.
 *
 *  Tomcat
 *    └── SpringServletContainerInitializer
 *          └── AppInitializer (this class)
 *                ├── Root ApplicationContext (WebConfig)
 *                └── Servlet ApplicationContext (WebConfig) + DispatcherServlet
 *
 * =====================================================================
 * TWO APPLICATION CONTEXTS
 * =====================================================================
 * Spring MVC traditionally has two contexts:
 *
 * 1. Root ApplicationContext  (parent)
 *    Created by ContextLoaderListener.
 *    Contains services, DAOs, DataSource — shared beans.
 *    Configured by getRootConfigClasses().
 *
 * 2. Servlet ApplicationContext  (child)
 *    Created by DispatcherServlet.
 *    Contains controllers, view resolvers, handler mappings.
 *    Configured by getServletConfigClasses().
 *
 * In this app we merge both into WebConfig for simplicity,
 * but we still follow the two-method convention.
 */
public class AppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Root ApplicationContext configuration classes.
     *
     * Why? Services and DAOs belong here so they can be shared
     * across multiple DispatcherServlets (in larger apps).
     * WebConfig contains @ComponentScan that picks up service/dao beans.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    /**
     * Servlet (child) ApplicationContext configuration classes.
     *
     * Why? Controllers, ViewResolvers, and MVC-specific beans go here.
     * ThymeleafConfig sets up the Thymeleaf engine and view resolver.
     *
     * We return null here because everything is in getRootConfigClasses().
     * Alternatively, split WebConfig into two separate config classes.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { ThymeleafConfig.class };
    }

    /**
     * URL mappings for the DispatcherServlet.
     *
     * "/" means the DispatcherServlet handles ALL requests.
     * Static resources (CSS/JS) are handled separately
     * via WebConfig#addResourceHandlers().
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
