package com.example.seabattlebacklocal.source;

import jakarta.annotation.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService{

    private String filePath="C:/Users/User/Downloads";
    public void save(MultipartFile file){

        String dir =filePath+"/"+file.getOriginalFilename();
        try{
            file.transferTo(new File(dir));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public Resource getFile(String name){
        String dir =filePath;
        Path path = Paths.get(dir);
        try {
            return (Resource) new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
