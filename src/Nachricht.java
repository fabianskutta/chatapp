package src;

/**
 * Die Klasse Eintrag dient dem Speichern der Nachrichten mit Namen
 * @author Henning Ainödhofer
 * @version 21.03.2018
 */
public class Nachricht
{
    private String Nachricht;
    private String name;
    private String id;

    /**
     * Konstruktor der Klasse Eintrag
     * @param punkte : int
     * @param name : String
     */
    public Nachricht(String id, String name, int punkte)
    {
        this.id = id;
        this.Nachricht = Nachricht;
        this.name = name;
    }

    /**
     * Diese Methode gibt die Punkte des Eintrages zurück
     * 
     * @return punkte : int
     */
    public String gibNachricht()
    {
        return this.Nachricht;
    }
    
    /**
     * Diese Methode gibt den Namen des Eintrages zurück
     * 
     * @return name : String
     */
    public String gibName()
    {
        return this.name;
    }
    
    /**
     * Diese Methode gibt die id des Eintrages zurück
     */
    public String gibId()
    {
        return this.id;
    }
        
        
}
