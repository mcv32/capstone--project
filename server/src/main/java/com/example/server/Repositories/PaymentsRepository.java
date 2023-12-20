package com.example.server.Repositories;

import com.example.server.Models.PaymentType;
import com.example.server.Models.Payments;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PaymentsRepository extends JpaRepository<Payments, PaymentType> {
}
