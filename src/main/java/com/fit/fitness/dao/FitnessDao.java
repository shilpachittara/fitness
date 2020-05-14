package com.fit.fitness.dao;

import com.fit.fitness.entity.FitnessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FitnessDao extends JpaRepository<FitnessEntity, Long> {
}
