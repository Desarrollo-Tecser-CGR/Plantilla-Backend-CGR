package com.cgr.bbp.infrastructure.persistence.repository.file;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgr.bbp.infrastructure.persistence.entity.file.FileEntity;

public interface IFileRepository extends JpaRepository<FileEntity, Integer> {
    
}
