package com.example.demo.ExamYear;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ExamYearRepository extends JpaRepository<ExamYear , Long> {
    Optional<ExamYear> findExamYearByExamDate(Date examDate);
}
