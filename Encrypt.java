// Name: Jon Brennan
// Date: 2/2/23
// Class: 415 - Networks
// Assignment - AES encryption and decryption

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public class Encrypt{
    
    public enum Encryption{
        AES,
        DES;
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Encryption (AES or DES)");
        Encryption type = Encryption.valueOf(sc.nextLine().toUpperCase());
        System.out.println("Enter your message to encrypt: ");
        String message = sc.nextLine();
        cipher(message, type);
    }


    public static void cipher(String message, Encryption type ) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException{
        

        switch(type){
            case AES: 
                System.out.println("Cipher text before encryption: " + message);
                KeyGenerator AESKEY = KeyGenerator.getInstance("AES");
                AESKEY.init(128);
                SecretKey Key = AESKEY.generateKey();

                Cipher encrypt = Cipher.getInstance("AES");
                encrypt.init(Cipher.ENCRYPT_MODE, Key);
                byte[] cipherText = encrypt.doFinal(message.getBytes());
                String Encrypted = Base64.getEncoder().encodeToString(cipherText);
                System.out.println("Encrypted with AES(128): " + Encrypted);

                Cipher decrypt = Cipher.getInstance("AES");
                decrypt.init(Cipher.DECRYPT_MODE, Key);
                byte[] Decrypted = decrypt.doFinal(Base64.getDecoder().decode(Encrypted));
                String output = new String(Decrypted);
                System.out.println("decrypted with AES(128): " + output);

                break;
            case DES:
                System.out.println("Cipher text before encryption: " + message);
                KeyGenerator DESKEY = KeyGenerator.getInstance("DES");
                DESKEY.init(56);
                SecretKey KeyDES = DESKEY.generateKey();

                Cipher encryptDES = Cipher.getInstance("DES");
                encryptDES.init(Cipher.ENCRYPT_MODE, KeyDES);
                byte[] cipherTextDES = encryptDES.doFinal(message.getBytes());
                String EncryptedDES = Base64.getEncoder().encodeToString(cipherTextDES);
                System.out.println("Encrypted with DES(56): " + EncryptedDES);

                Cipher decryptDES = Cipher.getInstance("DES");
                decryptDES.init(Cipher.DECRYPT_MODE, KeyDES);
                byte[] DecryptedDES = decryptDES.doFinal(Base64.getDecoder().decode(EncryptedDES));
                String outputDES = new String(DecryptedDES);
                System.out.println("decrypted with DES(56): " + outputDES);  

                break;      
            default:
                System.out.println("nice try buster, only supports AES or DES");
                break;

        }
    }
}




