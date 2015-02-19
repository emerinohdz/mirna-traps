/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.core.traps;

import com.google.common.collect.Lists;
import com.nuevebit.miroculus.mrna.core.CorrelationDiscovery;
import com.nuevebit.miroculus.mrna.core.Disease;
import com.nuevebit.miroculus.mrna.core.MiRNA;
import java.util.List;
import java.util.Set;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author emerino
 */
public class TestTrapSelector {

    @Test
    public void testSelectTraps() {
        List<CorrelationDiscovery> discoveries = Lists.newArrayList();

        discoveries.add(new CorrelationDiscovery(
                new MiRNA("hsa-miR-10a*"),
                new Disease(3.3),
                0));

        discoveries.add(new CorrelationDiscovery(
                new MiRNA("hsa-let-7b"),
                new Disease(3.3),
                1));

        discoveries.add(new CorrelationDiscovery(
                new MiRNA("hsa-let-7a"),
                new Disease(2.2),
                1));

        discoveries.add(new CorrelationDiscovery(
                new MiRNA("hsa-miR-10a"),
                new Disease(2.1),
                0));

        discoveries.add(new CorrelationDiscovery(
                new MiRNA("hsa-miR-10"),
                new Disease(5.1),
                1));

        discoveries.add(new CorrelationDiscovery(
                new MiRNA("hsa-miR-106a"),
                new Disease(1.1),
                0));

        TrapSelector selector = new TrapSelector();
        Set<String> traps = selector.selectTraps(discoveries, 6); // 6 max

        assertTrue(traps.size() == 3); // repeated are removed
        assertTrue(traps.contains("hsa-miR-10"));
        assertTrue(traps.contains("hsa-let-7"));
        assertTrue(traps.contains("hsa-miR-106"));

        traps = selector.selectTraps(discoveries, 2); // 2 max

        assertTrue(traps.size() == 2);
        assertTrue(traps.contains("hsa-miR-10"));
        assertTrue(traps.contains("hsa-let-7"));

        traps = selector.selectTraps(discoveries, 1); // 1 max

        assertTrue(traps.size() == 1);
        assertTrue(traps.contains("hsa-miR-10"));
    }
}
