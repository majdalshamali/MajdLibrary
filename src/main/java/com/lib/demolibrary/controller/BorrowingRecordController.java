package com.lib.demolibrary.controller;

import com.lib.demolibrary.constant.LibConstants;
import com.lib.demolibrary.dto.ErrorResponseDto;
import com.lib.demolibrary.dto.ResponseDto;
import com.lib.demolibrary.service.IBorrowRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Borrow and Return Book RESET API",
        description = "REST API to borrow and return book"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class BorrowingRecordController {

    private final IBorrowRecordService iBorrowRecordService;

    public BorrowingRecordController(IBorrowRecordService iBorrowRecordService) {
        this.iBorrowRecordService = iBorrowRecordService;
    }

    @Operation(
            summary = "Create Borrowing record REST API",
            description = "REST API to borrow Book inside Majd Library"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<ResponseDto> createBorrowed(@PathVariable Long bookId,@PathVariable Long patronId)
    {
        iBorrowRecordService.createBorrowRecord(bookId,patronId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(LibConstants.STATUS_201,LibConstants.MESSAGE_201));
    }

    @Operation(
            summary = "return Book  REST API",
            description = "REST API to return Book inside Majd Library"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<ResponseDto> createReturn(@PathVariable Long bookId,@PathVariable Long patronId)
    {
        iBorrowRecordService.createReturnRecord(bookId,patronId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(LibConstants.STATUS_200,LibConstants.MESSAGE_200));
    }


}
