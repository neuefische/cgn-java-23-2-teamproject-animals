package de.neuefische.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


 class AnimalServiceTest {
     AnimalRepository animalRepository = spy(AnimalRepository.class);

     AnimalService animalService = new AnimalService(animalRepository);


     @Test
     void testAddAnimal() {
         Animal expected = new Animal("1", "cat");
         when(animalRepository.save(expected)).thenReturn(expected);
         Animal actual = animalService.addAnimal(expected);
        Assertions.assertEquals(expected, actual);
        verify(animalRepository).save(expected);
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
        List<Animal> animals = Arrays.asList(
                new Animal("1", "cat"),
                new Animal("2", "dog")
        );
        when(animalRepository.saveAll(animals)).thenReturn(animals);
        animalService.deleteAnimal("2");
        Assertions.assertTrue(animals.contains(new Animal("1", "cat")));
    }
}
