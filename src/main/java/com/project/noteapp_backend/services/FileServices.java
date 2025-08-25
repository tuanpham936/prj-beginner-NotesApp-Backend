package com.project.noteapp_backend.services;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.project.noteapp_backend.models.File;
import com.project.noteapp_backend.repositories.FileRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class FileServices {
    private final FileRepository fileRepository;
    private final NoteServices noteServices;

    public FileServices(FileRepository fileRepository, NoteServices noteServices) {
        this.fileRepository = fileRepository;
        this.noteServices = noteServices;
    }

    public List<File> GetFilesByFolderId(String folderId) {
        return fileRepository.findFilesByFolderId(folderId);
    }

    public File PostNewFile(File f) {
        f.setId(UUID.randomUUID().toString());
        fileRepository.postFile(f.getId(), f.getFolderId(), f.getName());
        return f;
    }

    public boolean UpdateFile(File f) {
        if (fileRepository.findFileById(f.getId()).isEmpty()) return false;

        fileRepository.putFile(f.getId(), f.getFolderId(), f.getName());
        return true;
    }

    public boolean DeleteFile(File f) {
        if (fileRepository.findFileById(f.getId()).isEmpty()) return false;
        
        noteServices.DeleteNote(f.getId());
        fileRepository.deleteFile(f.getId(), f.getFolderId());
        return true;
    }
}
