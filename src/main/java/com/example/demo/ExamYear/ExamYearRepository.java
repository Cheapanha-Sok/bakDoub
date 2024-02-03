package com.example.demo.ExamYear;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamYearRepository extends JpaRepository<ExamYear , Long> {
}
