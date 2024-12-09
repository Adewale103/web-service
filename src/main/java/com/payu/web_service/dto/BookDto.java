package com.payu.web_service.dto;

import com.payu.web_service.enums.BookType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String id;
    private String name;
    private LocalDate publishDate;
    private LocalDate publishedDate;
    private int isbn;
    private BigDecimal price;
    private BookType bookType;

}
