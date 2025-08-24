package com.project.noteapp_backend.repositories;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileWriter;

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
        String fileName = id + ".txt";
        
        System.out.println(2);
        File file = new File(localStorageAbsolutePath, fileName);
        FileWriter writer = new FileWriter(file);
        writer.write(note.getResource().getContentAsString(null));
        writer.close();
        System.out.println(3);
    }
}
