package src;

/**
 * Diese Klasse setzt das Entwurfsmuster DataTableGateway um. Dabei stellt es alle Datenbankrelevanten Funktionen, die die Anwendung benötigt 
 * zur Verfügung. Erweiterungen und Einschränkungen sind möglich.
 * 
 * @author Henning Ainödhofer
 * @version 10.04.2018
 */
public class MsgGateway
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private DatabaseConnector db;

    /**
     * Konstruktor für Objekte der Klasse HighscoreGateway
     */
    public MsgGateway()
    {
        // Instanzvariable initialisieren
        db = null;
    }
    
    /**
     * Diese Methode setzt die READ-Funktion um, indem man sich alle Objekte der Tabelle liefern lassen kann.
     * 
     * @return Liste aller Einträge
     */
    public List<Nachricht> holeAlle()
    {
        verbinde();
        List <Nachricht> nachrichten = new List();
        db.executeStatement("Select * from Nachrichten");
        QueryResult ergebnis = db.getCurrentQueryResult();
        if(ergebnis != null)
        {
            for(int i = 0; i < ergebnis.getRowCount(); i++)
            {
                nachrichten.append(new Nachricht(ergebnis.getData()[i][0], ergebnis.getData()[i][1], ergebnis.getData()[i][2]));
            }
        }
        if(db.getErrorMessage() != null)
         {
             System.out.println(db.getErrorMessage());
         }
        beende();
        return nachrichten;
    }
    
    /**
     * Diese Methode stellt die Nachrichten mit Dtum und ID in die Datenbank ein. 
     * 
     * @param name
     * @param punkte
     */
    public void postMessage(String nachricht,int userID, String name)
    {
        verbinde();
        db.executeStatement("INSERT INTO Nachrichten (nachrichten, name, userID) VALUES ('"+nachricht+"', '"+name+"', "+userID+")");
        if(db.getErrorMessage() != null)
         {
             System.out.println(db.getErrorMessage());
         }
        beende();
    }
    
    /**
     * Diese Methode erzeugt die Tabelle Nachrichten, wenn diese nicht schon exisitiert.
     */
    public void erzeugeTabelleNachrichten()
    {
         verbinde();
         db.executeStatement("Create table if not exists Nachrichten (nID INTEGER PRIMARY KEY AUTOINCREMENT, nachrichten TEXT, name TEXT, userID INTEGER, FOREIGN KEY(userID) REFERENCES User(userID) )");
         if(db.getErrorMessage() != null)
         {
             System.out.println(db.getErrorMessage());
         }
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
