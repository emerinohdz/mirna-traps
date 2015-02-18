/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nuevebit.miroculus.mrna.core;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author emerino
 */
public class TestDisease {

    public TestDisease() {
    }

    @Test
    public void testCompareTo() {
        Disease d1 = new Disease(1);
        Disease d2 = new Disease(0);
        Disease d3 = new Disease(0);
        Disease d4 = new Disease(1);

        List<Disease> diseases = Lists.newArrayList(d1, d2, d3, d4);
        Collections.sort(diseases);

        assertTrue(diseases.get(0).getPotentialBiomarker() == 1);
        assertTrue(diseases.get(1).getPotentialBiomarker() == 1);
        assertTrue(diseases.get(2).getPotentialBiomarker() == 0);
        assertTrue(diseases.get(3).getPotentialBiomarker() == 0);
    }

}