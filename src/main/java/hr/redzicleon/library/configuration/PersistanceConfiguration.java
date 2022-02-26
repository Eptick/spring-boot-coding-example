package hr.redzicleon.library.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @EnableJpaAuditing enables the createdAt and updatedAt annotiations support
 * @EnableTransactionManagement enables the use of @Transactional annotation
 */
@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
public class PersistanceConfiguration { }
