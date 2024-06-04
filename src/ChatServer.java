import java.net.*;

/**
 * Klasse fuer einen SpielServer. Der Spielserver bietet die Möglickeit ein Spiel gegen den Server zu spielen. Bei dem Spiel muss man eine zufällige Zahl
 * zwischen 
 * @author Henning Ainödhofer
 * @version 21.03.2017
 */

public class ChatServer extends Server {

    private MsgGateway Msg;
    private UsrGateway Usr;
    private List<Spiel> UsrOnline; 
    
    public ChatServer(int p) {
        super(p);
        Msg = new MsgGateway();
        UsrOnline = new List<>();
    }

    /**
     * Diese Methode der Server-Klasse wird hiermit ueberschrieben.
     * Der angemeldete Client bekommt die Meldung, dass er angenommen wurde.
     */

    public void processNewConnection(String pClientIP, int pClientPort){
        this.send(pClientIP, pClientPort, "+OK Verbindung hergestellt");
    }

    /**
     * Diese Methode der Server-Klasse wird hiermit ueberschrieben.
     * Der angemeldete Client bekommt die gesendete Meldung zurueckgeschickt.
     */
    public void processMessage(String pClientIP, int pClientPort, String pMessage){ 
        switch(gibBereich(pMessage))
        {

        }
    }
    
    /**
     * Methode, die den User mit der übergebenen Client-IP, löscht
     * @param pClientIP
     */
    private synchronized void loescheOnlineUsr(String pClientIP, int pClientPort)
    {
    }

    /**
     * Methode, die den User mit der übergebenen Client-IP, setzt
     * @param pClientIP
     */
    private synchronized void setOnlineUsr(String pClientIP, int pClientPort)
    {
    }
    
    /**
     * Diese Methode generiert einen String aus den Nachrichten der übergebenen Liste.
     * Dabei beachtet man das folgende Format:
     * 
     * @param Liste mit Objekten vom Typ Eintrag
     * @return String mit Einrtägen durch Leerzeichen getrennt
     */
    private String generiereStringAusList(List<Message> l)
    {
    }
}
