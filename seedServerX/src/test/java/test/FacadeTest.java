/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.User;
import facades.ICollectiveFacade;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.mockito.InOrder;
import org.mockito.Mock;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Peter
 */
/*
@Ignore
public class FacadeTest {
    
    @Mock
    private ICollectiveFacade collectiveFacade;
    private User user;
    
    
    public FacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
    public void test(){
        collectiveFacade = mock(ICollectiveFacade.class);
        user = mock(User.class);
        
        InOrder inOrder = inOrder(collectiveFacade,user);
        
        
        when(collectiveFacade.getUserByUserId("1")).thenReturn(user);
        
    }
}
*/
