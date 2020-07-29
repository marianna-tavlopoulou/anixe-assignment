package com.anixe.assignment.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
public class RestLogFilter extends OncePerRequestFilter {

    private final boolean debug;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        if (!debug || isAsyncDispatch(request)) {
            filterChain.doFilter(request, response);
        } else {
            ContentCachingRequestWrapper wrappedRequest = wrapRequest(request);
            ContentCachingResponseWrapper wrappedResponse = wrapResponse(response);
            try {
                filterChain.doFilter(wrappedRequest, wrappedResponse);
            } finally {
                logRequest(wrappedRequest);
                wrappedResponse.copyBodyToResponse();
            }
        }
    }

    private void logRequest(ContentCachingRequestWrapper request) {
        String uri = request.getRequestURI();

        if (StringUtils.isNotBlank(request.getQueryString())) {
            uri = uri.concat("?").concat(request.getQueryString());
        }

        RestLogEntry logEntry = RestLogEntry.builder()
            .type(RestLogEntryType.REQUEST)
            .uri(uri)
            .content(getContentAsString(request.getContentAsByteArray(), request.getCharacterEncoding()))
            .build();

        Collections.list(request.getHeaderNames())
            .stream()
            .filter(header -> !"authorization".equals(header))
            .forEach(header -> Collections.list(request.getHeaders(header))
                .forEach(value -> logEntry.getHeaders().put(header, value)));

        log.info(logEntry.toString());
    }

    private String getContentAsString(byte[] content, String encoding) {
        if (content != null && content.length > 0) {
            try {
                return new String(content, encoding);
            } catch (UnsupportedEncodingException e) {
                return "Content length: ".concat(String.valueOf(content.length));
            }
        } else {
            return StringUtils.EMPTY;
        }
    }

    private ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper) {
            return (ContentCachingRequestWrapper) request;
        } else {
            return new ContentCachingRequestWrapper(request);
        }
    }

    private ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        } else {
            return new ContentCachingResponseWrapper(response);
        }
    }
}