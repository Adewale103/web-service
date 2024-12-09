package com.payu.web_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookType {
    HARDCOVER("HARDCOVER"), SOFTCOVER("SOFTCOVER"), EBOOK("EBOOK");

    private final String bookType;
}
