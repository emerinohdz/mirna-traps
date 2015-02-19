package com.nuevebit.miroculus.mrna.cli;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.InputStream;
import javax.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author emerino
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app-context.xml")
@Transactional(rollbackFor = Exception.class)
public class ITDatabasePopulator {

    @Inject
    private DatabasePopulator populator;

    public ITDatabasePopulator() {

    }

    @Test
    @Rollback(true)
    public void testPopulate() throws Exception {
        String csv = IOUtils.toString(ITDatabasePopulator.class
                .getResourceAsStream("/microRNAs.csv"));

        InputStream mortalityRatesStream = ITDatabasePopulator.class
                .getResourceAsStream("disease_mortality_rates.txt");

        populator.populate(csv, mortalityRatesStream);
    }
}
