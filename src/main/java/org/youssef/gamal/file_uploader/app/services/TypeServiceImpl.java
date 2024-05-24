package org.youssef.gamal.file_uploader.app.services;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;
import org.youssef.gamal.file_uploader.app.entities.Type;
import org.youssef.gamal.file_uploader.app.mappers.TypeMapper;
import org.youssef.gamal.file_uploader.app.repos.TypeRepo;

import java.util.List;
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
        List<Type> supportedTypes = (List<Type>) this.findAll();
        return Optional.of(
                typeRepo.findByName(type)
                    .orElseThrow(
                            () -> new UnsupportedMediaTypeStatusException(
                                    TypeMapper.toMediaType(type),
                                    TypeMapper.toMediaTypeList(supportedTypes)
                            )
                    )
        );
    }

    @Override
    public Iterable<Type> findAll() {
        return typeRepo.findAll();
    }
}
