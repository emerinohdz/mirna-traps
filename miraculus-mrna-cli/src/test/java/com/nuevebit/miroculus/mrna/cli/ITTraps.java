package com.nuevebit.miroculus.mrna.cli;

import com.nuevebit.miroculus.mrna.core.traps.MiRNAService;
import java.util.List;
import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Helps testing the obtained traps from the db.
 *
 * @author emerino
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app-context.xml")
public class ITTraps {

    @Inject
    private MiRNAService miRNAService;
    
    @Test
    public void testFindTraps() {
        List<String> traps = miRNAService.findTraps();
        assertTrue(traps.size() == 94);
    }
}
