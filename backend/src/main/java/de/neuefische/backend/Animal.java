package de.neuefische.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("animals")
public class Animal {
    @Id
    private String id;
    private String name;
    private String favoriteFood;
    private Type type;
    private String dateOfBirth;
    private String imageUrl;
    private String AnimalUserId;
}
