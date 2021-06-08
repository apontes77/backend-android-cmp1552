package com.pucgoias.devmobileapps.trabalhofinal.repositories;

import com.pucgoias.devmobileapps.trabalhofinal.models.TrafficViolation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Esta interface herda JpaRepository, de modo que todas as operações elementares CRUD
 * são geradas pelo próprio Spring Data JPA.
 */
@Repository
public interface TrafficViolationRepository extends JpaRepository<TrafficViolation, Integer> {
}
