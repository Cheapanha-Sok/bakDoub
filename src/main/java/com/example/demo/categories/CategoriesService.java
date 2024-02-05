package com.example.demo.categories;

import com.example.demo.ExamYear.ExamYear;
import com.example.demo.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;
    CategoriesService(CategoriesRepository categoriesRepository){
        this.categoriesRepository = categoriesRepository;
    }

    public ResponseEntity<Iterable<Categories>> getAllCategories() {
        return ResponseEntity.ok(categoriesRepository.findAll());
    }

    public ResponseEntity<Optional<Categories>> getCategories(Long categoriesId) {
        Optional<Categories> categories = categoriesRepository.findById(categoriesId);
        if (categories.isPresent()){
            return ResponseEntity.ok(categories);
        }
        throw new NotFoundException("Categories not found with id=" + categoriesId + "not found");
    }
    public ResponseEntity<Optional<Categories>> getCategoriesByCategoriesName(String categoriesName) {
        Optional<Categories> categories = categoriesRepository.findCategoriesByCategoriesName(categoriesName);
        if (categories.isPresent()){
            return ResponseEntity.ok(categories);
        }
        throw new NotFoundException("Categories with name="+ categoriesName + "not found");
    }
    @Transactional
    public ResponseEntity<URI> createCategories(Categories categories) {
        categoriesRepository.save(categories);
        return ResponseEntity.created(URI.create("/api/v1/categories/" + categories.getCategoriesId())).build();
    }
    @Transactional
    public ResponseEntity<HttpStatus> deleteCategories(Long categoriesId) {
        try{
            boolean isExist = categoriesRepository.existsById(categoriesId);
            if (isExist){
                categoriesRepository.deleteById(categoriesId);
                return ResponseEntity.noContent().build();
            }
            throw new NotFoundException("Categories with id="+ categoriesId + "not found");
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }

    }
    @Transactional
    public ResponseEntity<Categories> updateCategories(Long categoriesId, ExamYear updatedExamYear) {
        return null;
    }


}
