package com.lib.demolibrary.dto.createDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Patrons",
        description = "Schema to hold Patron information"
)
public class PatronsCreateDto {
    @NotEmpty(message = "Patron ID can not be a null or empty")
    @Schema(
            description = "Patron ID, like national number", example = "0132323443"
    )
    private String patronID;
    @NotEmpty(message = "First Name can not be a null or empty")
    @Schema(
            description = "First Name"
    )
    private String firstName;
    @NotEmpty(message = "Last Name can not be a null or empty")
    @Schema(
            description = "Last Name"
    )
    private String lastName;
    @Schema(
            description = "Email address of the patron", example = "dev@library.com"
    )
    @Email(message = "Email address should be a valid value")
    private String email;
    @NotEmpty(message = "mobile can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "mobile Number of the patron", example = "93434344453"
    )
    private String mobile;
    @Schema(
            description = "address of the patron"
    )
    private String address;
}
