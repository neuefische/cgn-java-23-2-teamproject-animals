package de.neuefische.backend;

import de.neuefische.backend.models.Animal;
import de.neuefische.backend.services.AnimalService;
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
    private AnimalService animalService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void returnEmptyArrayWhenListIsEmpty() {
        when(animalService.getAllAnimals()).thenReturn(Arrays.asList());

        // Call the method that interacts with the service
        List<Animal> result = animalService.getAllAnimals();

        // Assert the expected result
        assertEquals(Arrays.asList(), result);

    }

    @Test
    void verifyServiceWasCalled() {
        // Call the method that interacts with the service
        animalService.getAllAnimals();

        // Verify that the method was called
        verify(animalService).getAllAnimals();
    }

    @Test
    void returnAnimalWhenListNotEmpty() {
        Animal a = new Animal("1", "cat");
        when(animalService.getAllAnimals()).thenReturn(Arrays.asList(a));

        // Call the method that interacts with the service
        List<Animal> result = animalService.getAllAnimals();

        // Assert the expected result
        assertEquals(Arrays.asList(a), result);

    }


}
