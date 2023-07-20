package de.neuefische.backend;
import de.neuefische.backend.security.AnimalUser;
import de.neuefische.backend.security.AnimalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final UuidService uuidService;
    private final FileUploadService fileUploadService;
    private final AnimalRepository animalRepository;
    private final AnimalUserRepository animalUserRepository;


    public Animal addAnimal(MultipartFile file, DtoAnimal dtoAnimal) throws Exception {
        String id = uuidService.generateUUID();
        Animal animal = new Animal();
        animal.setImageUrl(fileUploadService.getImageURL(file));
        animal.setId(id);
        animal.setName(dtoAnimal.getName());
        animal.setFavoriteFood(dtoAnimal.getFavoriteFood());
        animal.setType(dtoAnimal.getType());
        animal.setDateOfBirth(dtoAnimal.getDateOfBirth());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AnimalUser animalUser = animalUserRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("could not find username"));
        animal.setAnimalUserId(animalUser.id());
        animalRepository.save(animal);
        return animal;
    }

    public void deleteAnimal(String id) {
        animalRepository.deleteById(id);
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Animal editAnimal(DtoAnimal dtoAnimal, String id) throws Exception {
        Animal isEditAnimal = animalRepository.findById(id).orElseThrow(() -> new Exception("could not find animal"));
        isEditAnimal.setName(dtoAnimal.getName());
        isEditAnimal.setFavoriteFood(dtoAnimal.getFavoriteFood());
        isEditAnimal.setDateOfBirth(dtoAnimal.getDateOfBirth());
        isEditAnimal.setType(dtoAnimal.getType());
        return animalRepository.save(isEditAnimal);
    }

}
