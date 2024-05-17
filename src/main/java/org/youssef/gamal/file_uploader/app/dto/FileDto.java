package org.youssef.gamal.file_uploader.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.youssef.gamal.file_uploader.app.entities.Type;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDto {

    private Long id;

    private String name;

    private Type type;

    private byte[] data;

    private String url;
}
