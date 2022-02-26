package hr.redzicleon.library.configuration;

import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import hr.redzicleon.library.annotations.ApiVersion;

/**
 * Request Mapper that handles the @ApiVersion annotation and produces versioned
 * routes
 */
public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    private final String prefix;

    public ApiVersionRequestMappingHandlerMapping(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Gets the current mappping for the route
     * Then checks for the @ApiVersion annotation and if it has the anotation
     * then it produces the correct route
     */
    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = super.getMappingForMethod(method, handlerType);
        if (info == null) {
            return null;
        }

        ApiVersion methodAnnotation = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        if (methodAnnotation != null) {
            RequestCondition<?> methodCondition = getCustomMethodCondition(method);
            // Concatenate our ApiVersion with the usual request mapping
            info = createApiVersionInfo(methodAnnotation, methodCondition).combine(info);
        } else {
            ApiVersion typeAnnotation = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
            if (typeAnnotation != null) {
                RequestCondition<?> typeCondition = getCustomTypeCondition(handlerType);
                // Concatenate our ApiVersion with the usual request mapping
                info = createApiVersionInfo(typeAnnotation, typeCondition).combine(info);
            }
        }

        return info;
    }

    /**
     * Produces the version info @RequestMappingInfo
     * @param annotation @ApiVersion annotation
     * @param customCondition customconditions from getCustomTypeCondition
     * @return
     */
    private RequestMappingInfo createApiVersionInfo(ApiVersion annotation, RequestCondition<?> customCondition) {
        int[] values = annotation.value();
        String[] patterns = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            // Build the URL prefix
            patterns[i] = prefix + values[i];
        }
        return RequestMappingInfo.paths(patterns)
                .customCondition(customCondition).build();
    }

}