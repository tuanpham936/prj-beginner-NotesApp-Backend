package com.project.noteapp_backend.controller.rest;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.noteapp_backend.models.Folder;
import com.project.noteapp_backend.services.FolderServices;

@RestController
@RequestMapping("/folder")
@CrossOrigin(origins = "http://localhost:5173")
public class FolderRestController {
    private final FolderServices folderServices;

    public FolderRestController(FolderServices folderServices) {
        this.folderServices = folderServices;
    }

    @GetMapping("")
    public List<Folder> getFolders() {
        System.out.println(1);
        return folderServices.GetAllFolders();
    }

    @PostMapping("")
    public ResponseEntity<Folder> postFolder(@RequestBody Folder folder) {
        return ResponseEntity.status(HttpStatus.CREATED).body(folderServices.InsertNewFolder(folder));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity putFolder(@PathVariable String id, @RequestBody Folder f) {
        if (folderServices.updateFolder(id, f)) return ResponseEntity.ok(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFolder(@PathVariable String id) {
        if (folderServices.deleteFolder(id)) return ResponseEntity.ok(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
