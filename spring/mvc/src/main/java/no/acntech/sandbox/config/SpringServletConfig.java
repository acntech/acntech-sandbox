package no.acntech.sandbox.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
public class SpringServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringApplicationConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
