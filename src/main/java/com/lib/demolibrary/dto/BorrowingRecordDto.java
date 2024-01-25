package com.lib.demolibrary.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor
public class BorrowingRecordDto {
    private BooksDto booksDto;
    private PatronsDto patronsDto;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private boolean isBorrowed;
}
