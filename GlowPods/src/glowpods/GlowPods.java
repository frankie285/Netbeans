package glowpods;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import arduino.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.WindowEvent;
/**
 *
 * @author Frank
 */
public class GlowPods extends Application {
    private static int Red;
    private static int Green;
    private static int Blue;
    private Button btn;
    private TextField tf;
    private Label lb;
    private Label lb2;
    
    @Override
    public void start(Stage primaryStage) {
        btn = new Button();
        tf = new TextField();
        lb = new Label();
        lb2 = new Label();
        btn.setText("Zet aan.");
        tf.setText("COM5");
        lb.setText(System.getProperty("os.name"));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                startScreenData();
            }});
        
        FlowPane root = new FlowPane();
        root.getChildren().addAll(btn, tf, lb, lb2);
        
        primaryStage.getIcons().add(new Image("/resources/launch.png"));
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Glow Case");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }});
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        launch(args);
    }
    
    private void startScreenData(){
        try{
                 Robot robot = new Robot();
                 
                 Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        
                        BufferedImage image;
                        Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                        for(;;){
                                try {   
                                        int a = (int) System.currentTimeMillis();
                                        image = robot.createScreenCapture(rectangle);
                                        int b = (int) System.currentTimeMillis();
                                        getAverageRGB(image);
                                        Platform.runLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                int z = (1000 / (b-a));
                                                lb2.setText(String.valueOf(z));
                                            }
                                        });
                                        
                                    } 
                                catch (Exception ex) {
                                        System.out.println(ex);
                                    } 
                                }
                    }
                    });
                    t.start();
                    
                    Thread t2 = new Thread(new Runnable() {
                     @Override
                     public void run() {
                         Arduino arduino = new Arduino(tf.getText(), 9600);
                         arduino.openConnection(); 
                         
                          for(;;){
                             try{
                                    String str = Red + "," + Green + "," + Blue + "," + Red + "," + Green + "," + Blue + "z";
                                    arduino.serialWrite(str);
                             }
                             catch(Exception e){
                                 System.out.println(e);
                             }
                         }                            
                     }
                 });
                    t2.start();
        }
        catch(Exception e){
        System.out.println(e);
        }
    }
    
    private void getAverageRGB(BufferedImage image){
        int pixelSpacing = 20;
        int width = image.getWidth();
        int height = image.getHeight();
        
        int total = (width / pixelSpacing) * (height / pixelSpacing);
        
        int r = 0;
        int g = 0;
        int b = 0;
        
        for(int x = 0; x < width; x = x + pixelSpacing){
            for(int y = 0; y < height; y = y + pixelSpacing){
                int color = image.getRGB(x, y);
                
                r += (color & 0xff0000) >> 16;
                g += (color & 0xff00) >> 8;
                b += color & 0xff;
            }
        }
        
        Red = r / total;
        Green = g / total;
        Blue = b / total;
    }   
}