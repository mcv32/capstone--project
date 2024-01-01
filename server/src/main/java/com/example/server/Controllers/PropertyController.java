package com.example.server.Controllers;

import com.example.server.Models.Property;
import com.example.server.Services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/properties")
public class PropertyController {
    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/getProperty")
    public Property getPropertyById(@RequestBody Map<String, Long> requestBody) {
        Property property = propertyService.getPropertyById(requestBody.get("id"));
        return property;
    }

    @PostMapping("/create")
    public Property createProperty(@RequestBody Property property) {
        return propertyService.createProperty(property);
    }

    @PutMapping("/update")
    public Property updateProperty(@RequestBody Map<String, String> requestBody) {
        return propertyService.updateProperty(Long.valueOf(requestBody.get("id")).longValue(),
                requestBody.get("name"),
                requestBody.get("address_street"),
                requestBody.get("address_line_2"),
                requestBody.get("state"),
                requestBody.get("city"),
                requestBody.get("zip"),
                requestBody.get("status"),
                Double.parseDouble(requestBody.get("property_balance"))
        );
    }

    @DeleteMapping("/delete")
    public String deleteProperty(@RequestBody Map<String, Long> requestBody) {
        propertyService.deleteProperty(requestBody.get("id"));
        return "Property with ID " + requestBody.get("id") + " deleted successfully.";
    }
}