package com.blog_Application.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class Common
{
    Logger Log= LoggerFactory.getLogger(Common.class);
    public void validateFileExtension(List<String> extensions, String fileName) throws Exception {
        String ext= fileName.substring(fileName.indexOf(".")+1);
        if(extensions.contains(ext)) {
            Log.info("file format is "+ext);
            return ;
        }
        throw new Exception("file format "+ext+" is invalid");
    }

    public Boolean isfilePresent(String filePath) throws Exception {
       File f= new File(filePath);
        if(f.exists())
        {
            Log.info("file already exists");
          return true;
        }
        return false;
    }
}
