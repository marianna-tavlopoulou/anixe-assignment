package com.anixe.assignment.logging;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Builder
public class RestLogEntry {

    private RestLogEntryType type;

    @Builder.Default
    private final Map<String, String> headers = new ConcurrentHashMap<>();

    private String content;

    private String uri;

    private HttpStatus httpStatus;
}
