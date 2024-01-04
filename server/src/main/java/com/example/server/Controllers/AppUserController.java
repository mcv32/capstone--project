package com.example.server.Controllers;


import com.example.server.Models.AppUser;
import com.example.server.Services.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.server.DTO.AppUserDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path= "/users")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    // get all app users
    @GetMapping
    public List<AppUser> getAllAccounts() {
        return appUserService.getAllAccounts();
    }

    // get user details for tenant dashboard
    @CrossOrigin(origins = {"http://localhost:3000/"}, allowedHeaders = {"Authorization"})
    @PostMapping("/details")
    public ResponseEntity<AppUserDto> getUserDetailsByEmail(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        AppUserDto userDetails = appUserService.getAppUserDetailsByEmail(email);
        if (userDetails != null) {
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.notFound().build(); // Or handle appropriately
        }
    }

    // update user email, first name, last name, and phone number
    @PostMapping("/update")
    public AppUser updateUser(@RequestBody Map<String, String> requestBody){
        return appUserService.updateAppUser(requestBody.get("old_email"), requestBody.get("new_email"),
                requestBody.get("firstName"), requestBody.get("lastName"), requestBody.get("phoneNumber"));
    }

    // update user role specifically
    @PostMapping("/update/role")
    public AppUser updateUserRole(@RequestBody Map<String, String > requestBody){
        return appUserService.updateUserRole(requestBody.get("role"), requestBody.get("email"));
    }

    // delete user
    @DeleteMapping("/delete")
    public String deleteUser(@RequestBody Map<String, String > requestBody){
        appUserService.deleteUser(requestBody.get("email"));
        return "User deleted successfully";
    }



    // get the login credentials into a pojo
    // use pojo to hit a get controller
    // send get request to db w pojo info
    // compare results to submitted payload
    // based on return value submit a generic route to frontend that posts the return results to an api endpoint


}