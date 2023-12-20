package com.example.server.Repositories;

import com.example.server.Models.Message;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message, Integer> {
    Message findById(int id);
}
