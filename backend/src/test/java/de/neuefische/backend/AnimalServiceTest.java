package de.neuefische.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


 class AnimalServiceTest {
     AnimalRepository animalRepository = spy(AnimalRepository.class);
     UuidService uuidService = spy(UuidService.class);


     @Test
     void testAddAnimal() {
         DtoAnimal dtoAnimal = new DtoAnimal("cat");
         Animal expected = new Animal(uuidService.generateUUID(), dtoAnimal.getName());
         when(animalRepository.save(expected)).thenReturn(expected);
         Animal actual = new Animal(expected.getId(), "cat");
         Assertions.assertEquals(expected, actual);
     }

     @Test
    void testGetAllAnimals() {
        when(animalRepository.findAll()).thenReturn(Arrays.asList(
                new Animal("1", "cat"),
                new Animal("2", "dog")
        ));
        List<Animal> actual = animalRepository.findAll();
        List<Animal> expected = Arrays.asList(
                new Animal("1", "cat"),
                new Animal("2", "dog")
        );
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testDeleteAnimal() {
        Animal animal = new Animal("2", "dog");
        animalRepository.save(animal);
        Assertions.assertTrue(animalRepository.findAll().isEmpty());
        verify(animalRepository).deleteById("2");
    }
}
