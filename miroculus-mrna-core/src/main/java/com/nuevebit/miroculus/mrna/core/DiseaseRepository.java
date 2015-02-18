/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.core;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author emerino
 */
public interface DiseaseRepository extends CrudRepository<Disease, Long>{
    
    Disease findByName(String name);
}
