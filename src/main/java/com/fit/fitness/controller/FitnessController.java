package com.fit.fitness.controller;


import com.fit.fitness.entity.FitnessEntity;
import com.fit.fitness.service.FitnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FitnessController {

    @Autowired
    FitnessService service;

    @RequestMapping(method = RequestMethod.GET, value = "/getAllFitnessDetails")
    public ResponseEntity<List<FitnessEntity>> getAll() {
        try {
            List<FitnessEntity> fitnessEntities = service.getAllFitness();
            return new ResponseEntity<>(fitnessEntities, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("error" + e.getCause() + e.getStackTrace());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
