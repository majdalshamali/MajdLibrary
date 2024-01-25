package com.lib.demolibrary.controller;

import com.lib.demolibrary.constant.LibConstants;
import com.lib.demolibrary.dto.BooksDto;
import com.lib.demolibrary.dto.ErrorResponseDto;
import com.lib.demolibrary.dto.ResponseDto;
import com.lib.demolibrary.dto.createDto.BooksCreateDto;
import com.lib.demolibrary.service.IBooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lib.demolibrary.mapper.BooksMapper.mapFromBookCreateDto;

@Tag(
        name = "CRUD REST APIs for Books in Majd Library",
        description = "CRUD REST APIs in Majd Library to CREATE, UPDATE, FETCH AND DELETE Books details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class BooksController {

    private final IBooksService iBooksService;

    public BooksController(IBooksService iBooksService) {
        this.iBooksService = iBooksService;
    }

    @Operation(
            summary = "Create Book REST API",
            description = "REST API to create new Book inside Majd Library"
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
    @PostMapping("/books")
    public ResponseEntity<ResponseDto> createBook(@Valid @RequestBody BooksCreateDto booksCreateDto) {
        iBooksService.createBook(mapFromBookCreateDto(booksCreateDto, new BooksDto()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(LibConstants.STATUS_201, LibConstants.MESSAGE_201));
    }
    @Operation(
            summary = "List Books in Majd Library REST API",
            description = "REST API to List all books in  Majd Library"
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
    @GetMapping("/books")
    public ResponseEntity<List<BooksDto>> listOfBooks() {
        List<BooksDto> booksDtos = iBooksService.listofBooks();
        return ResponseEntity.status(HttpStatus.OK)
                .body(booksDtos);
    }

    @Operation(
            summary = "Fetch Book Details REST API",
            description = "REST API to fetch Book details based on a ID of the book"
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
    @GetMapping("/books/{id}")
    public ResponseEntity<BooksDto> fetchBook(@PathVariable Long id) {
        BooksDto booksDto = iBooksService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(booksDto);
    }

    @Operation(
            summary = "Update Book Details REST API",
            description = "REST API to update Book details based on a Database ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
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
    @PutMapping("/books/{id}")
    public ResponseEntity<ResponseDto> updateBook(@PathVariable Long id, @Valid @RequestBody BooksCreateDto booksCreateDto) {
        boolean isUpdate = iBooksService.updateBook(id, mapFromBookCreateDto(booksCreateDto, new BooksDto()));
        if (isUpdate) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(LibConstants.STATUS_200, LibConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LibConstants.STATUS_417, LibConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Book REST API",
            description = "REST API to delete Book based on a database ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
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
    @DeleteMapping("/books/{id}")
    public ResponseEntity<ResponseDto> deleteBook(@PathVariable Long id) {
        boolean isDeleted = iBooksService.deleteBook(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(LibConstants.STATUS_200, LibConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LibConstants.STATUS_417, LibConstants.MESSAGE_417_DELETE));
        }
    }


}
