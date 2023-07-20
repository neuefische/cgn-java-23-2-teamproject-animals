package de.neuefische.backend;

import de.neuefische.backend.security.AnimalUser;
import de.neuefische.backend.security.AnimalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalUserService {
    private final AnimalUserRepository animalUserRepository;

    public String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public AnimalUser getCurrentUserId() throws Exception {
        return animalUserRepository.findByUsername(getCurrentUserName())
                .orElseThrow(() -> new Exception("cannot find user by username"));
    }
}
