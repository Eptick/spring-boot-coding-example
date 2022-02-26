package hr.redzicleon.library.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Sets up a in-memory concurrent HashMap cacher and scans the packages for
 * the @Cacheable annotation and sets up those caches
 */
@Configuration
@EnableCaching
public class CachingConfiguration {

}