package hr.redzicleon.library.services;

import java.util.Date;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import hr.redzicleon.library.domain.Book;
import hr.redzicleon.library.domain.QBook;
import hr.redzicleon.library.domain.Report;
import hr.redzicleon.library.domain.ReportBuilder;
import hr.redzicleon.library.domain.ReportType;
import hr.redzicleon.library.repository.BooksRepository;
import hr.redzicleon.library.repository.ReportRepository;

@Service
public class ReportServiceImpl implements ReportService {
    BooksRepository booksRepository;
    ReportRepository reportRepository;
    ModelMapper mapper;

    public ReportServiceImpl(BooksRepository booksRepository, ReportRepository reportRepository, ModelMapper mapper) {
        this.booksRepository = booksRepository;
        this.reportRepository = reportRepository;
        this.mapper = mapper;
    }

    public Report generateNewBooksReport() {
        ReportBuilder reportBuilder = new ReportBuilder(ReportType.NewBooksReport);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        Date lastExecutionDate = this.getLastExecutionDate(ReportType.NewBooksReport.getId());
        if(lastExecutionDate != null) {
            booleanBuilder.and(QBook.book.createdAt.gt(lastExecutionDate));
        }
        PageRequest pageable = PageRequest.of(0, 100);
        Page<Book> page;
        reportBuilder.addText("Report generated at " + (new Date()).toString());
        do {
            page = this.booksRepository.findAll(booleanBuilder, pageable);
            if(page.hasContent()) {
                reportBuilder.addText(
                    page.stream()
                    .map(e -> e.getISBN())
                    .reduce("", (acc, elem) -> acc + elem + "\n")
                );
                
            }

        } while(page.hasNext());
        Report report = reportBuilder.build();
        this.reportExecuted(report.getId());
        return report;
    }

    private Date getLastExecutionDate(Integer id) {
        Report report = this.getReportObject(id);
        return report.getLastExecutionDate();
    }

    private void reportExecuted(Integer id) {
        Report report = this.getReportObject(id);
        report.setLastExecutionDate(new Date());
        this.reportRepository.save(report);
    }

    private Report getReportObject(Integer id) {
        Optional<Report> report = this.reportRepository.findById(id);
        if (!report.isPresent()) {
            return this.createReport(id);
        } else {
            return report.get();
        }
    }

    private Report createReport(Integer id) {
        Report r = new Report();
        r.setId(id);
        return this.reportRepository.save(r);
    }

}
