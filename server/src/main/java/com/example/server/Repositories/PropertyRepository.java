package com.example.server.Repositories;

import com.example.server.Models.Property;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PropertyRepository extends JpaRepository <Property, Integer> {

    Property findById(int id);
}
