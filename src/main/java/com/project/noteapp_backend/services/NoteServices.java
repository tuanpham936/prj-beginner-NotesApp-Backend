package com.project.noteapp_backend.services;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.noteapp_backend.repositories.NoteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NoteServices {
    private final NoteRepository noteRepository;

    public NoteServices(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Resource GetNote(String id) {
        try {
            Resource note = noteRepository.findNoteById(id);
            if (note.exists()) {
                return note;
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            return null;
        }
    }

    public boolean PostNewNote(String id, MultipartFile note) throws IOException {
        if (noteRepository.findNoteById(id) != null) return false;

        noteRepository.postNote(id, note);
        return true;
    }

    public boolean UpdateNote(String id, MultipartFile note) throws IOException {
        if (noteRepository.findNoteById(id) == null) return false;

        noteRepository.updateNote(id, note);
        return true;
    }

    public boolean DeleteNote(String id) {
        if (noteRepository.findNoteById(id) == null) return true;

        noteRepository.deleteNote(id);
        return true;
    }
}
