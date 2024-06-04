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
     * Diese Methode setzt die READ-Funktion um, indem man sich alle Objekte der Tabelle liefern lassen kann.
     * 
     * @return Liste aller Einträge
     */
    public User<user> holeAlle()
    {
        verbinde();
        List <User> highscore = new List();
        db.executeStatement("Select id, name, punkte from highscore ORDER BY punkte ASC");
        QueryResult ergebnis = db.getCurrentQueryResult();
        if(ergebnis != null)
        {
            for(int i = 0; i < ergebnis.getRowCount(); i++)
            {
                highscore.append(new User(ergebnis.getData()[i][0], ergebnis.getData()[i][1], Integer.parseInt(ergebnis.getData()[i][2])));
            }
        }
        beende();
        return highscore;
    }
    
    
    /**
     * Diese Methode setzt die READ-Funktion um, indem man sich alle Objekte der Tabelle liefern lassen kann, die den selben Namen besitzen.
     * 
     * @param text
     * 
     * @return Liste aller Einträge
     */
    public List<User> sucheNachbenutzer(String benutzer)
    {
        verbinde();
        List <User> user = new List();
        db.executeStatement("Select id, name, punkte from highscore WHERE name = '"+name+"' ORDER BY punkte ASC");
        QueryResult ergebnis = db.getCurrentQueryResult();
        if(ergebnis != null)
        {
            for(int i = 0; i < ergebnis.getRowCount(); i++)
            {
                user.append(new user(ergebnis.getData()[i][0], ergebnis.getData()[i][1], Integer.parseInt(ergebnis.getData()[i][2])));
            }
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
    public void hinzufuegen(int id, String passwort, String benutzer)
    {
        verbinde();
        db.executeStatement("INSERT INTO highscore (name, punkte) VALUES ('"+name+"', "+punkte+")");
        beende();
    }
    
    
    /**
     * Diese Methode erzeugt die Tabelle highscore, wenn diese nicht schon exisitiert.
     */
    public void erzeugeTabelle()
    {
         verbinde();
         db.executeStatement("Create table if not exists highscore (id INTEGER PRIMARY KEY AUTOINCREMENT, name text, punkte int)");
         beende();
    }
    
    /**
     * Diese Methode stellt eine Verbindung zur Datenbank her.
     */
    private void verbinde()
    {
        if(db == null)
        {
            db = new DatabaseConnector("",0,"spielstand","","");
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
