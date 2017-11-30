/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Booking;
import entity.Home;
import entity.Role;
import entity.User;
import facades.DBUtil;
import facades.CollectiveFacadeFactory;
import facades.ICollectiveFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import security.PasswordStorage;

/**
 *
 * @author Peter
 */
public class HomeTest {

    private static ICollectiveFacade facade;
    private static DBUtil util;

    public HomeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        facade = CollectiveFacadeFactory.getTestInstance();
        util = new DBUtil(CollectiveFacadeFactory.getTestEmf());
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

    public void clear() {
        util.clearDB();
    }

    @Test
    public void testListAllHomes() {
        clear();
        Home home = new Home("forste sommerhus", "et sommerhus", "en addresse", 666, "a city", "200.35092,11.3323", 20000.0);
        facade.addHome(home);

        List<Home> res = facade.listAllHomes();

        Home[] expected = {home};
        Home[] resArr = res.toArray(expected);

        assertArrayEquals(expected, resArr);
    }

    @Test
    public void testBookHome() {
        clear();
        Home home = new Home("book home", "et sommerhus", "en addresse", 666, "a city", "200.35092,11.3323", 20000.0);
        Role role = new Role();
        User user;
        try {
            user = new User("testnavn", "testpassword");
            Booking booking = new Booking(10l, 20l, user);
            facade.bookHome(home.getId(), booking);
            facade.addHome(home);
            Home found = facade.listAllHomes().get(0);
            assertEquals(booking, found.getBookings().get(0));

        } catch (PasswordStorage.CannotPerformOperationException ex) {
            Logger.getLogger(HomeTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    @Ignore
    public void testFindHomesCloseTo() {
        clear();
        Home home = new Home("forste sommerhus", "et sommerhus", "en addresse", 666, "a city", "200.35092,11.3323", 20000.0);
        facade.addHome(home);
        home = new Home("andet sommerhus", "et sommerhus", "en addresse", 666, "a city", "201.35092,11.3323", 20000.0);
        facade.addHome(home);
        home = new Home("tredje sommerhus", "et sommerhus", "en addresse", 666, "a city", "202.35092,11.3323", 20000.0);
        facade.addHome(home);

        List<Home> test = facade.findHomesCloseTo("200.35092,11.3323", 0.5);
        assertEquals(1, test.size());

    }

    @Test
    public void testCreateSummerhouse() {
        clear();
        Home home = new Home("eneste sommerhus", "et sommerhus", "en addresse", 666, "a city", "202.35092,11.3323", 20000.0);
        facade.addHome(home);
        List<Home> test = facade.listAllHomes();

        assertEquals(1, test.size());
    }

}
