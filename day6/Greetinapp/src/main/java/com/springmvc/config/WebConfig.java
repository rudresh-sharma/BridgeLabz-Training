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

	    String driver = System.getenv("DB_DRIVER");
	    String url = System.getenv("DB_URL");
	    String username = System.getenv("DB_USERNAME");
	    String password = System.getenv("DB_PASSWORD");

	    // Local STS fallback
	    if (driver == null || driver.isBlank()) {
	        driver = "com.mysql.cj.jdbc.Driver";
	    }

	    if (url == null || url.isBlank()) {
	        url = "jdbc:mysql://localhost:3306/greeting_app_db";
	    }

	    if (username == null || username.isBlank()) {
	        username = "root";
	    }

	    if (password == null || password.isBlank()) {
	        password = "Rudresh@2005";
	    }

	    ds.setDriverClassName(driver);
	    ds.setUrl(url);
	    ds.setUsername(username);
	    ds.setPassword(password);

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