/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.ambilight;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 *
 * @author Frank
 */
public class AndroidAmbilight extends Application {
    
    @Override
    public void start(Stage primaryStage) {
            
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        
                    JFrame frame = new JFrame();
                    frame.getContentPane().setLayout(new FlowLayout());
                   
                    frame.pack();
                    frame.setVisible(true);
        
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                SQL sql = new SQL();
                byte[] array = sql.getPicture();
                
                InputStream in = new ByteArrayInputStream(array);
                try {
                    BufferedImage imageCon = ImageIO.read(in);
                    
                 
                     frame.getContentPane().add(new JLabel(new ImageIcon(imageCon)));
                    
                  
                } catch (IOException ex) {
                    Logger.getLogger(AndroidAmbilight.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
