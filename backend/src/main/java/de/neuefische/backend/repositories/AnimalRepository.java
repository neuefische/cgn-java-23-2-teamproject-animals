package de.neuefische.backend.repositories;

import de.neuefische.backend.models.Animal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimalRepository {

    private List<Animal> animals;

    public AnimalRepository() {
        this.animals = new ArrayList<>();
    }

    public List<Animal> getAllAnimals() {
        return animals;
    }


}
