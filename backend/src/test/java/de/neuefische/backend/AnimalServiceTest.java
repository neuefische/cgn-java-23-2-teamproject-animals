package de.neuefische.backend;

import com.cloudinary.Cloudinary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


class AnimalServiceTest {
    AnimalRepository animalRepository = spy(AnimalRepository.class);
    UuidService uuidService = spy(UuidService.class);

    Cloudinary cloudinary = spy(Cloudinary.class);

    AnimalService animalService = new AnimalService(uuidService, cloudinary, animalRepository);


    @Test
    void test_postAnimal() throws Exception {
        DtoAnimal dtoAnimal = new DtoAnimal("cat", "cereals", "31-03-1998", Type.CAT);
        Animal expected = new Animal(
                "123", dtoAnimal.getName(),
                dtoAnimal.getFavoriteFood(),
                dtoAnimal.getType(),
                dtoAnimal.getDateOfBirth(),
                "imageUrl"
        );
        MultipartFile mockFile = new MockMultipartFile(
                "file",          // parameter name
                "test-file.txt", // original file name
                "text/plain",    // content type
                "File content".getBytes() // file content
        );
        when(uuidService.generateUUID()).thenReturn("123");
        when(animalRepository.save(expected)).thenReturn(expected);
        Animal actual = animalService.addAnimal(mockFile, dtoAnimal);
        Assertions.assertEquals(expected, actual);
    }

  /*   @Test
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
     }*/
 }
