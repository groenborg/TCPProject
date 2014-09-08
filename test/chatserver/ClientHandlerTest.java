package chatserver;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author simon
 */
public class ClientHandlerTest {

    private SocketMock mock;
    private ClientHandler handler;

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
        handler = new ClientHandler(mock);
    }

    @After
    public void tearDown() {
        mock = null;
        handler = null;
    }

    /**
     * Test of run method, of class ClientHandler.
     */
    @Test
    public void testOpenStream() {
        System.out.println("open streams");
        boolean expResult = true;
        try {
            handler.openStreams(mock);
        } catch (IOException e) {
            expResult = false;
        }
        assertEquals(true, expResult);
    }

    @Test
    public void testCloseServer() {
        ClientHandler h = new ClientHandler(mock);
        boolean error = true;
        try {
            h.openStreams(mock);
            h.shutDownClient();
        } catch (Exception e) {
            error = false;
        }
        assertTrue(error && mock.closeCalled);

    }

}
