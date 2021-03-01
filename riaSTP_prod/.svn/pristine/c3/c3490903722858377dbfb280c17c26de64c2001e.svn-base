 package com.fbn.ria.util;

 import java.security.spec.KeySpec;
 import javax.crypto.Cipher;
 import javax.crypto.SecretKey;
 import javax.crypto.SecretKeyFactory;
 import javax.crypto.spec.DESKeySpec;
 import org.apache.commons.codec.binary.Base64;

 public class EncryptionCode  {
     private static final String UNICODE_FORMAT = "UTF8";
        public static final String DES_ENCRYPTION_SCHEME = "DES";
        private KeySpec myKeySpec;
        private SecretKeyFactory mySecretKeyFactory;
        private Cipher cipher;
        byte[] keyAsBytes;
        private String myEncryptionKey;
        private String myEncryptionScheme;
        SecretKey key;
    
     public EncryptionCode()
             throws Exception  {
         this.myEncryptionKey = "riaPassword";
         this.myEncryptionScheme = "DES";
         this.keyAsBytes = this.myEncryptionKey.getBytes("UTF8");
         this.myKeySpec = new DESKeySpec(this.keyAsBytes);
         this.mySecretKeyFactory = SecretKeyFactory.getInstance(this.myEncryptionScheme);
         this.cipher = Cipher.getInstance(this.myEncryptionScheme);
         this.key = this.mySecretKeyFactory.generateSecret(this.myKeySpec);
            }
    
     public String encryptPassword(String plainPwd) {
         String encryptedString = "";
         try  {
             this.cipher.init(1, this.key);
             byte[] plainText = plainPwd.getBytes("UTF8");
             byte[] encryptedText = this.cipher.doFinal(plainText);
                       
             encryptedString = Base64.encodeBase64String(encryptedText);
                    }  catch (Exception e) {
             e.printStackTrace();
                    }
         return encryptedString;
            }
     
     public String encryptPassword(String plainPwd,String encKey) {
         String encryptedString = "";
         try  {
              this.myEncryptionKey = encKey;
         this.myEncryptionScheme = "DES";
         this.keyAsBytes = this.myEncryptionKey.getBytes("UTF8");
         this.myKeySpec = new DESKeySpec(this.keyAsBytes);
         this.mySecretKeyFactory = SecretKeyFactory.getInstance(this.myEncryptionScheme);
         this.cipher = Cipher.getInstance(this.myEncryptionScheme);
         this.key = this.mySecretKeyFactory.generateSecret(this.myKeySpec);
             this.cipher.init(1, this.key);
             byte[] plainText = plainPwd.getBytes("UTF8");
             byte[] encryptedText = this.cipher.doFinal(plainText);
             org.apache.commons.codec.binary.Base64 base64encoder = new Base64();
           
             encryptedString = base64encoder.encodeToString(encryptedText);
                     
                     //encodeBase64String(encryptedText);
                    }  catch (Exception e) {
             e.printStackTrace();
                    }
         return encryptedString;
            }
           
    
     public static void main(String[] args)
             throws Exception  {
         System.out.println("Enter the password after the prompt");
          String val = "dedupadm";
        //if(args.length == 1){
        //    System.out.println("Args length greated than 1");
        //    val = "custom";
       // }
        
         System.out.println("Value to encrypt --" + val);
         EncryptionCode enCode = new EncryptionCode();
         String encval = enCode.encryptPassword(val);
         System.out.println("Encrypted value ---  " + encval);
            }
     }

