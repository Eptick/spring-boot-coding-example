package hr.redzicleon.library.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import hr.redzicleon.library.domain.Report;
import hr.redzicleon.library.services.NotificationService;
import hr.redzicleon.library.services.ReportService;

@Component
public class ScheduledTasks {
	private final ReportService reportService;
	private final NotificationService notificationService;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	public ScheduledTasks(ReportService reportService, NotificationService notificationService) {
		this.reportService = reportService;
		this.notificationService = notificationService;
	}

	@Scheduled(fixedRate = 3600000)
	public void reportNewBooks() {
		log.info("Reporting new books at: {}", dateFormat.format(new Date()));
		Report r = this.reportService.generateNewBooksReport();
		this.notificationService.send(r.getContent());
	}
}