import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ServletInitializer extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TaskManagementApplication.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        SpringApplicationBuilder builder = configure(new SpringApplicationBuilder());
        builder.listeners(new TomcatServletWebServerFactory()).run(args);
    }
}
