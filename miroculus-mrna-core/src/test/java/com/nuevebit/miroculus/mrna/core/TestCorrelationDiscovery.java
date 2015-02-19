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
public class TestCorrelationDiscovery {

    public TestCorrelationDiscovery() {
    }

    @Test
    public void testCompareTo() {
        CorrelationDiscovery d1 = new CorrelationDiscovery(
                new Disease(3.3), 0);
        CorrelationDiscovery d2 = new CorrelationDiscovery(
                new Disease(3.3), 1);
        CorrelationDiscovery d3 = new CorrelationDiscovery(
                new Disease(2.2), 1);
        CorrelationDiscovery d4 = new CorrelationDiscovery(
                new Disease(2.1), 0);

        List<CorrelationDiscovery> diseases = Lists.newArrayList(d1, d2, d3, d4);
        Collections.sort(diseases);

        assertTrue(diseases.get(0).equals(d2));
        assertTrue(diseases.get(1).equals(d3));
        assertTrue(diseases.get(2).equals(d1));
        assertTrue(diseases.get(3).equals(d4));
    }

}