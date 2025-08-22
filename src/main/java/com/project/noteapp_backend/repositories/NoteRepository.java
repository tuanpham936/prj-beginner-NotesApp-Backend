package com.project.noteapp_backend.repositories;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class NoteRepository {
    @Value("${localstorage.note}")
    private String localStoragePath;

    public Resource findNoteById(String id) {
        Resource file = new ClassPathResource(localStoragePath + "/" + id + ".txt");
        return file.exists() ? file : null;
    }
}
