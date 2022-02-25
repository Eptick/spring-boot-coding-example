package hr.redzicleon.library.domain;

public enum ReportType {
    NewBooksReport(1);

    private final Integer id;

    ReportType(int id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }
}
