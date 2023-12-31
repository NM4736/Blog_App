package com.blog_Application.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileHandlingService {
    String uploadFile(String path, MultipartFile file) throws Exception;
    InputStream getFile(String path, String fileName) throws FileNotFoundException;


}
