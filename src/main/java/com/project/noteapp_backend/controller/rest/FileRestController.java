package com.project.noteapp_backend.controller.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.noteapp_backend.models.File;
import com.project.noteapp_backend.services.FileServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "http://localhost:5173")
public class FileRestController {
    private final FileServices fileServices;

    public FileRestController(FileServices fileServices) {
        this.fileServices = fileServices;
    }

    @GetMapping("/{id}")
    public List<File> getFilesByFolderId(@PathVariable String id) {
        return fileServices.GetFilesByFolderId(id);
    }

    @PostMapping("")
    public ResponseEntity<File> postNewFile(@RequestBody File file) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(fileServices.PostNewFile(file));
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity putFile(@RequestBody File file) {
        try {
            if (fileServices.UpdateFile(file)) return ResponseEntity.status(200).body(null);
            else return ResponseEntity.status(400).body(null);
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
    @DeleteMapping("")
    public ResponseEntity deleteFile(@RequestBody File file) {
        try {
            if (fileServices.DeleteFile(file)) return ResponseEntity.status(200).body(null);
            else return ResponseEntity.status(400).body(null);
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
