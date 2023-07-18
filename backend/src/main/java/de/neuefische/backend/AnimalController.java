package de.neuefische.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        return ResponseEntity.ok(animalService.getAllAnimals());
    }

    @PostMapping
    public ResponseEntity<Animal> addAnimal(@RequestParam("file") MultipartFile file, DtoAnimal dtoAnimal) {
        try {
            Animal animal = animalService.addAnimal(file, dtoAnimal);
            return ResponseEntity.ok(animal);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable String id) {
        animalService.deleteAnimal(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Animal> editAnimal(@RequestBody DtoAnimal dtoAnimal, @PathVariable String id) {
        try {
            Animal editedAnimal = animalService.editAnimal(dtoAnimal, id);
            return ResponseEntity.ok(editedAnimal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
