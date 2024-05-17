package org.youssef.gamal.file_uploader.app.services;

import org.youssef.gamal.file_uploader.app.entities.Type;

import java.util.Optional;

public interface TypeServiceInterface {

    Type save(Type type);

    Optional<Type> findById(Long id);

    Type updateById(Long id, Type type);

    void deleteById(Long id);

    Optional<Type> findByTypeName(String type);

    Iterable<Type> findAll();
}
