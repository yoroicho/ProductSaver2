/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMaking;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 00499
 */
public class ConfigurationXMLTest {
    
    public ConfigurationXMLTest() {
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

    /**
     * Test of getProperty method, of class ConfigurationXML.
     */
    @Test
    public void testGetProperty() {
        System.out.println("getProperty");
        String key = "";
        ConfigurationXML instance = null;
        String expResult = "";
        String result = instance.getProperty(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addProperty method, of class ConfigurationXML.
     */
    @Test
    public void testAddProperty() {
        System.out.println("addProperty");
        String key = "";
        String value = "";
        ConfigurationXML instance = null;
        instance.addProperty(key, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of storeToXML method, of class ConfigurationXML.
     */
    @Test
    public void testStoreToXML() {
        System.out.println("storeToXML");
        String filename = "";
        String comments = "";
        ConfigurationXML instance = null;
        instance.storeToXML(filename, comments);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class ConfigurationXML.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ConfigurationXML.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
