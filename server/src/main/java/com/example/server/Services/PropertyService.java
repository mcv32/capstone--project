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
        Optional<Property> property = propertyRepository.findById(id);
        if(property.isPresent()){
            Property p = property.get();
            return p;
        }
        return null;
    }

    public Property createProperty(Property property) {
        Property newProperty = new Property();
        newProperty.setProperty_balance(property.getProperty_balance());
        newProperty.setCity(property.getCity());
        newProperty.setName(property.getName());
        newProperty.setZip(property.getZip());
        newProperty.setState(property.getState());
        newProperty.setAddress_line_2(property.getAddress_line_2());
        newProperty.setAddress_street(property.getAddress_street());
        newProperty.setStatus(property.getStatus());
        propertyRepository.save(newProperty);
        return newProperty;
    }

    public Property updateProperty(Long id, Property updatedProperty) {

        Optional<Property> property = propertyRepository.findById(id);
        if(property.isPresent()){
            Property p = property.get();
            return p;
        }
        return null;
    }

    public void deleteProperty(Long id) {
        propertyRepository.findById(id);
    }
}
