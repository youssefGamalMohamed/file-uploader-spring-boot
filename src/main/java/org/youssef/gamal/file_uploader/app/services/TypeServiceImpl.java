package org.youssef.gamal.file_uploader.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youssef.gamal.file_uploader.app.entities.Type;
import org.youssef.gamal.file_uploader.app.repos.TypeRepo;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TypeServiceImpl implements TypeServiceInterface {

    private final TypeRepo typeRepo;

    @Override
    public Type save(Type type) {
        return typeRepo.save(type);
    }

    @Override
    public Optional<Type> findById(Long id) {
        return Optional.of(
                typeRepo.findById(id)
                    .orElseThrow()
        );
    }

    @Override
    public Type updateById(Long id, Type type) {
        Type oldTypeData = typeRepo.findById(id)
                .orElseThrow();
        oldTypeData.setName(type.getName());
        return typeRepo.save(oldTypeData);
    }

    @Override
    public void deleteById(Long id) {
        typeRepo.deleteById(id);
    }

    @Override
    public Optional<Type> findByTypeName(String type) {
        return Optional.of(
                typeRepo.findByName(type)
                    .orElseThrow()
        );
    }

    @Override
    public Iterable<Type> findAll() {
        return typeRepo.findAll();
    }
}
