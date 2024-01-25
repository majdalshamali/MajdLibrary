package com.lib.demolibrary.controller;


import com.lib.demolibrary.constant.LibConstants;
import com.lib.demolibrary.dto.ErrorResponseDto;
import com.lib.demolibrary.dto.PatronsDto;
import com.lib.demolibrary.dto.ResponseDto;
import com.lib.demolibrary.dto.createDto.PatronsCreateDto;
import com.lib.demolibrary.service.IPatronService;
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

import static com.lib.demolibrary.mapper.PatronsMapper.mapFromPatronCreateDto;

@Tag(
        name = "CRUD REST APIs for Patron in Majd Library",
        description = "CRUD REST APIs in Majd Library to CREATE, UPDATE, FETCH AND DELETE Patron details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class PatronController {

    private final IPatronService iPatronService;

    public PatronController(IPatronService iPatronService) {
        this.iPatronService = iPatronService;
    }

    @Operation(
            summary = "Create Patron REST API",
            description = "REST API to create new Patron inside Majd Library"
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
    @PostMapping("/patrons")
    public ResponseEntity<ResponseDto> createPatron(@Valid @RequestBody PatronsCreateDto patronsCreateDto) {
        iPatronService.createPatron(mapFromPatronCreateDto(patronsCreateDto,new PatronsDto()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(LibConstants.STATUS_201, LibConstants.MESSAGE_201));
    }

    @Operation(
            summary = "List patrons in Majd Library REST API",
            description = "REST API to List all patrons in  Majd Library"
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

    @GetMapping("/patrons")
    public ResponseEntity<List<PatronsDto>> listOfPatron() {
        List<PatronsDto> patronsDtos = iPatronService.listOfPatron();
        return ResponseEntity.status(HttpStatus.OK)
                .body(patronsDtos);
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
    @GetMapping("/patrons/{id}")
    public ResponseEntity<PatronsDto> fetchPatron(@PathVariable Long id) {
        PatronsDto patronsDto = iPatronService.fetchPatron(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(patronsDto);
    }


    @Operation(
            summary = "Update Patron Details REST API",
            description = "REST API to update patron details based on a Database ID"
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
    @PutMapping("/patrons/{id}")
    public ResponseEntity<ResponseDto> updatePatron(@PathVariable Long id,@Valid @RequestBody PatronsCreateDto patronsCreateDto) {
        boolean isUpdated = iPatronService.updatePatron(id, mapFromPatronCreateDto(patronsCreateDto,new PatronsDto()));
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(LibConstants.STATUS_200, LibConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LibConstants.STATUS_417, LibConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete patron REST API",
            description = "REST API to delete patron based on a database ID"
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

    @DeleteMapping("/patrons/{id}")
    public ResponseEntity<ResponseDto> deletePatron(@PathVariable Long id) {
        boolean isDeleted = iPatronService.deletePatron(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(LibConstants.STATUS_200, LibConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LibConstants.STATUS_417, LibConstants.MESSAGE_417_DELETE));
        }
    }

}
