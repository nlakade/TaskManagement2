 package com.taskmanagement;

// import org.springframework.boot.builder.SpringApplicationBuilder;
// import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// public class ServletInitializer extends SpringBootServletInitializer {

// 	@Override
// 	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
// 		return application.sources(TaskManagementApplication.class);
// 	}

// }
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

public class ServletInitializer extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TaskManagementApplication.class);
    }

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }
}
