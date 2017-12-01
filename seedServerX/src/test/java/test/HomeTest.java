package test;

import entity.Booking;
import entity.Home;
import entity.User;
import facades.DBUtil;
import facades.CollectiveFacadeFactory;
import facades.ICollectiveFacade;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Peter
 */

public class HomeTest {

    private static ICollectiveFacade facade;
    private static DBUtil util;

    public HomeTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
        facade = CollectiveFacadeFactory.getTestInstance();
        util = new DBUtil(CollectiveFacadeFactory.getTestEmf());
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {

    }

    @After
    public void tearDown()
    {

    }

    public void clear()
    {
        util.clearDB();
    }
    @Ignore
    @Test
    public void testListAllHomes()
    {
        clear();
        Home home = new Home("forste sommerhus", "et sommerhus", "en addresse", 666, "a city", "200.35092,11.3323", 20000.0);
        facade.addHome(home);

        List<Home> res = facade.listAllHomes();

        Home[] expected =
        {
            home
        };
        Home[] resArr = res.toArray(expected);

        assertArrayEquals(expected, resArr);
    }
    @Ignore
    @Test
    public void testBookHome()
    {
        clear();
        Home home = new Home("book home", "et sommerhus", "en addresse", 666, "a city", "200.35092,11.3323", 20000.0);
        
        User user = (User) facade.getUserByUserName("user");
        Booking booking = new Booking(10l, 20l, user);
        
        facade.addHome(home);
        Home savedHome = facade.listAllHomes().get(0);
        boolean success = facade.bookHome(savedHome.getId(), booking);

        assertEquals(true, success);
    }
    @Test
    public void testBookHomeFAIL()
    {
        Home home = new Home("book home", "et sommerhus", "en addresse", 666, "a city", "200.35092,11.3323", 20000.0);
        
        User user = (User) facade.getUserByUserName("user");
        Booking booking = new Booking(10l, 20l, user);
        
        facade.addHome(home);
        Home savedHome = facade.listAllHomes().get(0);
        //facade.bookHome(savedHome.getId(), booking);
        boolean success = facade.bookHome(savedHome.getId(), booking);

        assertEquals(false, success);
    }
    @Ignore
    @Test
    public void testCreateSummerhouse()
    {
        clear();
        Home home = new Home("eneste sommerhus", "et sommerhus", "en addresse", 666, "a city", "202.35092,11.3323", 20000.0);
        facade.addHome(home);
        List<Home> test = facade.listAllHomes();

        assertEquals(1, test.size());
    }

}
