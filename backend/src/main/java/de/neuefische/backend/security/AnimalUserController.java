package de.neuefische.backend.security;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
    public String getUserInfo2(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    @PostMapping("Login")
    public String login(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
