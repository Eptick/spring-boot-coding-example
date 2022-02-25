package hr.redzicleon.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.redzicleon.library.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {
}
