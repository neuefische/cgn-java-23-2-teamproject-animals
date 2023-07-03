package de.neuefische.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping()
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @PostMapping()
    public Animal addAnimal(@RequestBody Animal animal) {
        return this.animalService.addAnimal(animal);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        animalService.delete(id);
    }


}
