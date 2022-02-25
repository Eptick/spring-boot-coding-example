package hr.redzicleon.library.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StubNotificationService implements NotificationService {
	private static final Logger log = LoggerFactory.getLogger(StubNotificationService.class);

    public void send(String content) {
        // noop
        log.info(content);
    }
}
