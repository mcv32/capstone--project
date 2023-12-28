package com.example.server.Services;

import com.example.server.Models.Property;
import com.example.server.Repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    @Autowired
    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }


    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    public Property createProperty(Property property) {
        return propertyRepository.findById(property.getProperty_id());
    }

    public Property updateProperty(Long id, Property updatedProperty) {
        return propertyRepository.findById(id);
    }

    public void deleteProperty(Long id) {
        propertyRepository.findById(id);
    }
}
