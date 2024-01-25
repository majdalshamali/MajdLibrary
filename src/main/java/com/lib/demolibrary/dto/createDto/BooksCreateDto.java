package com.lib.demolibrary.dto.createDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Books",
        description = "Schema to hold Book information"
)
public class BooksCreateDto {
    @NotEmpty(message = "Book title can not be a null or empty")
    @Schema(
            description = "Book title"
    )
    private String title;

    @NotEmpty(message = "Book author can not be a null or empty")
    @Schema(
            description = "Book Author", example = "Majd Alshamali"
    )
    private String author;

    @NotNull(message = "Book Year can not be a null or empty")
    @Max(value = 2024, message = "Year must be less than or equal to 2024")
    @Schema(
            description = "year of the book, should be less than 2024", example = "1990"
    )
    private int year;
    @NotEmpty(message = "Book ISBN can not be a null or empty")
    @Schema(
            description = "ISBN of the book should be not null and unique ", example = "ISBN001"
    )
    private String isbn;
}
