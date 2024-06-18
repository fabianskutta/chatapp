package src;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VigenereKryptomodul implements Verschlüsselung {
    private String schluessel;

    // Methode zur Verschlüsselung
    @Override
    public String verschlüsseln(String kt) {
        String key = ladeSchlüssel();
        return vigenereEncrypt(kt, key);
    }

    // Methode zur Entschlüsselung
    @Override
    public String entschlüsseln(String vt) {
        String key = ladeSchlüssel();
        return vigenereDecrypt(vt, key);
    }

    @Override
    public void speichereSchlüssel(String schlüssel) {
        try {
            Files.write(Paths.get("vigenere_schluessel.txt"), schlüssel.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String ladeSchlüssel() {
        try {
            return new String(Files.readAllBytes(Paths.get("vigenere_schluessel.txt")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String vigenereEncrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    result.append((char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A'));
                } else {
                    result.append((char) ((c + key.charAt(j) - 2 * 'a') % 26 + 'a'));
                }
                j = ++j % key.length();
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private String vigenereDecrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    result.append((char) ((c - key.charAt(j) + 26) % 26 + 'A'));
                } else {
                    result.append((char) ((c - key.charAt(j) + 26) % 26 + 'a'));
                }
                j = ++j % key.length();
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

}

