package de.neuefische.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoAnimal {
    private String name;
    private String favoriteFood;
    private String dateOfBirth;
    private Type type;
}
