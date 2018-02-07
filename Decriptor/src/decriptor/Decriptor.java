/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decriptor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.List;

/**
 *
 * @author Frank
 */
public class Decriptor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        ObjectInputStream keyFIS = new ObjectInputStream(new FileInputStream("publicKey.txt"));
        PublicKey publicKey = (PublicKey) keyFIS.readObject();

        ObjectInputStream encryptedOIS = new ObjectInputStream(new FileInputStream("inputSignedByFrankie van Meel.txt"));
        List<byte[]> separateData = (List<byte[]>) encryptedOIS.readObject();
        
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initVerify(publicKey);
        
        boolean verifies = signature.verify(separateData.get(1));
        
        if(verifies){
            OutputStream OS = new FileOutputStream("reconstruated.txt");
            OS.write(separateData.get(2));
        }
    }
    
}
