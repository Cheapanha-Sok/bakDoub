package com.example.demo.examAnswer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/v1/examAnswer/")
public class ExamAnswerController {
    private final ExamAnswerService examAnswerService;

    public ExamAnswerController(ExamAnswerService examAnswerService) {
        this.examAnswerService = examAnswerService;
    }
    @GetMapping
    public ResponseEntity<Iterable<ExamAnswer>> getAllExamAnswer() {
        return (examAnswerService.getAllExamAnswer());
    }

    @GetMapping("{examYearId}")
    public ResponseEntity<Optional<ExamAnswer>> getExamAnswer(@PathVariable("examYearId") Long examYearId) {
        return examAnswerService.getExamAnswer(examYearId);
    }
    @PostMapping("{exam_year_date}/{categories_name}")
    public ResponseEntity<String> createExamAnswer(
            PdfModel pdfModel,
            @PathVariable("exam_year_date") LocalDate examYearDate,
            @PathVariable("categories_name") String categories_name) {

        return examAnswerService.createExamAnswer(pdfModel, examYearDate, categories_name);
    }

    @DeleteMapping("{exam_year_id}")
    public ResponseEntity<HttpStatus> deleteExamAnswer(@PathVariable("exam_year_id")  Long examAnswerId) {
        return examAnswerService.deleteExamAnswer(examAnswerId);
    }

//    @PutMapping("{exam_year_id}")
//    public ResponseEntity<ExamAnswer> updateExamAnswer(@PathVariable("exam_year_id") Long examYearId, @RequestBody ExamYear updatedExamYear) {
//        return examAnswerService.updatedExamYear(examYearId, updatedExamYear);
//    }


}
