package src;

/**
 * Diese Klasse setzt das Entwurfsmuster DataTableGateway um. Dabei stellt es alle Datenbankrelevanten Funktionen, die die Anwendung benötigt 
 * zur Verfügung. Erweiterungen und Einschränkungen sind möglich.
 * 
 * @author Henning Ainödhofer
 * @version 10.04.2018
 */
public class UsrGateway
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private DatabaseConnector db;

    /**
     * Konstruktor für Objekte der Klasse HighscoreGateway
     */
    public UsrGateway()
    {
        // Instanzvariable initialisieren
        db = null;
    }
    
    /**
     * Diese Methode setzt die READ-Funktion um, indem man sich alle Objekte der Tabelle liefern lassen kann, die den selben Namen besitzen.
     * 
     * @param text
     * 
     * @return Liste aller Einträge
     */
    public User getUser(String benutzer)
    {
        verbinde();
        User user = null;
        db.executeStatement("Select userID, passwort, benutzer from User WHERE benutzer = '"+benutzer+"'");
        QueryResult ergebnis = db.getCurrentQueryResult();
        if(ergebnis != null)
        {
            user = new User(Integer.parseInt(ergebnis.getData()[0][0]), ergebnis.getData()[0][1], ergebnis.getData()[0][2]);
        }
        beende();
        return user;
    }
    
    /**
     * Diese Methode setzt die CREATE-Funktion um, indem hier neue Highscores in die Datenbank eingetragen werden.
     * 
     * @param name
     * @param punkte
     */
    public void hinzufuegen(String passwort, String benutzer)
    {
        verbinde();
        db.executeStatement("INSERT INTO User (passwort, benutzer) VALUES ('"+passwort+"', '"+benutzer+"')");
        beende();
    }
    
    
    /**
     * Diese Methode erzeugt die Tabelle highscore, wenn diese nicht schon exisitiert.
     */
    public void erzeugeTabelle()
    {
         verbinde();
         db.executeStatement("Create table if not exists User (userID INTEGER PRIMARY KEY AUTOINCREMENT, benutzer TEXT, passwort TEXT )");
         beende();
    }
    
    /**
     * Diese Methode stellt eine Verbindung zur Datenbank her.
     */
    private void verbinde()
    {
        if(db == null)
        {
            db = new DatabaseConnector("",0,"Chatapp","","");
        }
    }
    
    /**
     * Diese Methode beendet die Verbindung zur Datenbank.
     */
    private void beende()
    {
        if(db != null)
        {
            db.close();
            db = null;
        }
    }
}
