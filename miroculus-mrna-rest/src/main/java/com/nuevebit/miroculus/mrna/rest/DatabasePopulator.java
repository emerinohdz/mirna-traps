/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.rest;

import com.nuevebit.miroculus.mrna.core.AuthorRepository;
import com.nuevebit.miroculus.mrna.core.CorrelationDiscoveryRepository;
import com.nuevebit.miroculus.mrna.core.DiscoveryMethodRepository;
import com.nuevebit.miroculus.mrna.core.DiseaseRepository;
import com.nuevebit.miroculus.mrna.core.MiRNARepository;
import com.nuevebit.miroculus.mrna.core.PublicationRepository;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * This class handles persistence of the provided CSV and downloaded json
 * with mortality rates.
 *
 * @author emerino
 */
@Named
@Singleton
public class DatabasePopulator {
    
    @Inject
    private MiRNARepository miRNARepository;

    @Inject
    private DiseaseRepository diseaseRepository;

    @Inject
    private CorrelationDiscoveryRepository correlationDiscoveryRepository;

    @Inject
    private DiscoveryMethodRepository discoveryMethodRepository;

    @Inject
    private AuthorRepository authorRepository;

    @Inject
    private PublicationRepository publicationRepository;

    /**
     * Retrieve information from CSV and json files, and populate db with
     * this data.
     */
    public void populate() {
        
    }
}
