package com.payu.web_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payu.web_service.dto.BookDto;
import com.payu.web_service.dto.BookRequest;
import com.payu.web_service.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${management.service.url}")
    private String managementServiceUrl;

    public PagedResponse getAllBooks(int page, int size) {
        String url = managementServiceUrl + "/all?pageNo=" + page + "&noOfItems=" + size;
        return restTemplate.getForObject(url, PagedResponse.class);
    }

    public Object searchBooks(String query, int page, int size) {
        String url = managementServiceUrl + "/search-book?q=" + query + "&page=" + page + "&size=" + size;
        return restTemplate.getForObject(url, Object.class);
    }

    public void createBook(BookRequest bookRequest) {
        log.info("book request {}",bookRequest);
        String url = managementServiceUrl;
        try {
        restTemplate.postForObject(url, bookRequest, Void.class);
        } catch (HttpStatusCodeException e) {
            String errorMessage = "An unexpected error occurred";
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST || e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                try {
                    Map<String, String> errorResponse = objectMapper.readValue(e.getResponseBodyAsString(), Map.class);
                    errorMessage = errorResponse.getOrDefault("message", errorMessage);
                } catch (Exception parseException) {
                 log.error("Error occurred {}",parseException.getMessage());
                }
            }
            throw new RuntimeException(errorMessage);
        }
    }

    public void updateBook(String bookId, BookRequest bookRequest) {
        String url = managementServiceUrl + "?bookId=" + bookId;
        restTemplate.put(url, bookRequest);
    }

    public void deleteBook(String bookId) {
        String url = managementServiceUrl + "/delete?bookId=" + bookId;
        restTemplate.delete(url);
    }

    public BookDto getBookById(String bookId) {
        String url = managementServiceUrl + "?bookId=" + bookId;
        return restTemplate.getForObject(url, BookDto.class);
    }
}
