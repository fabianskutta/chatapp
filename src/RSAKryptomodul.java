package src;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import java.util.Base64;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RSAKryptomodul implements Verschlüsselung {
    private PublicKey publicKey;
    private PrivateKey privateKey;

    // Generate RSA key pair and initialize publicKey and privateKey
    public RSAKryptomodul() {
        try {
            KeyPair keyPair = generateKeyPair();
            this.publicKey = keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    private String keyToString(java.security.Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    private PublicKey stringToPublicKey(String key) throws Exception {
        byte[] byteKey = Base64.getDecoder().decode(key);
        X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(X509publicKey);
    }

    private PrivateKey stringToPrivateKey(String key) throws Exception {
        byte[] byteKey = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(byteKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    @Override
    public String verschlüsseln(String kt, int schlüssel) {
        try {
            PublicKey publicKey = stringToPublicKey(ladeSchlüssel());
            Cipher encryptCipher = Cipher.getInstance("RSA");
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] cipherText = encryptCipher.doFinal(kt.getBytes());
            return Base64.getEncoder().encodeToString(cipherText);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String entschlüsseln(String vt, int schlüssel) {
        try {
            PrivateKey privateKey = stringToPrivateKey(ladeSchlüssel());
            byte[] bytes = Base64.getDecoder().decode(vt);
            Cipher decryptCipher = Cipher.getInstance("RSA");
            decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(decryptCipher.doFinal(bytes));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void speichereSchlüssel(String schlüssel) {
        try {
            Files.write(Paths.get("schluessel.txt"), schlüssel.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String ladeSchlüssel() {
        try {
            return new String(Files.readAllBytes(Paths.get("schluessel.txt")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


