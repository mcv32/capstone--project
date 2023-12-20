package com.example.server.Repositories;


import com.example.server.Models.TransactionTests;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TransactionTestsRepository extends JpaRepository <TransactionTests, Integer> {
    TransactionTests findById(int id);
}
