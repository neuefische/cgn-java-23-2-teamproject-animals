package de.neuefische.backend;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnimalService {


    private final UuidService uuidService;
    private final Cloudinary cloudinary;
    private final AnimalRepository animalRepository;


    public String getImageURL(MultipartFile multipartFile) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("folder", "images");
            Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(), params);
            String imageURL = uploadResult.get("url").toString();
            return imageURL;
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    public Animal addAnimal(MultipartFile file, DtoAnimal dtoAnimal) throws Exception {
        String id = uuidService.generateUUID();
        Animal animal = new Animal();
        animal.setImageUrl(getImageURL(file));
        animal.setId(id);
        animal.setName(dtoAnimal.getName());
        animal.setFoods(dtoAnimal.getFoods());
        animal.setType(Type.OTHER);
        animal.setDateOfBirth(dtoAnimal.getDateOfBirth());
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
        Animal isEditAnimal = animalRepository.findById(id).orElseThrow(() -> new RuntimeException("could not find animal"));
        isEditAnimal.setName(dtoAnimal.getName());
        return animalRepository.save(isEditAnimal);
    }
}
