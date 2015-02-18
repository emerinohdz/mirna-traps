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
        Disease d1 = new Disease(0, 3.3);
        Disease d2 = new Disease(1, 3.3d);
        Disease d3 = new Disease(1, 2.2);
        Disease d4 = new Disease(0, 2.1);

        List<Disease> diseases = Lists.newArrayList(d1, d2, d3, d4);
        Collections.sort(diseases);

        assertTrue(diseases.get(0).equals(d2));
        assertTrue(diseases.get(1).equals(d3));
        assertTrue(diseases.get(2).equals(d1));
        assertTrue(diseases.get(3).equals(d4));
    }

}