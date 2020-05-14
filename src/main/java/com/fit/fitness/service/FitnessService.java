package com.fit.fitness.service;


import com.fit.fitness.dao.FitnessDao;
import com.fit.fitness.entity.FitnessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitnessService {

    @Autowired
    FitnessDao fitnessDao;

    public List<FitnessEntity> getAllFitness(){
        return fitnessDao.findAll();
    }
}
