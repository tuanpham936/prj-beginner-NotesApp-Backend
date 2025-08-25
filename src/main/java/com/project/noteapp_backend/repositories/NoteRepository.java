package com.project.noteapp_backend.repositories;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class NoteRepository {
    @Value("${localstorage.relative}")
    private String localStorageRelativePath;

    public Resource findNoteById(String id) {
        File folder = new File(localStorageRelativePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        Resource file = new FileSystemResource(localStorageRelativePath + "/" + id + ".txt");
        return file.exists() ? file : null;
    }

    public void postNote(String id, MultipartFile note) throws IOException {
        File folder = new File(localStorageRelativePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        
        File file = new File(folder, note.getOriginalFilename());
        
        // note.transferTo(file);
        Files.write(Paths.get(file.getAbsolutePath()), note.getBytes());
    }

    public void updateNote(String id, MultipartFile note) throws IOException {
        File folder = new File(localStorageRelativePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(localStorageRelativePath + "/" + id + ".txt");

        Files.write(Paths.get(file.getAbsolutePath()), note.getBytes());
    }

    public void deleteNote(String id) {
        File folder = new File(localStorageRelativePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(localStorageRelativePath + "/" + id + ".txt");
        file.delete();
    }
}
