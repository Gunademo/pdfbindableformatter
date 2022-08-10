package com.example.demo.controller;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class SpringFileUploadController {
    String location = "";
    File file1 = null;
    String fileName = "";

    @GetMapping("/index")
    public String hello() {
        System.out.println("I got the call");
        return "uploader";
    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {

        String fileName = file.getOriginalFilename();

        location = "C:\\Users\\GVUDARAP\\Desktop\\pdfbindableformatter\\src\\main\\resources\\static\\" + fileName;
        try {
//src/main/java/com/example/demo/PdFbindableFormatterApplication.java
            file1 = new File("../../main/resources/static/download/" + fileName);
            file.transferTo(file1);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }


    @GetMapping(value = "/process", produces = "application/pdf")
    public ResponseEntity<?> getImageWithMediaType(HttpServletResponse response) throws IOException {
//        location = "C:\\Users\\GVUDARAP\\Desktop\\PDF_Binding\\WS\\src\\" + fileName;
        location = "C:\\Users\\GVUDARAP\\Desktop\\pdfbindableformatter\\src\\main\\resources\\static\\Resisting_Happiness.pdf";

        Path path = Paths.get(location);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
//            PDFBinding.processFile();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}