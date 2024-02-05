package com.example.demo.ExamYear;

import com.example.demo.examAnswer.ExamAnswer;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "exam_year")
public class ExamYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_year_id")
    private Long examYearId;
    @Column(name = "exam_date" , unique = true)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate examDate;


    public ExamYear(Long examYearId , LocalDate examDate){
        this.examYearId = examYearId;
        this.examDate = examDate;
    }
    public ExamYear(LocalDate examDate){
        this.examDate = examDate;
    }

    public ExamYear() {

    }

    public Long getExamYearId() {
        return examYearId;
    }

    public void setExamYearId(Long examYearId) {
        this.examYearId = examYearId;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }
    @ManyToMany(mappedBy = "examYears" , fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private List<ExamAnswer> examAnswers;

    @Override
    public String toString() {
        return "ExamYear{" +
                "examYearId=" + examYearId +
                ", examDate=" + examDate +
                '}';
    }
}