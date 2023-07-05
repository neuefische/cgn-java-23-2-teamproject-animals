package de.neuefische.backend;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, String> {

    /*private List<Animal> animals;

    private Animal result;


    public AnimalRepository() {
        this.animals = new ArrayList<>();
    }

    public Animal addAnimal(Animal animal) {
        this.animals.add(animal);
        return animal;
    }

    Function<String, Animal> getAnimalById = (id) -> {
        animals.forEach(animal -> {
            if (animal.getId().equals(id)) result = animal;
        });
        return result;
    };

    public void deleteAnimal(String id) {
        Animal animal = getAnimalById.apply(id);
        animals.remove(animal);
    }


    public List<Animal> getAllAnimals() {
        return animals;
    }

    public Animal editAnimal(Animal animal, String id) {
        Animal newAnimal = getAnimalById.apply(id);
        newAnimal.setName(animal.getName());

        return newAnimal;
    }*/


}
