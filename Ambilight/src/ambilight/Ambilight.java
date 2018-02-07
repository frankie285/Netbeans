/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ambilight;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Frank
 */
public class Ambilight extends Application {
    
    /**
     * public static fields
     */
    public static User loggedInUser;
    
    
    /**
     * starts the scene
     * @param stage stage on which the scene is printed
     * @throws Exception when something goes wrong
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    /**
     * handles the login, checks if the login data matches the data in the database
     * @param user gets a user object
     * @return returns a ColorData object
     * @throws SQLException
     * @throws IOException 
     */
    public static ColorData login(User user) throws SQLException, IOException{
        List<User> users = database.login();
        ColorData colorData = new ColorData();
        
        for(User userObject: users){
            {
                if(userObject.username.equals(user.username) && userObject.password.equals(user.password)){
                    System.out.println("Succesvol ingelogd!!!");
                    loggedInUser = user;
                    colorData = ColorSettings(userObject);
                }
            }
        }
        return colorData;
    }
    
    /**
     * gets a user object and returns the matching colors in a matching ColorData object
     * @param user gets a User object
     * @return returns a ColorData object
     * @throws SQLException 
     */
    public static ColorData ColorSettings(User user) throws SQLException{
        ColorData colorData = new ColorData();
        List<ColorData> colorDatas = database.getColorData();
        
        for(ColorData object: colorDatas){
            if(object.id == user.id){
                System.out.println("er bestaat een preset");
                colorData = object;
            }
        }   
        return colorData;
    }
}