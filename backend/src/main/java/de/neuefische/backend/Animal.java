package de.neuefische.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    private String id = UUID.randomUUID().toString();
    private String name;
}
