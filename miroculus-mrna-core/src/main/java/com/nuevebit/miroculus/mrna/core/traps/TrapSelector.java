/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.core.traps;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.nuevebit.miroculus.mrna.core.CorrelationDiscovery;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * A TrapSelector is responsible for finding the best suited miRNAs to use in a
 * test, according to disease's potential biomarks and mortality rates.
 *
 * @author emerino
 */
public class TrapSelector {

    /**
     * Select the best suited miRNAs to use as traps. Return the miRNAs as
     * Strings, since they are tested using short name.
     *
     * @param discoveries the discoveries correlations to use for finding traps.
     * @param limit the limit of traps to find
     * @return the selected traps to use, as a Set of Strings.
     */
    public Set<String> selectTraps(List<CorrelationDiscovery> discoveries,
            int limit) {
        // create copy
        List<CorrelationDiscovery> sorted = Lists.newArrayList(discoveries);

        // first, we should sort the diseases, in order to make it easier to
        // discard those miRNAs with associated diseases that have no
        // potential biomarker and lower mortality rates.
        Collections.sort(sorted);

        // having the discoveries sorted, it's easy to find the traps
        Set<String> traps = Sets.newHashSet();
        for (CorrelationDiscovery discovery : sorted) {
            if (traps.size() < limit) {
                System.out.println(discovery.getPotentialBiomarker());
                traps.add(discovery.getMiRNA().getShortName());
            } else {
                break;
            }
        }

        return traps;
    }
}
