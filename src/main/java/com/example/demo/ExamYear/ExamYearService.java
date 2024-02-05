package com.example.demo.ExamYear;


import com.example.demo.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
public class ExamYearService {

    private final ExamYearRepository examYearRepository;

    ExamYearService(ExamYearRepository examYearRepository){
        this.examYearRepository = examYearRepository;
    }

    public ResponseEntity<Iterable<ExamYear>>getAllExamYear(){
        return ResponseEntity.ok(examYearRepository.findAll());
    }
    public ResponseEntity<Optional<ExamYear>> getExamYear(Long examYearId){
        Optional<ExamYear> examYear = examYearRepository.findById(examYearId);
        if (examYear.isPresent()){
            return ResponseEntity.ok(examYear);
        }
        throw new NotFoundException("ExamYear with id=" + examYearId + "not found");

    }
    @Transactional
    public ResponseEntity<URI> createExamYear(ExamYear examYear) {
        examYearRepository.save(examYear);
        return ResponseEntity.created(URI.create("/api/v1/examYear/" + examYear.getExamYearId())).build();
    }
    @Transactional
    public ResponseEntity<HttpStatus> removeExamYear(Long examYearId) {
        boolean isExist = examYearRepository.existsById(examYearId);
        if (isExist){
            examYearRepository.deleteById(examYearId);
            return ResponseEntity.noContent().build();
        }
        throw new NotFoundException("ExamYear with id=" + examYearId + "not found");
    }
    @Transactional
    public ResponseEntity<ExamYear> updatedExamYear(Long examYearId, ExamYear updatedExamYear) {
        Optional<ExamYear> examYear = examYearRepository.findById(examYearId);
        if (examYear.isPresent()){
            ExamYear existingExamYear = examYear.get();
            if (updatedExamYear.getExamDate() != null){
                existingExamYear.setExamDate(updatedExamYear.getExamDate());
            }
            examYearRepository.save(existingExamYear);
            return ResponseEntity.ok(existingExamYear);
        }
        throw new NotFoundException("ExamYear with id=" + examYearId + "not found");
    }
}
