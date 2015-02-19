/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.rest.resource;

import com.google.common.collect.Lists;
import com.nuevebit.miroculus.mrna.core.Disease;
import com.nuevebit.miroculus.mrna.core.DiseaseRepository;
import java.util.List;
import javax.inject.Inject;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 *
 * @author emerino
 */
public class DiseaseResource extends ServerResource {

    @Inject
    private DiseaseRepository diseaseRepository;
    
    @Get("json")
    public List<Disease> getDiseases() {
        return Lists.newArrayList(diseaseRepository.findAll());
    }
}
