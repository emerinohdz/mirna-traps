/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.rest.resource;

import com.google.common.collect.Lists;
import com.nuevebit.miroculus.mrna.core.MiRNA;
import com.nuevebit.miroculus.mrna.core.MiRNARepository;
import java.util.List;
import javax.inject.Inject;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 *
 * @author emerino
 */
public class MiRNAResource extends ServerResource {
    
    @Inject
    private MiRNARepository miRNARepository;

    @Get("json")
    public List<MiRNA> getMiRNAs() {
        return Lists.newArrayList(miRNARepository.findAll());
    }
}
