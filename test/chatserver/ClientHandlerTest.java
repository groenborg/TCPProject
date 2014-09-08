/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatserver;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author simon
 */
public class ClientHandlerTest {
    
    private SocketMock mock;
    
    public ClientHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mock = new SocketMock();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of run method, of class ClientHandler.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        ClientHandler instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testCloseServer(){
       try{
        ClientHandler h = new ClientHandler(mock);
        h.shutDownClient();
       }catch(IOException ex){
           System.out.println("lol");
       }
       
       mock.closeCalled = true;
    }
    
    
}
