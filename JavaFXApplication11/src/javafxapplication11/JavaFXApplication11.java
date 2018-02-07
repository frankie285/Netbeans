/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication11;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 *
 * @author Frank
 */
public class JavaFXApplication11 extends Application {
        static PBEKeySpec pbeKeySpec;
        static PBEParameterSpec pbeParamSpec;
        static SecretKeyFactory keyFac;
        static TextField tf;
        static char[] characters;

    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        PasswordField pfd = new PasswordField();
        tf = new TextField();
        btn.setText("Decrypt text");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try{
                    doLogic(pfd.getText().toCharArray());
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        });
        
        FlowPane root = new FlowPane();
        root.getChildren().addAll(btn, pfd, tf);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Decryptor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private static void doLogic(char[] password) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("encrypted.txt"));
        List<byte[]> saltAndText = (List<byte[]>) ois.readObject();
        byte[] salt = saltAndText.get(0);
        byte[] text = saltAndText.get(1);
        
        
        pbeParamSpec = new PBEParameterSpec(salt, 20);
            pbeKeySpec = new PBEKeySpec(password);
            keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
            Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
            pbeCipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);
            byte[] cipherText = pbeCipher.doFinal(text);
            String t = new String(cipherText, "UTF-8");
            tf.setText(t);
    }
}
