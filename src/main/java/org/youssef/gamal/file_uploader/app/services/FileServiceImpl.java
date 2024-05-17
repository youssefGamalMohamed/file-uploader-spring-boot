package org.youssef.gamal.file_uploader.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youssef.gamal.file_uploader.app.entities.File;
import org.youssef.gamal.file_uploader.app.entities.Type;
import org.youssef.gamal.file_uploader.app.repos.FileRepo;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileServiceInterface {

    private final FileRepo fileRepo;
    private final TypeServiceInterface typeService;

    @Override
    public File save(File file) {
        return fileRepo.save(file);
    }

    @Override
    public Optional<File> findById(Long id) {
        return Optional.of(
                fileRepo.findById(id)
                        .orElseThrow()
        );
    }

    @Override
    public File updateById(Long id, File file) {
        File fileFromDb = fileRepo.findById(id)
                .orElseThrow();
        fileFromDb.setName(file.getName());
        fileFromDb.setData(file.getData());
        fileFromDb.setType(file.getType());

        return fileRepo.save(fileFromDb);
    }

    @Override
    public void deleteById(Long id) {
        fileRepo.deleteById(id);
    }

    @Override
    public Iterable<File> findAll() {
        return fileRepo.findAll();
    }

    @Override
    public List<File> saveAll(List<File> files) {
        for (File file : files) {
            Optional<Type> type = Optional.of(
                    typeService.findByTypeName(file.getType().getName())
                    .orElseThrow()
            );
            file.setType(type.get());
        }
        return fileRepo.saveAll(files);
    }
}
