package com.example.server.Services;

import com.example.server.Models.Property;
import com.example.server.Repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository){
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getAllProperties(){
        return propertyRepository.findAll();
    }

    public Property getPropertyById(int id) {
        return propertyRepository.findById(id);
    }
}
