package de.neuefische.backend;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoAnimal {

    @NotBlank
    @Size(min = 3, max = 5, message = "Der Name muss zwischen 3 und 5 Zeichen lang sein")
    private String name;
}
