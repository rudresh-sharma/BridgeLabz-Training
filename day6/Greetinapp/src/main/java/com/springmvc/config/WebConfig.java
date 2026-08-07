package com.springmvc.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.springmvc")
public class WebConfig implements WebMvcConfigurer {


    // ============================================================
    // 1. DATABASE CONNECTION
    // ============================================================

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setDriverClassName(
                System.getenv("DB_DRIVER")
        );

        ds.setUrl(
                System.getenv("DB_URL")
        );

        ds.setUsername(
                System.getenv("DB_USERNAME")
        );

        ds.setPassword(
                System.getenv("DB_PASSWORD")
        );

        return ds;
    }



    // ============================================================
    // 2. JSP VIEW RESOLVER
    // ============================================================

    @Bean
    public InternalResourceViewResolver jspViewResolver() {

        InternalResourceViewResolver resolver =
                new InternalResourceViewResolver();

        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(2);
        resolver.setContentType("text/html;charset=UTF-8");

        return resolver;
    }



    // ============================================================
    // 3. STATIC RESOURCES (CSS / JS / IMAGES)
    // ============================================================

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(3600);
    }

}