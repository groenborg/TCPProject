package utility;

import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Grønborg
 */
public class UtilityTest {

    private Properties properties;

    public UtilityTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.properties = Utility.initProperties("server.properties");
    }

    @After
    public void tearDown() {
        this.properties = null;
    }

    /**
     * Test of initProperties method, of class Utility.
     */
    @Test
    public void testInitProperties() {
        System.out.println("initProperties");
        String serverProperties = "server.properties";
        Properties result = Utility.initProperties(serverProperties);
        assertNotNull(result);
    }

    @Test
    public void testLoadportProperty() {
        System.out.println("load port");
        String expresult = this.properties.getProperty("port");
        assertEquals(expresult, "8014");
    }

    @Test
    public void testLoadIpaddressProperty() {
        System.out.println("load ipaddress");
        String expString = this.properties.getProperty("ipaddress");
        assertEquals(expString, "10.0.1.11");
    }

}
