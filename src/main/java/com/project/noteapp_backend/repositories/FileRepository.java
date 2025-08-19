package com.project.noteapp_backend.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.noteapp_backend.models.File;
import com.project.noteapp_backend.models.Folder;
import jakarta.transaction.Transactional;

@Repository
public interface FileRepository extends CrudRepository<Folder, String> {
    @Query(value = "SELECT * FROM files WHERE folder_id = :folderId", nativeQuery = true)
    public List<File> findFilesByFolderId(@Param("folderId") String folderId);

    
    @Query(value = "SELECT * FROM files WHERE id = :id", nativeQuery = true)
    public List<File> findFileById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO files VALUES (:id, :folderId, :name)", nativeQuery = true)
    public void postFile(@Param("id") String id, @Param("folderId") String folderId, @Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE files SET folder_id = :folderId, name = :name WHERE id = :id", nativeQuery = true)
    public void putFile(@Param("id") String id, @Param("folderId") String folderId, @Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM files WHERE id = :id and folder_id = :folderId", nativeQuery = true)
    public void deleteFile(@Param("id") String id, @Param("folderId") String folderId);
}
