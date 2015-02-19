/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.rest.resource;

import com.google.common.collect.Lists;
import com.nuevebit.miroculus.mrna.core.CorrelationDiscovery;
import com.nuevebit.miroculus.mrna.core.CorrelationDiscoveryRepository;
import java.util.List;
import javax.inject.Inject;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 *
 * @author emerino
 */
public class CorrelationDiscoveryResource extends ServerResource {

    @Inject
    private CorrelationDiscoveryRepository correlationDiscoveryRepository;
    
    @Get("json")
    public List<CorrelationDiscovery> getDiscoveries() {
        return Lists.newArrayList(correlationDiscoveryRepository.findAll());
    }
}
