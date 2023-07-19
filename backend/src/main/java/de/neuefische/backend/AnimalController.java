package de.neuefische.backend;

import de.neuefische.backend.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;


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

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleException(NoSuchElementException exception) {
        return new ErrorMessage(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleRuntimeExceptions(Exception exception) {
        return new ErrorMessage(exception.getMessage());
    }
}
