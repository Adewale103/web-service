package com.payu.web_service.dto;

import com.payu.web_service.enums.BookType;
import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String publishedDate;
    @Positive
    private int isbn;
    @NotNull
    private BigDecimal price;
    @NotNull(message = "Book type must not be null")
    private BookType bookType;

}