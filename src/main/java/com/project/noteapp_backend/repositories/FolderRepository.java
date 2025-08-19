package com.project.noteapp_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.noteapp_backend.models.Folder;

import jakarta.transaction.Transactional;

@Repository
public interface FolderRepository extends CrudRepository<Folder, String>{
    @Query(value = "SELECT * FROM folder", nativeQuery = true)
    public List<Folder> findAllFolders();

    @Query(value = "SELECT * FROM folder WHERE folder.id = :id", nativeQuery = true)
    public List<Folder> findFolderById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO folder VALUES (:id, :name)", nativeQuery = true)
    public void insertFolder(@Param("id") String id, @Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE folder SET name = :name WHERE id = :id", nativeQuery = true)
    public void updateFolder(@Param("id") String id, @Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM folder WHERE id = :id", nativeQuery = true)
    public void deleteFolder(@Param("id") String id);
}
