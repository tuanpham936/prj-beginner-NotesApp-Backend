package com.project.noteapp_backend.controller.rest;

import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.noteapp_backend.services.NoteServices;

@RestController
@RequestMapping("/note")
@CrossOrigin(origins = "http://localhost:5173")
public class NoteRestController {
    private final NoteServices noteServices;

    public NoteRestController(NoteServices noteServices) {
        this.noteServices = noteServices;
    }

    @GetMapping("/{id}")
    public Resource getNote(@PathVariable String id) {
        return noteServices.GetNote(id);
    }
}
