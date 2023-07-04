package de.neuefische.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;


public class AnimalServiceTest {

    @Test
    void testAddAnimal() {
        AnimalRepository animalRepository = spy(AnimalRepository.class);
        Animal expected = new Animal("1", "cat");
        Animal actual = animalRepository.addAnimal(expected);
        Assertions.assertEquals(expected, actual);
        verify(animalRepository).addAnimal(expected);
    }

    @Test
    void testGetAllAnimals() {
        AnimalRepository animalRepository = spy(AnimalRepository.class);
        animalRepository.addAnimal(new Animal("1", "cat"));
        List<Animal> actual = animalRepository.getAllAnimals();
        List<Animal> expected = Arrays.asList(new Animal("1", "cat"));
        Assertions.assertEquals(expected, actual);
        Assertions.assertTrue(animalRepository.getAllAnimals().size() == 1);
    }

    @Test
    void testDeleteAnimal() {
        AnimalRepository animalRepository = spy(AnimalRepository.class);
        animalRepository.addAnimal(new Animal("1", "cat"));
        List<Animal> actual = animalRepository.getAllAnimals();
        animalRepository.deleteAnimal("1");
        Assertions.assertFalse(animalRepository.getAllAnimals().contains(new Animal("1", "cat")));
    }
}
