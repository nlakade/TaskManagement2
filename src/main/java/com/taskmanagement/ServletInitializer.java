package com.taskmanagement;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ServletInitializer extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected Class<TaskManagementApplication> getApplicationClass() {
        return TaskManagementApplication.class;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(TaskManagementApplication.class);

        servletContext.addListener(new ContextLoaderListener(context));

        SpringApplication application = new SpringApplication(getApplicationClass());
        application.setWebApplicationType(SpringBootApplication.WebApplicationType.SERVLET);
        application.run(context.getDefaultConfigLocations());
    }
}
