package de.neuefische.backend.services;

import de.neuefische.backend.models.Animal;
import de.neuefische.backend.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService() {
        this.animalRepository = new AnimalRepository();
    }

    public List<Animal> addAnimal(Animal animal) {
        return animalRepository.addAnimal(animal);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.getAllAnimals();
    }

}
