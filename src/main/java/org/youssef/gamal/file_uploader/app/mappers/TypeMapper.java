package org.youssef.gamal.file_uploader.app.mappers;

import org.springframework.http.MediaType;
import org.youssef.gamal.file_uploader.app.entities.Type;

import java.util.List;

public class TypeMapper {

    public static MediaType toMediaType(Type type) {
        return MediaType.parseMediaType(type.getName());
    }
    public static MediaType toMediaType(String type) {
        return MediaType.parseMediaType(type);
    }

    public static List<MediaType> toMediaTypeList(List<Type> types) {
        return types.stream()
                .map(TypeMapper::toMediaType)
                .toList();
    }
}
