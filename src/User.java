
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
    private int Userid;

    /**
     * Konstruktor der Klasse Eintrag
     * @param punkte : int
     * @param name : String
     */
    public User(int Userid, String benutzer, String passwort)
    {
        this.Userid = Userid;
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
    public int gibuserid()
    {
        return this.Userid;
    }
    
    /**
     * Diese Methode gibt die id des Eintrages zurück
     */
    public String gibpasswort()
    {
        return this.passwort;
    }
        
        
}
