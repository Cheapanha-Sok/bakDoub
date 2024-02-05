package com.example.demo.examAnswer;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class PdfModel {
    private MultipartFile file;
}
