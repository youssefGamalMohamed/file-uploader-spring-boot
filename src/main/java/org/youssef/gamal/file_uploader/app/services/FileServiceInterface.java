package org.youssef.gamal.file_uploader.app.services;

import org.youssef.gamal.file_uploader.app.entities.File;

import java.util.List;
import java.util.Optional;

public interface FileServiceInterface {

    File save(File file);

    Optional<File> findById(Long id);

    File updateById(Long id, File file);

    void deleteById(Long id);

    Iterable<File> findAll();

    List<File> saveAll(List<File> files);
}
