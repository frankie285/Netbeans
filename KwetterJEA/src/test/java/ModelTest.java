/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.kwetterjea.Profile;
import com.mycompany.kwetterjea.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import static org.junit.Assert.*;

/**
 *
 * @author Frank
 */
public class ModelTest {
    
    public ModelTest() {
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
    public void changeUsername() {
        User user = new User();
        String username = "frankie";
        user.setUsername(username);
        assertEquals(username, user.getUsername());
    }
    
    public void changeImage() throws IOException{
        InputStream is = getClass().getResourceAsStream("/car0.jpg");
        BufferedImage image = ImageIO.read(is);
        User user = new User();
        Profile profile = new Profile();
        user.setAccount(profile);
        profile.setImage(image);
        assertEquals(user.getAccount().getImage(), image);
    }
    
    public void addBio(){
        
    }
}
