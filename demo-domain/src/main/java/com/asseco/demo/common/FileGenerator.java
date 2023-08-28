package com.asseco.demo.common;

import com.asseco.demo.exception.MyException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.asseco.demo.exception.ErrorCode.DEMO_5;

@Component
@RequiredArgsConstructor
public class FileGenerator {

    private final ObjectMapper objectMapper;

    public <T> ResponseEntity<ByteArrayResource> generateFile(List<T> objects) {
        var fileContent = generateFileContent(objects);
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentDispositionFormData("attachment", "users_file.json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileContent);
    }

    private <T> ByteArrayResource generateFileContent(List<T> objects) {
        ByteArrayResource resource;
        try {
            resource = new ByteArrayResource(objectMapper.writeValueAsBytes(objects));
        } catch (JsonProcessingException e) {
            throw new MyException(DEMO_5.name(), "Error while generating file with objects", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resource;
    }
}
