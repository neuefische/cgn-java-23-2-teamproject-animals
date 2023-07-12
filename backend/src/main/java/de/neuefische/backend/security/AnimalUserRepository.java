package de.neuefische.backend.security;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalUserRepository extends MongoRepository<AnimalUser, String> {
    Optional<AnimalUser> findByUsername(String username);
}