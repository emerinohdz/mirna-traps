/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.rest.resource;

import com.nuevebit.miroculus.mrna.core.traps.MiRNAService;
import java.util.List;
import javax.inject.Inject;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 *
 * @author emerino
 */
public class TrapResource extends ServerResource {
 
    @Inject
    private MiRNAService miRNAService;

    @Get("json")
    public List<String> getTraps() {
        return miRNAService.findTraps();
    }
}
