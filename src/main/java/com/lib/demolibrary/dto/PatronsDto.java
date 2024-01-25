package com.lib.demolibrary.dto;


import com.lib.demolibrary.dto.createDto.PatronsCreateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Schema(
        name = "Extend Patron",
        description = "Schema to hold Patron information contains ID"
)
public class PatronsDto extends PatronsCreateDto {

    @Schema(
            description = "Patron database ID"
    )
    private Long id;

}
