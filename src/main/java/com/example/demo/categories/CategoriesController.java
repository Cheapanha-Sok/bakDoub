package com.example.demo.categories;

import com.example.demo.ExamYear.ExamYear;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/categories/")
public class CategoriesController {
    private final CategoriesService categoriesService;
    CategoriesController(CategoriesService categoriesService){
        this.categoriesService = categoriesService;
    }
    @GetMapping
    public ResponseEntity<Iterable<Categories>> getAllCategories() {
        return (categoriesService.getAllCategories());
    }

    @GetMapping("{categories_id}")
    public ResponseEntity<Optional<Categories>> getCategories(@PathVariable("categories_id") Long categoriesId) {
        return categoriesService.getCategories(categoriesId);
    }
    @GetMapping("categoriesName/{categories_name}")
    public ResponseEntity<Optional<Categories>> getCategoriesByCategoriesName(@PathVariable("categories_name") String categoriesName) {
        return categoriesService.getCategoriesByCategoriesName(categoriesName.toLowerCase());
    }
    @PostMapping
    public ResponseEntity<URI> createCategories(@RequestBody Categories categories) {
        return categoriesService.createCategories(categories);
    }

    @DeleteMapping("{categories_id}")
    public ResponseEntity<HttpStatus> deleteCategories(@PathVariable("categories_id")  Long categoriesId) {
        return categoriesService.deleteCategories(categoriesId);
    }

    @PutMapping("{categories_id}")
    public ResponseEntity<Categories> updateCategories(@PathVariable("categories_id") Long categoriesId, @RequestBody ExamYear updatedExamYear) {
        return categoriesService.updateCategories(categoriesId, updatedExamYear);
    }

}
