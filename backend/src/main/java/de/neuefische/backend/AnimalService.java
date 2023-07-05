package de.neuefische.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimalService {


    private final AnimalRepository animalRepository;

    public Animal addAnimal(Animal animal) {
        animal.setId(UUID.randomUUID().toString());
        return animalRepository.save(animal);
    }

    public void deleteAnimal(String id) {
        animalRepository.deleteById(id);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }


}
