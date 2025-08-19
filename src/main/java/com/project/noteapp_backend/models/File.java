package com.project.noteapp_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class File {
    @Id
    private String id;
    private String folderId;
    private String name;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFolderId() {
        return folderId;
    }
    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
