package org.youssef.gamal.file_uploader.app.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorResponse extends ApiErrorResponse {

    private Map<String,String> validationErrors;

}
