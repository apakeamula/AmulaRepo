 package com.fbn.ria.util;

 import java.security.spec.KeySpec;
 import javax.crypto.Cipher;
 import javax.crypto.SecretKey;
 import javax.crypto.SecretKeyFactory;
 import javax.crypto.spec.DESKeySpec;
 import org.apache.commons.codec.binary.Base64;

 public class DecryptionCode  {
     private static final String UNICODE_FORMAT = "UTF8";
        public static final String DES_ENCRYPTION_SCHEME = "DES";
        private KeySpec myKeySpec;
        private SecretKeyFactory mySecretKeyFactory;
        private static Cipher cipher;
        byte[] keyAsBytes;
        private String myEncryptionKey;
        private String myEncryptionScheme;
        static SecretKey key;
    
     public DecryptionCode()
             throws Exception  {
         this.myEncryptionKey = "riaPassword";
         this.myEncryptionScheme = "DES";
         this.keyAsBytes = this.myEncryptionKey.getBytes("UTF8");
         this.myKeySpec = new DESKeySpec(this.keyAsBytes);
         this.mySecretKeyFactory = SecretKeyFactory.getInstance(this.myEncryptionScheme);
         cipher = Cipher.getInstance(this.myEncryptionScheme);
         key = this.mySecretKeyFactory.generateSecret(this.myKeySpec);
            }
    
     public String decryptPassword(String encPassword)  {
         String decryptedText = "";
         try  {
             cipher.init(2, key);
            
             Base64 base64decoder = new Base64();
             byte[] encryptedText = base64decoder.decode(encPassword);
            
             //System.out.println("encryptedText -- " + encryptedText.toString());
             byte[] plainText = cipher.doFinal(encryptedText);
             decryptedText = bytes2String(plainText);
                    }  catch (Exception e)  {
             e.printStackTrace();
                    }
         return decryptedText;
            }
     
      public String decryptPassword(String encPassword, String encKey)  {
         String decryptedText = "";
         try  {
             this.myEncryptionKey = encKey;
         this.myEncryptionScheme = "DES";
         this.keyAsBytes = this.myEncryptionKey.getBytes("UTF8");
         this.myKeySpec = new DESKeySpec(this.keyAsBytes);
         this.mySecretKeyFactory = SecretKeyFactory.getInstance(this.myEncryptionScheme);
         cipher = Cipher.getInstance(this.myEncryptionScheme);
         key = this.mySecretKeyFactory.generateSecret(this.myKeySpec);
             cipher.init(2, key);
            
             org.apache.commons.codec.binary.Base64 base64decoder = new Base64();
             byte[] encryptedText = base64decoder.decode(encPassword);           
             System.out.println("encryptedText -- " + encryptedText.toString());
             byte[] plainText = cipher.doFinal(encryptedText);
             decryptedText = bytes2String(plainText);
                    }  catch (Exception e)  {
             e.printStackTrace();
             decryptedText = "Exception occurred";
                    }
         return decryptedText;
            }
    
     private String bytes2String(byte[] bytes)  {
         StringBuffer stringBuffer = new StringBuffer();
         for (int i = 0; i < bytes.length; i++) {
             stringBuffer.append((char) bytes[i]);
                    }
         return stringBuffer.toString();
            }
    
     public static void main(String[] args)
             throws Exception {
         String password = "";
         password = "lH7u+lO75ZQPI0uhyoGh4Q==";
         DecryptionCode decCode = new DecryptionCode();
         System.out.println("Decrypt password -- " + decCode.decryptPassword(password));
            }
     }


