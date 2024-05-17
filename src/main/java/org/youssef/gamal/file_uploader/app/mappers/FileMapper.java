package org.youssef.gamal.file_uploader.app.mappers;

import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;
import org.youssef.gamal.file_uploader.app.dto.FileDto;
import org.youssef.gamal.file_uploader.app.entities.File;
import org.youssef.gamal.file_uploader.app.entities.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileMapper {

    public static File toEntity(MultipartFile multipartFile) throws IOException {
       return File.builder()
               .name(multipartFile.getOriginalFilename())
               .type(
                       Type.builder()
                               .name(multipartFile.getContentType())
                               .build()
               )
               .data(multipartFile.getBytes())
               .build();

    }


    public static List<File> toEntities(MultipartFile[] multipartFiles) throws IOException {
        List<File> list = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            File entity = toEntity(multipartFile);
            list.add(entity);
        }
        return list;
    }

    public static FileDto toDto(File file) {
        return FileDto.builder()
                .id(file.getId())
                .name(file.getName())
                .type(file.getType())
                .data(file.getData())
                .build();
    }
    public static List<FileDto> toDtos(List<File> savedFiles) {
        return savedFiles
                .stream()
                .map(FileMapper::toDto)
                .toList();
    }
}
