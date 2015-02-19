/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuevebit.miroculus.mrna.cli;

import com.nuevebit.miroculus.mrna.core.traps.MiRNAService;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Test application to obtain a list of MicroRNA traps.
 *
 * @author emerino
 */
@Named
@Singleton
public class MiRNATestApplication {

    @Inject
    private MiRNAService miRNAService;

    public void generateList() {
        for (String trap : miRNAService.findTraps()) {
            System.out.println(trap);
        }
    }
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "app-context.xml");

        MiRNATestApplication app = context.getBean(MiRNATestApplication.class);
        app.generateList();
    }
}
