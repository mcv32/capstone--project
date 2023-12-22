package com.example.server.Services;

import com.example.server.Models.Property;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    private final List<Property> properties = new ArrayList<>();

    public List<Property> getAllProperties() {
        return properties;
    }

    public Property getPropertyById(int id) {
        Optional<Property> optionalProperty = properties.stream()
                .filter(property -> property.getProperty_id() == id)
                .findFirst();
        return optionalProperty.orElse(null);
    }

    public Property createProperty(Property property) {
        int nextId = properties.isEmpty() ? 1 : properties.get(properties.size() - 1).getProperty_id() + 1;
        property.setProperty_id(nextId);
        properties.add(property);
        return property;
    }

    public Property updateProperty(int id, Property updatedProperty) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getProperty_id() == id) {
                updatedProperty.setProperty_id(id);
                properties.set(i, updatedProperty);
                return updatedProperty;
            }
        }
        return null;
    }

    public void deleteProperty(int id) {
        properties.removeIf(property -> property.getProperty_id() == id);
    }
}
