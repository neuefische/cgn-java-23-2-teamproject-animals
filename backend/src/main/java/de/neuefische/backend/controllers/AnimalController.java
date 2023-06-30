package de.neuefische.backend.controllers;

import de.neuefische.backend.models.Animal;
import de.neuefische.backend.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/animals")
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @PostMapping("/animal")
    public List<Animal> addAnimal(@RequestBody Animal animal) {
        return this.animalService.addAnimal(animal);
    }


}
