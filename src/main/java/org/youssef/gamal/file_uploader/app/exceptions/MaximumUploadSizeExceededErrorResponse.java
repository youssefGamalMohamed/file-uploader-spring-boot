package org.youssef.gamal.file_uploader.app.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MaximumUploadSizeExceededErrorResponse extends ApiErrorResponse {

    private String fileMaximumAllowedSize;
    private String maximumAllowedRequestSize;
}
