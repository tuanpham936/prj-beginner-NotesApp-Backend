package com.project.noteapp_backend.controller.rest;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.project.noteapp_backend.services.NoteServices;
import org.springframework.web.bind.annotation.PostMapping;


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
    
    @PostMapping("/{id}")
    public ResponseEntity postNote(@PathVariable String id, @RequestParam("note") MultipartFile note) {
        System.out.println(0);
        try {
            if (noteServices.PostNewNote(id, note)) return ResponseEntity.status(201).body(null);
            else return ResponseEntity.status(400).body(null);
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
}
