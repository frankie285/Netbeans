/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package key.generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author Frank
 */
public class KeyGenerator {
    private static final int KEYSIZE = 1024;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = new SecureRandom();
            pairgen.initialize(KEYSIZE, random);
            KeyPair keyPair = pairgen.genKeyPair();
            
            try{
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("publicKey.txt"));
                out.writeObject(keyPair.getPublic());
            }
            catch(Exception e){
                System.out.println(e);
            }
            try{
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("privateKey.txt"));
                out.writeObject(keyPair.getPrivate());
            }
            catch(Exception e){
                System.out.println(e);
            }
    }   
}