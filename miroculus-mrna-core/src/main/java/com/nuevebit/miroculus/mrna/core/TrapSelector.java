/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.core;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

/**
 * A TrapSelector is responsible for finding the best suited miRNAs to use
 * in a test, according to disease's potential biomarks and mortality rates.
 *
 * @author emerino
 */
public class TrapSelector {
    
    /**
     * Select the best suited miRNAs to use as traps.
     * 
     * @param miRNAs
     * @return 
     */
    public List<MiRNA> selectTraps(List<Disease> diseases) {
        // first, we should sort the miRNAs, in order to make it easier to
        // discard those miRNAs with associated diseases that have no
        // potential biomarker.
        List<Disease> filtered = Lists.newArrayList();

        
    }
}
