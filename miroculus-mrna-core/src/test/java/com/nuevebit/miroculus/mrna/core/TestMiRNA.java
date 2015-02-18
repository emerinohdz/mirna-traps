/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nuevebit.miroculus.mrna.core;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author emerino
 */
public class TestMiRNA {

    public TestMiRNA() {
    }

    @Test
    public void testNameNormalization() {
        MiRNA miRNA = new MiRNA("hsa-miR-10a*");
        assertTrue(miRNA.getName().equals("hsa-miR-10a"));

        miRNA = new MiRNA("hsa-miR-140-3p*");
        assertTrue(miRNA.getName().equals("hsa-miR-140-3p"));
    }

    @Test
    public void testShortName() {
        MiRNA miRNA = new MiRNA("hsa-miR-10a");
        assertTrue(miRNA.getShortName().equals("hsa-miR-10"));
        assertTrue(!miRNA.getShortName().equals(miRNA.getName()));
        
        miRNA = new MiRNA("hsa-miR-10b");
        assertTrue(miRNA.getShortName().equals("hsa-miR-10"));

        miRNA = new MiRNA("hsa-miR-140-3p");
        assertTrue(miRNA.getShortName().equals("hsa-miR-140"));
        
        miRNA = new MiRNA("hsa-miR-140-5p");
        assertTrue(miRNA.getShortName().equals("hsa-miR-140"));

        // both name and short name should be the same
        miRNA = new MiRNA("hsa-miR-1");
        assertTrue(miRNA.getShortName().equals(miRNA.getName()));
    }

    @Test
    public void testEquals() {
        MiRNA miRNA1 = new MiRNA("hsa-miR-10a");
        MiRNA miRNA2 = new MiRNA("hsa-miR-10a");
        assertTrue(miRNA1.equals(miRNA2));
        
        miRNA1 = new MiRNA("hsa-miR-10a");
        miRNA2 = new MiRNA("hsa-miR-10b");
        assertTrue(!miRNA1.equals(miRNA2));

        miRNA1 = new MiRNA("hsa-miR-140-3p");
        miRNA2 = new MiRNA("hsa-miR-140-5p");
        assertTrue(!miRNA1.equals(miRNA2));

        miRNA1 = new MiRNA("hsa-miR-10a");
        miRNA2 = new MiRNA("hsa-miR-140-5p");
        assertTrue(!miRNA1.equals(miRNA2));
    }
}