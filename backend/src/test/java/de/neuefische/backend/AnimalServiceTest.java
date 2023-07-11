package de.neuefische.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


 class AnimalServiceTest {
     AnimalRepository animalRepository = spy(AnimalRepository.class);
     UuidService uuidService = spy(UuidService.class);
     AnimalService animalService = new AnimalService(uuidService, animalRepository);

     @Test
     void test_postAnimal() {
         DtoAnimal dtoAnimal = new DtoAnimal("cat");
         Animal expected = new Animal("123", dtoAnimal.getName());
         when(uuidService.generateUUID()).thenReturn("123");
         when(animalRepository.save(expected)).thenReturn(expected);
         Animal actual = animalService.addAnimal(dtoAnimal);
         Assertions.assertEquals(expected, actual);
     }

     @Test
     void test_getAllAnimals() {
         when(animalRepository.findAll()).thenReturn(Arrays.asList(
                 new Animal("1", "cat"),
                 new Animal("2", "dog")
         ));
         List<Animal> actual = animalService.getAllAnimals();
         List<Animal> expected = Arrays.asList(
                 new Animal("1", "cat"),
                 new Animal("2", "dog")
         );
         Assertions.assertEquals(expected, actual);
     }

     @Test
     void test_deleteAnimal() {
         animalService.deleteAnimal("2"); //return: void -> darum kein when benötigt.
         verify(animalRepository).deleteById("2");
     }

     @Test
     void test_updateAnimal() {
         when(animalRepository.findById("123")).thenReturn(Optional.of(new Animal("123", "Dog")));
         DtoAnimal dtoAnimal = new DtoAnimal("Cat");
         Animal actual = animalService.editAnimal(dtoAnimal, "123");
         Animal expected = new Animal("123", "Dog");
         Assertions.assertNotEquals(expected, actual);
     }
 }
