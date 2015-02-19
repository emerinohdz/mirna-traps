/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.rest;

import com.nuevebit.miroculus.mrna.cli.DatabasePopulator;
import com.nuevebit.miroculus.mrna.core.MiRNARepository;
import java.io.IOException;
import javax.inject.Inject;
import org.restlet.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author emerino
 */
public class MiRNARestlet extends Application {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(MiRNARestlet.class);

    @Inject
    public MiRNARestlet(
            MiRNARepository miRNARepository,
            DatabasePopulator databasePopulator) throws IOException {
        
        setName("RESTful miRNA Trap Tester");
        setDescription("miRNA trap tester");

        setOwner("Edgar Merino");
        setAuthor("NueveBit");

        getStatusService().setContactEmail("emerino@nuevebit.com");

        // first time deploy, load db
        if (miRNARepository.count() == 0) {
            databasePopulator.populate();
        }
    }

}
