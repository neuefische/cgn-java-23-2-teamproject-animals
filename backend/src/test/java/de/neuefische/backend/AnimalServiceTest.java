package de.neuefische.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


class AnimalServiceTest {
    AnimalRepository animalRepository = spy(AnimalRepository.class);
    UuidService uuidService = spy(UuidService.class);
    FileUploadService fileUploadService = mock(FileUploadService.class);
    AnimalService animalService = new AnimalService(uuidService, fileUploadService, animalRepository);


    @Test
    void testFileUpload() throws Exception {
        MultipartFile mockFile = new MockMultipartFile(
                "file",
                "image.jpg",
                "text/plain",
                "File content".getBytes()
        );
        String imageUrl = "https://res.cloudinary.com/duexg0xg3/image/upload/v1689596766/images/qrtyzbgcdihn2fb39x16.jpg";
        fileUploadService.getImageURL(mockFile);
        verify(fileUploadService).getImageURL(mockFile);
    }

    @Test
    void test_postAnimal() throws Exception {
        MultipartFile mockFile = new MockMultipartFile(
                "file",
                "image.jpg",
                "text/plain",
                "File content".getBytes()
        );
        String imageUrl = "https://res.cloudinary.com/duexg0xg3/image/upload/v1689596766/images/qrtyzbgcdihn2fb39x16.jpg";
        DtoAnimal dtoAnimal = new DtoAnimal("cat", "fish", "14/02/2002", Type.CAT);
        Animal expected = new Animal(
                "1", dtoAnimal.getName(),
                dtoAnimal.getFavoriteFood(),
                dtoAnimal.getType(),
                dtoAnimal.getDateOfBirth(),
                imageUrl

        );
        when(uuidService.generateUUID()).thenReturn("1");
        when(animalRepository.save(expected)).thenReturn(expected);
        when(fileUploadService.getImageURL(mockFile)).thenReturn(imageUrl);
        Animal actual = animalService.addAnimal(mockFile, dtoAnimal);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void test_getAllAnimals() {
        when(animalRepository.findAll()).thenReturn(Arrays.asList(
                new Animal("1", "cat", "fish", Type.CAT, "14/02/2002", "imageUrl"),
                new Animal("2", "dog", "fish", Type.DOG, "14/02/2004", "imageUrl2")
        ));
         List<Animal> actual = animalService.getAllAnimals();
         List<Animal> expected = Arrays.asList(
                 new Animal("1", "cat", "fish", Type.CAT, "14/02/2002", "imageUrl"),
                 new Animal("2", "dog", "fish", Type.DOG, "14/02/2004", "imageUrl2")
         );
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test_deleteAnimal() {
        animalService.deleteAnimal("1");
        verify(animalRepository).deleteById("1");
    }


    @Test
    void test_updateAnimal() throws Exception {
        when(animalRepository.findById("1")).thenReturn(Optional.of(new Animal("1", "cat", "fish", Type.CAT, "14/02/2002", "imageUrl")));
        DtoAnimal dtoAnimal = new DtoAnimal("cat", "fish", "14/02/2002", Type.CAT);
        Animal actual = animalService.editAnimal(dtoAnimal, "1");
        Animal expected = new Animal("1", "cat", "fish", Type.CAT, "14/02/2002", "imageUrl");
        Assertions.assertNotEquals(expected, actual);
    }
}
