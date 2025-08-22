package com.project.noteapp_backend.services;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.project.noteapp_backend.repositories.NoteRepository;

@Service
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
}
