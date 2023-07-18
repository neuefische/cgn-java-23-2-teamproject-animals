package de.neuefische.backend;

import de.neuefische.backend.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @PostMapping
    public Animal addAnimal(@RequestBody DtoAnimal dtoAnimal) {

        return animalService.addAnimal(dtoAnimal);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable String id) {
        animalService.deleteAnimal(id);
    }

    @PutMapping("{id}")
    public Animal editAnimal(@RequestBody DtoAnimal dtoAnimal, @PathVariable String id) {
        return animalService.editAnimal(dtoAnimal, id);
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable String id) {
        return animalService.getAnimalById(id);
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
