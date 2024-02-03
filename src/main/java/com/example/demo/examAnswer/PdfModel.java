package com.example.demo.examAnswer;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class PdfModel {
    private String name;
    private MultipartFile file;

    public PdfModel(){

    }
    public PdfModel(String name, MultipartFile file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
