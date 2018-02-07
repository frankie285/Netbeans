/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryptor;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Frank
 */
public class Encryptor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, Exception {
        //leest de private key file uit
        ObjectInputStream keyFIS = new ObjectInputStream(new FileInputStream("privateKey.txt"));
        PrivateKey privateKey = (PrivateKey) keyFIS.readObject();
        
        //leest de tekst file uit
        FileInputStream inputFIS = new FileInputStream("input.txt");
        byte[] inputData = new byte[inputFIS.available()];
        inputFIS.read(inputData);
        inputFIS.close();
        
        //de naam van de ondertekenaar
        String ondertekenaar = "Frankie van Meel";
        
        
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateKey);
        byte[] signed = signature.sign();
        
        byte[] signatureLength = ByteBuffer.allocate(4).putInt(signed.length).array();
        
        List<byte[]> list = new ArrayList<byte[]>();
        list.add(signatureLength);
        list.add(signed);
        list.add(inputData);
        
        ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream("inputSignedBy" + ondertekenaar + ".txt"));
        OOS.writeObject(list);
        OOS.close();
    }
}
