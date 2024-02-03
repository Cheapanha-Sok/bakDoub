package com.example.demo.examAnswer;
import com.example.demo.ExamYear.ExamYear;
import com.example.demo.categories.Categories;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "exam_answer")
public class ExamAnswer {
    @Id
    @Column(name = "exam_answer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examAnswerId;
    @Column(name = "exam_answer_name" , unique = true)
    private String examAnswerName;
    @Column(name = "pdf_url" , unique = true)
    private String pdfUrl;

    public ExamAnswer(){

    }
    public ExamAnswer(Long examAnswerId , String examAnswerName , String pdfUrl){
        this.examAnswerId = examAnswerId;
        this.examAnswerName = examAnswerName;
        this.pdfUrl = pdfUrl;
    }
    public ExamAnswer(String examAnswerName , String pdfUrl){
        this.examAnswerName = examAnswerName;
        this.pdfUrl = pdfUrl;
    }

    public Long getExamAnswerId() {
        return examAnswerId;
    }

    public void setExamAnswerId(Long examAnswerId) {
        this.examAnswerId = examAnswerId;
    }

    public String getExamAnswerName() {
        return examAnswerName;
    }

    public void setExamAnswerName(String examAnswerName) {
        this.examAnswerName = examAnswerName;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id" , referencedColumnName = "categories_id")
    private Categories categories;

    public List<ExamYear> getExamYears() {
        return examYears;
    }

    public void setExamYears(List<ExamYear> examYears) {
        this.examYears = examYears;
    }

    @ManyToMany(fetch = FetchType.LAZY ,cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "bakdoubAnswer" , joinColumns = @JoinColumn(name = "exam_answer_id") , inverseJoinColumns = @JoinColumn(name = "exam_year_id"))
    private List<ExamYear> examYears;

    @Override
    public String toString() {
        return "ExamAnswer{" +
                "examAnswerId=" + examAnswerId +
                ", examAnswerName='" + examAnswerName + '\'' +
                ", pdfUrl='" + pdfUrl + '\'' +
                '}';
    }
}
