package com.example.demo.ExamYear;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface ExamYearRepository extends JpaRepository<ExamYear , Long> {
    Optional<ExamYear> findExamYearByExamDate(LocalDate examDate);
}
