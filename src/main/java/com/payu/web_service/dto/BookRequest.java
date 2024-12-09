package com.payu.web_service.dto;

import com.payu.web_service.enums.BookType;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @NotBlank
    private int isbn;
    @NotBlank
    @Min(value = 1, message = "Price can not be less than 1")
    private BigDecimal price;
    @Pattern(regexp = "HARDCOVER|SOFTCOVER|EBOOK", message = "Book type must be either 'HARDCOVER', 'EBOOK' or 'SOFTCOVER'")
    private BookType bookType;

}