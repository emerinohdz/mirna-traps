/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.core.traps;

import com.google.common.collect.Lists;
import com.nuevebit.miroculus.mrna.core.CorrelationDiscoveryRepository;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Default MiRNA service provider.
 *
 * @author emerino
 */
@Named
@Singleton
public class MiRNAServiceProvider implements MiRNAService {

    private TrapSelector trapSelector = new TrapSelector();

    @Inject
    private CorrelationDiscoveryRepository correlationDiscoveryRepository;

    @Override
    public List<String> findTraps() {
        Set<String> traps = trapSelector.selectTraps(
                Lists.newArrayList(correlationDiscoveryRepository.findAll()), 
                94);

        // perhaps traps should be ordered inside TrapSelector?
        List<String> orderedTraps = Lists.newArrayList(traps);
        Collections.sort(orderedTraps);

        return orderedTraps;
    }
}
