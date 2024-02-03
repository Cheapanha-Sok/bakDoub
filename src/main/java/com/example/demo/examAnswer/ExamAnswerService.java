package com.example.demo.examAnswer;
import com.example.demo.ExamYear.ExamYear;
import com.example.demo.ExamYear.ExamYearRepository;
import com.example.demo.cloudinary.CloudinaryService;
import com.example.demo.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ExamAnswerService {
    private final CloudinaryService cloudinaryService;
    private final ExamAnswerRepository examAnswerRepository;
    private final ExamYearRepository examYearRepository;
    @Autowired
    ExamAnswerService(ExamAnswerRepository examAnswerRepository , CloudinaryService cloudinaryService , ExamYearRepository examYearRepository){
        this.examAnswerRepository = examAnswerRepository;
        this.cloudinaryService = cloudinaryService;
        this.examYearRepository = examYearRepository;
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
    public ResponseEntity<HttpStatus> deleteExamAnswer(Long examAnswerId){
        boolean isExist = examAnswerRepository.existsById(examAnswerId);
        if(isExist){
            examAnswerRepository.deleteById(examAnswerId);
            return ResponseEntity.noContent().build();
        }
        throw new NotFoundException("ExamAnswer with id=" + examAnswerId + "not found");
    }
    public ResponseEntity<String> createExamAnswer(PdfModel pdfModel , Long examYearId){
        Optional<ExamYear> examYear = examYearRepository.findById(examYearId);
        if (examYear.isPresent()){
            if (pdfModel.getName() == null || pdfModel.getName().isEmpty() || pdfModel.getName().isBlank()){
                return ResponseEntity.badRequest().build();
            }if (pdfModel.getFile().isEmpty() || pdfModel.getFile() == null){
                return ResponseEntity.badRequest().build();
            }
            ExamAnswer examAnswer = new ExamAnswer();
            examAnswer.setExamAnswerName(pdfModel.getName());
            examAnswer.setPdfUrl(cloudinaryService.uploadFile(pdfModel.getFile() , examYear.get().toString()));
            if (examAnswer.getPdfUrl() == null){
                return ResponseEntity.badRequest().build();
            }
            examAnswerRepository.save(examAnswer);
            return ResponseEntity.ok(String.format("Url: %s",examAnswer.getPdfUrl()));
        }
        throw  new NotFoundException("Exam year with id" + examYearId + "not found");


    }
}