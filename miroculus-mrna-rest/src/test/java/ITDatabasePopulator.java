/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.nuevebit.miroculus.mrna.rest.DatabasePopulator;
import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author emerino
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app-context.xml")
public class ITDatabasePopulator {

    @Inject
    private DatabasePopulator populator;

    public ITDatabasePopulator() {
    }

    @Test
    public void testPopulate() {
        populator.populate();
    }
}