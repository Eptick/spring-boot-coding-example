package hr.redzicleon.library.domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class ReportBuilder {
    private ReportType type;
    // LinkedHashSet retains order of addition
    private Set<String> content = new LinkedHashSet<String>();

    public ReportBuilder(ReportType type) {
        this.type = type;
    }

    public ReportBuilder addText(String text) {
        content.add(text);
        return this;
    }

    public Report build() {
        Report report = new Report();
        report.setId(this.type.getId());
        report.setContent(this.content.stream().reduce("", (acc, elem) -> acc + elem + "\n"));
        return report;
    }

}
