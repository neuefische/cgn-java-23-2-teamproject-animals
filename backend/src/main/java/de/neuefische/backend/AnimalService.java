package de.neuefische.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {


    private final AnimalRepository animalRepository;

    public Animal addAnimal(Animal animal) {
        return animalRepository.addAnimal(animal);
    }

    public void deleteAnimal(String id) {
        animalRepository.deleteAnimal(id);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.getAllAnimals();
    }

    public Animal editAnimal(Animal animal, String id) {
        return animalRepository.editAnimal(animal, id);
    }

}
