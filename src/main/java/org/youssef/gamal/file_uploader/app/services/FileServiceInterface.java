package org.youssef.gamal.file_uploader.app.services;

import org.youssef.gamal.file_uploader.app.entities.File;
import org.youssef.gamal.file_uploader.app.entities.Type;

import java.util.Optional;

public interface FileServiceInterface {

    File save(File file);

    Optional<File> findById(Long id);

    File updateById(Long id, File file);

    void deleteById(Long id);

    Iterable<File> findAll();
}
