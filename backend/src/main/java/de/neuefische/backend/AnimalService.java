package de.neuefische.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {


    private final UuidService uuidService;

    private final AnimalRepository animalRepository;

    public Animal addAnimal(DtoAnimal dtoAnimal) {
        String id = uuidService.generateUUID();
        Animal animal = new Animal(id, dtoAnimal.getName());
        return animalRepository.save(animal);
    }

    public void deleteAnimal(String id) {
        animalRepository.deleteById(id);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Animal editAnimal(DtoAnimal dtoAnimal, String id) {
        Animal isEditAnimal = animalRepository.findById(id).orElseThrow(() -> new RuntimeException("could not find animal"));
        isEditAnimal.setName(dtoAnimal.getName());
        return animalRepository.save(isEditAnimal);

    }
}
