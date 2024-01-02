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
        newProperty.setProperty_profit_and_loss(property.getProperty_profit_and_loss());
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

    public Property updateProperty(Long id, String name, String address_street, String address_line_2, String state, String city, String zip, String status, double property_profit_and_loss) {

            propertyRepository.updateProperty(id, name, address_street, address_line_2, state, city, zip, status, property_profit_and_loss);
//            p.setName(name);
//            p.setAddress_street(address_street);
//            p.setAddress_line_2(address_line_2);
//            p.setState(state);
//            p.setCity(city);
//            p.setZip(zip);
//            p.setStatus(status);
//            p.setProperty_balance(property_balance);
//            propertyRepository.save(p);
        Optional<Property> property = propertyRepository.findById(id);
        if(property.isPresent()){
            Property p = property.get();
            return p;
        }
        return null;
    }

    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}
