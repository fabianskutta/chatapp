package src;

/**
 * Beschreiben Sie hier die Klasse kodierung.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class CeasarKryptomodul implements Verschlüsselung
{
    
    private Queue<String> Sub;
    private int key;
    private String gespeicherterSchlüssel;

    /**
     * Konstruktor für Objekte der Klasse kodierung
     */
    public CeasarKryptomodul()
    {
        this.Sub = new Queue<String>();
        this.gespeicherterSchlüssel = "";
    }
    
    public Queue<String> befuellen() {
        char a = 65; // ASCII-Wert für 'A'
        for (int i = 0; i < 26; i++) {
            String c = String.valueOf(a);
            Sub.enqueue(c);
            a++;
        }
        return Sub;
    }
    
    public String verschlüsseln(String klartext){
        key = Integer.valueOf(ladeSchlüssel());
        String erg="";
        klartext.toUpperCase();
        for(int i = 0; i<klartext.length(); i++){
            String aktuell = String.valueOf(klartext.charAt(i));
            //Anfangsbuchstabe gegeben
            while(!Sub.front().equals(aktuell)){
                Sub.enqueue(Sub.front());
                Sub.dequeue();
            }
            //Schluesselverschiebung hinzufügen
            for(int a=0; a<key; a++){
                Sub.enqueue(Sub.front());
                Sub.dequeue();
            }
            erg = erg+Sub.front();
        }
        return erg;
    }
      

    public String entschlüsseln(String geheimtext) {
        key = Integer.valueOf(ladeSchlüssel());
        String erg = "";
        geheimtext = geheimtext.toUpperCase();
        for (int i = 0; i < geheimtext.length(); i++) {
            String aktuell = String.valueOf(geheimtext.charAt(i)); // Buchstabe an Stelle i im String
            // Buchstabe i in der Queue finden
            while (!Sub.front().equals(aktuell)) {
                Sub.enqueue(Sub.front());
                Sub.dequeue();
            }
            // Schluesselverschiebung rückgängig machen
            for (int a = 0; a < 26 - key; a++) {
                Sub.enqueue(Sub.front());
                Sub.dequeue();
            }
            erg = erg + Sub.front();
        }
        return erg;
    }

    public void speichereSchlüssel(String schlüssel) {
        this.gespeicherterSchlüssel = schlüssel;
    }

    public String ladeSchlüssel() {
        return this.gespeicherterSchlüssel;
    }
}
