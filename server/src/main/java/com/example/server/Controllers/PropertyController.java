package com.example.server.Controllers;

import com.example.server.Models.Property;
import com.example.server.Services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/properties")
public class PropertyController {
    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public List<Property> getAllProperties(){
        return propertyService.getAllProperties();
    }
    @PostMapping
    public Property getPropertyById(@RequestBody int id){
        Property property = propertyService.getPropertyById(id);
        return property;
    }

}
