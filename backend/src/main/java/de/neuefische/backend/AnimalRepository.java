package de.neuefische.backend;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Repository
public class AnimalRepository {

    private List<Animal> animals;

    private Animal result;


    public AnimalRepository() {
        this.animals = new ArrayList<>();
    }

    public Animal addAnimal(Animal animal) {
        this.animals.add(animal);
        return animal;
    }

    Function<String, Animal> getAnimalById = (id) -> {
        animals.forEach(animal -> {
            if (animal.getId().equals(id)) result = animal;
        });
        return result;
    };

    public void deleteAnimal(String id) {
        Animal animal = getAnimalById.apply(id);
        animals.remove(animal);
    }


    public List<Animal> getAllAnimals() {
        return animals;
    }


}
