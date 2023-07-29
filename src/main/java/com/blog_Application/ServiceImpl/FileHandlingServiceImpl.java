package com.blog_Application.ServiceImpl;
import com.blog_Application.Service.Common;
import com.blog_Application.Service.FileHandlingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileHandlingServiceImpl implements FileHandlingService {
    Logger Log= LoggerFactory.getLogger(FileHandlingServiceImpl.class);
    @Autowired
    Common common;
    @Override
    public String uploadFile(String path,MultipartFile file) throws Exception {
        /*List<String> extensions= new ArrayList<>();
        extensions.add("png");
        extensions.add("jpeg");
        extensions.add("jpg");
       common.validateFileExtension(extensions,file.getOriginalFilename());*/

       // file name
        String fileName=file.getOriginalFilename();

        //file Full path -->
        String filePath= path+ File.separator+fileName; // path\fileName something

        //create folder if not present
        File f= new File(path);
        if(!f.exists())
        {
            f.mkdir(); // folder will be created in c directory
        }

       /*Boolean status=common.isfilePresent(filePath);
        if(status)
        {
            File file1= new File(filePath);
            file1.delete();
        }*/
        // copy
        //Files.copy(source, destination path where data is to be copied)
        try(InputStream inputStream= file.getInputStream()) {
            Files.copy(inputStream, Paths.get(filePath));
        }
        catch(IOException e)
        {
            throw new Exception("file upload failed"+e.getMessage());
        }

        return fileName;

    }

    @Override
    public InputStream getFile(String path, String fileName) throws FileNotFoundException {
       String fullPath= path+File.separator+fileName;
       InputStream inputStream= new FileInputStream(fullPath);
       return inputStream;
    }


}
