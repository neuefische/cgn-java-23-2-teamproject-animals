package de.neuefische.backend;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimalRepository {

    private List<Animal> animals;

    public AnimalRepository() {
        this.animals = new ArrayList<>();
    }

    public Animal addAnimal(Animal animal) {
        this.animals.add(animal);
        return animal;
    }

    public void delete(String id) {
        animals.remove(id);
    }


    public List<Animal> getAllAnimals() {

        return animals;
    }


}
