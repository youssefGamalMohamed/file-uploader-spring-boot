package org.youssef.gamal.file_uploader.app.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.youssef.gamal.file_uploader.app.dto.FileDto;
import org.youssef.gamal.file_uploader.app.entities.File;
import org.youssef.gamal.file_uploader.app.mappers.FileMapper;
import org.youssef.gamal.file_uploader.app.services.FileServiceInterface;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
@AllArgsConstructor
public class FileController {

    private final FileServiceInterface fileService;

    @PostMapping(value = "/files")
    public ResponseEntity<List<FileDto>> upload(
            @RequestParam("files") List<MultipartFile> files,
            HttpServletRequest httpRequest
    ) throws IOException {

        List<File> filesList = FileMapper.toEntities(files);
        List<File> savedFiles = fileService.saveAll(filesList);
        List<FileDto> fileDtos = FileMapper.toDtos(savedFiles);
        fileDtos = fileDtos.stream()
                    .map(fileDto ->  {
                        fileDto.setUrl(httpRequest.getRequestURL() + "/" + fileDto.getId());
                        fileDto.setType(null);
                        fileDto.setId(null);
                        fileDto.setData(null);
                        return fileDto;
                    })
                    .collect(Collectors.toList());

        return ResponseEntity.ok(fileDtos);
    }


    @GetMapping("/files/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<File> file = fileService.findById(id);
        FileDto fileDto = FileMapper.toDto(file.get());

        String contentType = fileDto.getType().getName();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));
        headers.setContentDispositionFormData("file", fileDto.getName());

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileDto.getData());

    }

}
