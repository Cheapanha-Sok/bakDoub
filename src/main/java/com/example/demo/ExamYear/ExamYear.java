package com.example.demo.ExamYear;

import com.example.demo.examAnswer.ExamAnswer;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "exam_year")
public class ExamYear {
    @Id
    @Column(name = "exam_year_id")
    private Long examYearId;
    @Column(name = "exam_date" , unique = true)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date examDate;

    public ExamYear(Long examYearId , Date examDate){
        this.examYearId = examYearId;
        this.examDate = examDate;
    }
    public ExamYear(Date examDate){
        this.examYearId = examYearId;
    }

    public ExamYear() {

    }

    public Long getExamYearId() {
        return examYearId;
    }

    public void setExamYearId(Long examYearId) {
        this.examYearId = examYearId;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }
    @ManyToMany(mappedBy = "examYears" , fetch = FetchType.LAZY)
    private List<ExamAnswer> examAnswers;

    @Override
    public String toString() {
        return "ExamYear{" +
                "examYearId=" + examYearId +
                ", examDate=" + examDate +
                '}';
    }
}