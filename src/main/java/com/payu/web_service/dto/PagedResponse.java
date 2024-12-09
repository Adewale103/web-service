package com.payu.web_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagedResponse {
    private List<BookDto> books;
    private int pageNumber;
    private int totalPages;
    private long totalElements;
    private int size;
    private int numberOfElementsInPage;
}
