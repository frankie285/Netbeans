/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arenatojava;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Frank
 */
public class ArenaToJava extends Application {
    private BufferedImage capture = null;
    Rectangle screenRect = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDefaultConfiguration().getBounds();
    SQL sql = new SQL();
    
    Color LT;
    Color LB;
    Color RT;
    Color RB;
    
    @Override
    public void start(Stage primaryStage) {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(640);
        imageView.setFitHeight(360);
        
        
        Button btn = new Button();
        btn.setText("Start importing Resolume");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                    for(;;){
                        try {
                        capture = new Robot().createScreenCapture(screenRect);
                        LT = averageColor(capture, 0,0, (screenRect.width / 2 - 10), (screenRect.height / 2 - 10));
                        LB = averageColor(capture, 0, (screenRect.height / 2), (screenRect.width / 2), (screenRect.height / 2));
                        RT = averageColor(capture, (screenRect.width / 2), 0, (screenRect.width / 2), (screenRect.height / 2));
                        RB = averageColor(capture, (screenRect.width / 2), (screenRect.height / 2), (screenRect.width / 2), (screenRect.height / 2));
                      
                        Image x = SwingFXUtils.toFXImage(capture, null);
                        imageView.setImage(x);
                        
                        sql.updateColors(LT, 23);
                        sql.updateColors(LB, 25);
                        sql.updateColors(RT, 24);
                        sql.updateColors(RB, 26);
                        
                        
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                 primaryStage.setTitle(LT.toString() + " : " + LB.toString() + " : " + RT.toString() + " : " + RB.toString());
                            }
                            
                        });
                       
                        } catch (Exception e) {
                        System.out.println(e);
                        }
                    }    
                    }
                }).start();
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.getChildren().add(imageView);
        
        Scene scene = new Scene(root, 640, 360);
        
        primaryStage.setTitle("Glow Sticks Control");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws AWTException, IOException {
        launch(args);
        }
    
    public static Color averageColor(BufferedImage bi, int x0, int y0, int w, int h) {
    int x1 = x0 + w;
    int y1 = y0 + h;
    long sumr = 0, sumg = 0, sumb = 0;
    int num = 0;
    for (int x = x0; x < x1; x++) {
        for (int y = y0; y < y1; y++) {
            Color pixel = new Color(bi.getRGB(x, y));
            sumr += pixel.getRed();
            sumg += pixel.getGreen();
            sumb += pixel.getBlue();
            num++;
        }
    }
    
    int red = (int) (sumr / num);
    int green = (int) (sumg / num);
    int blue = (int) (sumb / num);
    return new Color(red, green, blue);
}
}
