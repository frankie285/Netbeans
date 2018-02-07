/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordencryption;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
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
import javax.swing.JPasswordField;

/**
 *
 * @author Frank
 */
public class PasswordEncryption extends Application {
        static PBEKeySpec pbeKeySpec;
        static PBEParameterSpec pbeParamSpec;
        static SecretKeyFactory keyFac;
        
        static byte[] salt = {
                (byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c,
                (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99
            };
        static int count = 20;
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        TextField tfd = new TextField();
        PasswordField pfd = new PasswordField();
        btn.setText("Encrypt this text");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try{
                    doLogic(tfd.getText(), pfd.getText().toCharArray());
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        });
        
        FlowPane root = new FlowPane();
        root.getChildren().addAll(btn, tfd, pfd);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Encryptor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private static void doLogic(String text, char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{
            
            pbeParamSpec = new PBEParameterSpec(salt, count);
            pbeKeySpec = new PBEKeySpec(password);
            keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
            Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
            pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);
            byte[] cipherText = pbeCipher.doFinal(text.getBytes());
            
            
            List<byte[]> saltAndCipherText = new ArrayList();
            saltAndCipherText.add(salt);
            saltAndCipherText.add(cipherText);
            
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("encrypted.txt"));
            oos.writeObject(saltAndCipherText);
            oos.close();
    }
}
