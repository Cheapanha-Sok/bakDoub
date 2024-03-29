package com.example.demo.examAnswer;
import com.example.demo.ExamYear.ExamYear;
import com.example.demo.ExamYear.ExamYearRepository;
import com.example.demo.categories.Categories;
import com.example.demo.categories.CategoriesRepository;
import com.example.demo.cloudinary.CloudinaryService;
import com.example.demo.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExamAnswerService {
    private final CloudinaryService cloudinaryService;
    private final ExamAnswerRepository examAnswerRepository;
    private final ExamYearRepository examYearRepository;
    private final CategoriesRepository categoriesRepository;
    ExamAnswerService(ExamAnswerRepository examAnswerRepository , CloudinaryService cloudinaryService , ExamYearRepository examYearRepository , CategoriesRepository categoriesRepository){
        this.examAnswerRepository = examAnswerRepository;
        this.cloudinaryService = cloudinaryService;
        this.examYearRepository = examYearRepository;
        this.categoriesRepository = categoriesRepository;
    }
    public ResponseEntity<Iterable<ExamAnswer>> getAllExamAnswer(){
        return ResponseEntity.ok(examAnswerRepository.findAll());
    }
    public ResponseEntity<Optional<ExamAnswer>> getExamAnswer(Long examAnswerId){
        Optional<ExamAnswer> examAnswer = examAnswerRepository.findById(examAnswerId);
        if (examAnswer.isPresent()){
            return ResponseEntity.ok(examAnswer);
        }
        throw new NotFoundException("ExamAnswer with id=" + examAnswerId + "not found");
    }
    @Transactional
    public ResponseEntity<HttpStatus> deleteExamAnswer(Long examAnswerId){
        boolean isExist = examAnswerRepository.existsById(examAnswerId);
        if(isExist){
            examAnswerRepository.deleteById(examAnswerId);
            return ResponseEntity.noContent().build();
        }
        throw new NotFoundException("ExamAnswer with id=" + examAnswerId + "not found");
    }
    @Transactional
    public ResponseEntity<String> createExamAnswer(PdfModel pdfModel , LocalDate examDate , String categoriesName){
        Optional<ExamYear> examYear = examYearRepository.findExamYearByExamDate(examDate);
        Optional<Categories> categories = categoriesRepository.findCategoriesByCategoriesName(categoriesName);
        if (examYear.isPresent()){
            if (categories.isPresent()){
                ExamAnswer examAnswer = new ExamAnswer();
                examAnswer.setExamYears(List.of(examYear.get()));
                examAnswer.setCategories(categories.get());
                examAnswer.setPdfUrl(cloudinaryService.uploadFile(pdfModel.getFile() , examDate.toString()));
                if (examAnswer.getPdfUrl() == null){
                    return ResponseEntity.badRequest().build();
                }
                examAnswerRepository.save(examAnswer);
                return ResponseEntity.ok(String.format("Url: %s",examAnswer.getPdfUrl()));
            }
            throw  new NotFoundException("Categories  with categoriesName" + categoriesName + "not found");
        }
        throw  new NotFoundException("Exam year  with Date" + examDate + "not found");
    }
}