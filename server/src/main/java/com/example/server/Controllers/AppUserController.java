package com.example.server.Controllers;


import com.example.server.Models.AppUser;
import com.example.server.Services.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path= "/users")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public List<AppUser> getAllAccounts() {
        return appUserService.getAllAccounts();
    }

    @PostMapping
    public ResponseEntity<Optional<AppUser>> getUserByEmail(@RequestBody String email){
        Optional<AppUser> user = appUserService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }



    // get the login credentials into a pojo
    // use pojo to hit a get controller
    // send get request to db w pojo info
    // compare results to submitted payload
    // based on return value submit a generic route to frontend that posts the return results to an api endpoint


}