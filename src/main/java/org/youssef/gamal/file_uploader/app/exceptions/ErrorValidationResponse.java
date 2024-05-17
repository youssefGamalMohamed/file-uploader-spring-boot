package org.youssef.gamal.file_uploader.app.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorValidationResponse {

    private Map<String,String> validationErrors;

}
