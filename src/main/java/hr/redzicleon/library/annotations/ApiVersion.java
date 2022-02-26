package hr.redzicleon.library.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to version the api, inserts the version prefix infront of the route
 * 
 * Example: @ApiVersion("1") 
 * Produces: /v1/hello-world route matching
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {
    int[] value();
}