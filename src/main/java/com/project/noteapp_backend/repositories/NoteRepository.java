package com.project.noteapp_backend.repositories;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class NoteRepository {
    @Value("${localstorage.relative}")
    private String localStorageRelativePath;
    @Value("${localstorage.absolute}")
    private String localStorageAbsolutePath;

    public Resource findNoteById(String id) {
        Resource file = new ClassPathResource(localStorageRelativePath + "/" + id + ".txt");
        return file.exists() ? file : null;
    }

    public void postNote(String id, MultipartFile note) throws IOException {
        File folder = new File(localStorageAbsolutePath);
        if (!folder.exists()) {
            System.out.println(1);
            folder.mkdirs();
        }
        
        File file = new File(folder, note.getOriginalFilename());
        
        // note.transferTo(file);
        Files.write(Paths.get(file.getAbsolutePath()), note.getBytes());
    }
}
