package src;

/**
 * Die Klasse Eintrag dient dem Speichern der Nachrichten mit Namen
 * @author Henning Ainödhofer
 * @version 21.03.2018
 */
public class User
{
    private String passwort;
    private String benutzer;
    private int id;

    /**
     * Konstruktor der Klasse Eintrag
     * @param punkte : int
     * @param name : String
     */
    public User(int id, String benutzer, String passwort)
    {
        this.id = id;
        this.benutzer = benutzer;
        this.passwort = passwort;
    }

    /**
     * Diese Methode gibt die Punkte des Eintrages zurück
     * 
     * @return punkte : int
     */
    public String gibbenutzer()
    {
        return this.benutzer;
    }
    
    /**
     * Diese Methode gibt den Namen des Eintrages zurück
     * 
     * @return name : String
     */
    public int gibid()
    {
        return this.id;
    }
    
    /**
     * Diese Methode gibt die id des Eintrages zurück
     */
    public String gibpasswort()
    {
        return this.passwort;
    }
        
        
}
