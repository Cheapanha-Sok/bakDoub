package com.example.demo.categories;

import com.example.demo.examAnswer.ExamAnswer;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categories_id")
    private Long categoriesId;
    @Column(name = "categories_name")
    private String categoriesName;


    public Categories(){

    }
    Categories(Long categoriesId , String categoriesName){
        this.categoriesId = categoriesId;
        this.categoriesName = categoriesName;
    }
    Categories(String categoriesName){
        this.categoriesName = categoriesName;
    }


    public Long getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Long categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "categories" ,cascade = CascadeType.ALL)
    private List<ExamAnswer> examAnswers;

    @Override
    public String toString() {
        return "Categories{" +
                "categoriesId=" + categoriesId +
                ", categoriesName='" + categoriesName + '\'' +
                '}';
    }
}
