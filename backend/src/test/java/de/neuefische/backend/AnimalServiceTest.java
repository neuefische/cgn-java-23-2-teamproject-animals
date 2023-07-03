package de.neuefische.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AnimalServiceTest {
    @Mock
    private AnimalRepository animalRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void returnEmptyArrayWhenListIsEmpty() {
        when(animalRepository.getAllAnimals()).thenReturn(Arrays.asList());
        List<Animal> result = animalRepository.getAllAnimals();
        assertEquals(Arrays.asList(), result);
        verify(animalRepository).getAllAnimals();

    }
    @Test
    void returnAnimalWhenListNotEmpty() {
        Animal a = new Animal("1", "cat");
        when(animalRepository.getAllAnimals()).thenReturn(Arrays.asList(a));
        List<Animal> result = animalRepository.getAllAnimals();
        assertEquals(Arrays.asList(a), result);

    }
    @Test
    void returnAListofAnimalsWithOneAnimal() {
        Animal a = new Animal("2", "cat");
        when(animalRepository.addAnimal(a)).thenReturn(a);


        Animal result = animalRepository.addAnimal(a);


        assertEquals(a, result);
        verify(animalRepository).addAnimal(a);

    }

}
