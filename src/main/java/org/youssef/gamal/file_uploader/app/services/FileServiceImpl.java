package org.youssef.gamal.file_uploader.app.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;
import org.youssef.gamal.file_uploader.app.entities.File;
import org.youssef.gamal.file_uploader.app.entities.Type;
import org.youssef.gamal.file_uploader.app.repos.FileRepo;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
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
        List<Type> supportedTypes = (List<Type>) typeService.findAll();
        for (File file : files) {
            Type type = supportedTypes.stream()
                    .filter(t -> t.getName().equals(file.getType().getName()))
                    .findFirst()
                    .orElseThrow(() -> new UnsupportedMediaTypeStatusException(
                            MediaType.parseMediaType(file.getType().getName()),
                            supportedTypes.stream()
                                    .map(t -> MediaType.parseMediaType(t.getName()))
                                    .toList()
                    ));

            file.setType(type);
        }
        return fileRepo.saveAll(files);
    }
}
