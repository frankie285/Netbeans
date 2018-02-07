/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import auction.webservice.User;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yannick
 */
public class Registration {
    
    public Registration() {
    }
    
     @Before
    public void setUp() throws Exception {
        clean();
    }

    @Test
    public void registerUser() {
        User user1 = registerUser("xxx1@yyy");
        assertTrue(user1.getEmail().equals("xxx1@yyy"));
        User user2 = registerUser("xxx2@yyy2");
        assertTrue(user2.getEmail().equals("xxx2@yyy2"));
        User user2bis = registerUser("xxx2@yyy2");
        assertEquals(user2bis.getEmail(), user2.getEmail());
        assertEquals(user2bis.getId(), user2.getId());
        //geen @ in het adres
        assertNull(registerUser("abc"));
    }

    @Test
    public void getUserTest() {
        User user1 = registerUser("xxx5@yyy5");
        User userGet = getUser("xxx5@yyy5");
        assertEquals(userGet.getEmail(), user1.getEmail());
        assertEquals(userGet.getId(), user1.getId());
        assertNull(getUser("aaa4@bb5"));
        registerUser("abc");
        assertNull(getUser("abc"));
    }

    private static User registerUser(java.lang.String arg0) {
        auction.webservice.RegistrationService service = new auction.webservice.RegistrationService();
        auction.webservice.Registration port = service.getRegistrationPort();
        return port.registerUser(arg0);
    }

    private static User getUser(java.lang.String arg0) {
        auction.webservice.RegistrationService service = new auction.webservice.RegistrationService();
        auction.webservice.Registration port = service.getRegistrationPort();
        return port.getUser(arg0);
    }

    private static void clean() {
        auction.webservice.RegistrationService service = new auction.webservice.RegistrationService();
        auction.webservice.Registration port = service.getRegistrationPort();
        port.clean();
    }

    private static java.util.List<auction.webservice.User> getUsers() {
        auction.webservice.RegistrationService service = new auction.webservice.RegistrationService();
        auction.webservice.Registration port = service.getRegistrationPort();
        return port.getUsers();
    }
    
    
}
