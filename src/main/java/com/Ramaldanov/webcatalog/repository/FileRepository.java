package com.Ramaldanov.webcatalog.repository;

import com.Ramaldanov.webcatalog.entity.UsersFile;
import com.Ramaldanov.webcatalog.exceptions.FileNameNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<UsersFile, Long> {
    UsersFile findByFileName(String fileName) throws FileNameNotFoundException;
}
