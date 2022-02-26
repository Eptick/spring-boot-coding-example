package hr.redzicleon.library.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


@Configuration
public class WebMvcConfiguration extends DelegatingWebMvcConfiguration  {
    /**
     * Enables the version anotation request mapping
     */
    @Override
    public RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new ApiVersionRequestMappingHandlerMapping("v");
    }
}