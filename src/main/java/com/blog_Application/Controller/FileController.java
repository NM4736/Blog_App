package com.blog_Application.Controller;

import com.blog_Application.Service.FileHandlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file/")
public class FileController
{
@Value("${project.image}")
    String path;

    @Autowired
    FileHandlingService fileHandlingService;
    @PostMapping("/imageUpload/")
    public ResponseEntity<String> uploadPostImage(@RequestPart("file") MultipartFile file) throws Exception {

        String fileName=fileHandlingService.uploadFile(path,file);

        return new ResponseEntity<>(fileName, HttpStatus.OK);

    }


}
