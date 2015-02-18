/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.core;

import java.util.List;

/**
 * Service that helps with operations on miRNAs.
 *
 * @author emerino
 */
public interface MiRNAService {
    
    /**
     * Find the best suited traps to provide a standard 96 well plate. 
     * 
     * @return a list of 94 MiRNA suited for the test.
     */
    List<MiRNA> findTraps();
}
