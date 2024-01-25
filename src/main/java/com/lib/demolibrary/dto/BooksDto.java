package com.lib.demolibrary.dto;

import com.lib.demolibrary.dto.createDto.BooksCreateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Schema(
        name = "Extend Books",
        description = "Schema to hold Book information contains ID"
)
public class BooksDto extends BooksCreateDto {
    @Schema(
            description = "Book Database ID"
    )
    private Long id;

}
