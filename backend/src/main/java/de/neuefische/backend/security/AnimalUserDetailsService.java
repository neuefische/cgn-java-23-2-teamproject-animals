package de.neuefische.backend.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;
@Service
public class AnimalUserDetailsService implements UserDetailsService {

    private final AnimalUserRepository animalUserRepository;

    public AnimalUserDetailsService(AnimalUserRepository animalUserRepository) {
        this.animalUserRepository = animalUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AnimalUser animalUser = animalUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " +username + " not found!"));
        return new User(animalUser.username(),animalUser.password(), Collections.emptyList());
    }
}