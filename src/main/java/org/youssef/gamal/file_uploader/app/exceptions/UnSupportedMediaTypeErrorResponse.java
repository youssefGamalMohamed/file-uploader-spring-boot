package org.youssef.gamal.file_uploader.app.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnSupportedMediaTypeErrorResponse extends ApiErrorResponse {

    private String rejectedContentType;
    private List<String> supportedTypes;

}
