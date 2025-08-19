package com.project.noteapp_backend.services;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.project.noteapp_backend.models.Folder;
import com.project.noteapp_backend.repositories.FolderRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class FolderServices {
    private final FolderRepository folderRepository;

    public FolderServices(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public List<Folder> GetAllFolders() {
        return folderRepository.findAllFolders();
    }

    public Folder InsertNewFolder(Folder f) {
        f.setId(UUID.randomUUID().toString());
        folderRepository.insertFolder(f.getId(), f.getName());
        return f;
    }

    public boolean updateFolder(String id, Folder f) {
        if (folderRepository.findFolderById(id).size() <= 0) return false;

        folderRepository.updateFolder(id, f.getName());
        return true;
    }

    public boolean deleteFolder(String id) {
        if (folderRepository.findFolderById(id).size() <= 0) return false;

        folderRepository.deleteFolder(id);
        return true;
    }
}
