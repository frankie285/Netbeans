/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ambilight;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Frank
 */
public class FXMLDocumentController implements Initializable {
    
    /**
     * private fields
     */
    @FXML 
    private TextField username_text;
    
    @FXML
    private PasswordField password_text;
    
    @FXML
    private Label label1, label2,label3, label4, label5, label6;
    
    @FXML
    private ColorPicker color1, color2, color3, color4, color5, color6;
    
    
    
    /**
     * handles the login button click, creates a new user object, checks if it matches the database and returns the correct colors, inputs them into the labels
     * @param event takes an ActionEvent 
     */
    @FXML
    private void handleLogIn(ActionEvent event) throws SQLException, IOException {
        if(username_text.getText() != null && password_text.getText() != null){
             User user = new User(username_text.getText(), password_text.getText());
            ColorData colorData = Ambilight.login(user);
            setLabels(colorData);
        }
    }
    
    /**
     * handles the register button click, creates a user object and inserts it into the database
     * @param event takes an ActionEvent argument
     */
    @FXML
    private void handleRegister(ActionEvent event){
            if(username_text.getText() != null && password_text.getText() != null){
                User user = new User(username_text.getText(), password_text.getText());
                try{
                    database.register(user.username, user.password);
                    System.out.println("data inserted");
                    }
                catch(Exception e){
            e.fillInStackTrace();
            }                      
        }
    }
    
    /**
     * creates a color object from the colorpicker's data, inserts it into the database and writes the values to the labels, also serializes and deserializes the object and displays the values in the console.
     */
    @FXML
    private void handleColorLogout(){
        ColorData colorData = new ColorData(database.userID, color1.getValue().toString(), color2.getValue().toString(), color3.getValue().toString(), color4.getValue().toString(), color5.getValue().toString(), color6.getValue().toString());
        database.setColors(colorData);
        setLabels(colorData);
        
        try {
         FileOutputStream fileOut =
         new FileOutputStream("C:/temp/employee.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(colorData);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /tmp/employee.ser");
      }catch(IOException i) {
         i.printStackTrace();
      }
        
        ColorData c = null;
      try {
         FileInputStream fileIn = new FileInputStream("C:/temp/employee.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         c = (ColorData) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i) {
         i.printStackTrace();
         return;
         
      }catch(ClassNotFoundException d) {
         System.out.println("ColorData class not found");
         d.printStackTrace();
         return;
      }
      
      System.out.println("Deserialized object");
      System.out.println(c.id + "," + c.color1 + "," + c.color2 + "," + c.color3 + "," + c.color4 + "," + c.color5 + "," + c.color6);
    }
    
    /**
     * takes a ColorData object and sets the laels to the correct color values
     * @param colors takes a ColorData object
     */
    public void setLabels(ColorData colorData){
        label1.setText(colorData.color1);
        label2.setText(colorData.color2);
        label3.setText(colorData.color3);
        label4.setText(colorData.color4);
        label5.setText(colorData.color5);
        label6.setText(colorData.color6);
    }
    
    /**
     * 
     * things to do when the scene is created
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
