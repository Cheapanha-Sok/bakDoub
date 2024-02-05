package com.example.demo.ExamYear;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/examYear/")
public class ExamYearController {
    private final ExamYearService examYearService;

    ExamYearController(ExamYearService examYearService){
        this.examYearService = examYearService;
    }
    @GetMapping
    public ResponseEntity<Iterable<ExamYear>> getAllExamYear() {
        return (examYearService.getAllExamYear());
    }

    @GetMapping("{examYearId}")
    public ResponseEntity<Optional<ExamYear>> getExamYear(@PathVariable("examYearId") Long examYearId) {
        return examYearService.getExamYear(examYearId);
    }

    @PostMapping
    public ResponseEntity<URI> createCourse(@RequestBody ExamYear examYear) {
        return examYearService.createExamYear(examYear);
    }

    @DeleteMapping("{exam_year_id}")
    public ResponseEntity<HttpStatus> deleteExamYear(@PathVariable("exam_year_id")  Long examYearId) {
        return examYearService.removeExamYear(examYearId);
    }

    @PutMapping("{exam_year_id}")
    public ResponseEntity<ExamYear> updateExamYear(@PathVariable("exam_year_id") Long examYearId, @RequestBody ExamYear updatedExamYear) {
        return examYearService.updatedExamYear(examYearId, updatedExamYear);
    }

}
