package com.lib.demolibrary.dto.createDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Books",
        description = "Schema to hold Borrowing record"
)
public class BorrowingRecordCreateDto {

    @NotEmpty(message = "book id should not be null or empty")
    @Schema(
            description = "reference of the book"
    )
    private Long bookId;
    @NotEmpty(message = "Patron id should not be null or empty")
    @Schema(
            description = "reference of the Patron"
    )
    private Long patronId;

}
