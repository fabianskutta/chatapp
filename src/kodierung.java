package src;

/**
 * Beschreiben Sie hier die Klasse kodierung.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class kodierung implements Verschlüsselung
{
    
    private Queue<String> Sub;
    private int key;
    /**
     * Konstruktor für Objekte der Klasse kodierung
     */
    public kodierung()
    {
        this.Sub = new Queue<String>();
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
    
    public String verschluesseln(String klartext, int key){
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
    
    public String entschluesseln(String geheimtext, int key){
        String erg="";
        geheimtext.toUpperCase();
        for(int i=0; i<geheimtext.length(); i++){
            String aktuell = String.valueOf(geheimtext.charAt(i)); //Buchstabe an Stelle i im String
            //Buchstabe i in der Queue finden
            while(!Sub.front().equals(aktuell)){
                Sub.enqueue(Sub.front());
                Sub.dequeue();
            }
            //Schluesselverschiebung hinzufügen
            for(int a=0; a<a-key; a++){
                Sub.enqueue(Sub.front());
                Sub.dequeue();
            }
            erg = erg+Sub.front();
        }
        return erg;
    }

}
