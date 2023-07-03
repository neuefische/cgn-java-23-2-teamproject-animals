package de.neuefische.backend;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService() {
        this.animalRepository = new AnimalRepository();
    }

    public Animal addAnimal(Animal animal) {
        return animalRepository.addAnimal(animal);
    }

    public void delete(String id) {
        animalRepository.delete(id);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.getAllAnimals();
    }

}
