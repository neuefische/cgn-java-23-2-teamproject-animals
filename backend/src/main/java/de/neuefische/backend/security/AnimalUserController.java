package de.neuefische.backend.security;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class AnimalUserController {

    @GetMapping("me")
    public String getUserInfo(Principal principal){
        if(principal==null){
            return "anonymousUser";
        }
        return principal.getName();
    }

    @GetMapping("me1")
    public String getUserInfo2(Principal principal){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
