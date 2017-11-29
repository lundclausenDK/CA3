/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Booking;
import entity.Home;
import facades.CollectiveFacadeFactory;
import facades.ICollectiveFacade;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Peter
 */
public class HomeTest {

    private static ICollectiveFacade facade;
    
    public HomeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        facade = CollectiveFacadeFactory.getTestInstance();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    
    @Test
    public void testListAllHomes() {
        List<Home> res = facade.listAllHomes();
        
        Home[] expected = {};
        Home[] resArr = res.toArray(expected);
        
        assertArrayEquals(expected, resArr);
    }

    @Test
    public void testBookHome() {
        Home home = new Home();
        Booking booking = new Booking();
        facade.addHome(home);
        
        facade.bookHome(home.getId(), booking);
        Home found = facade.listAllHomes().get(0);
        
        
        assertEquals(booking, found.getBookings().get(0));
    }

    @Test
    public void testFindHomeById() {
        fail();
    }

    @Test
    public void testFindHomesCloseTo() {
        fail();
    }
}
