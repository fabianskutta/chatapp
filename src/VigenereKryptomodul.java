package src;

public class VigenereKryptomodul implements Verschlüsselung {
    private String schluessel;

    // Methode zur Verschlüsselung
    @Override
    public String verschlüsseln(String kt, int schlüssel) {
        String key = ladeSchlüssel();
        return vigenereEncrypt(kt, key);
    }

    // Methode zur Entschlüsselung
    @Override
    public String entschlüsseln(String vt, int schlüssel) {
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

    public static void main(String[] args) {
        VigenereKryptomodul vigenere = new VigenereKryptomodul();
        String schluessel = "VIGENERE";

        // Schlüssel speichern
        vigenere.speichereSchlüssel(schluessel);

        // Beispieltext
        String klartext = "Hello, World!";
        System.out.println("Klartext: " + klartext);

        // Verschlüsseln
        String verschluesselt = vigenere.verschlüsseln(klartext, 0);
        System.out.println("Verschlüsselter Text: " + verschluesselt);

        // Entschlüsseln
        String entschluesselt = vigenere.entschlüsseln(verschluesselt, 0);
        System.out.println("Entschlüsselter Text: " + entschluesselt);
    }
}

