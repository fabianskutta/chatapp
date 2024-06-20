package src;

import java.util.List;

/**
 * Klasse für einen SpielServer. Der Spielserver bietet die Möglichkeit ein Spiel gegen den Server zu spielen.
 * Bei dem Spiel muss man eine zufällige Zahl zwischen 
 * @autor Henning Ainödhofer
 * @version 21.03.2017
 */
public class ChatServer extends Server {

    private MsgGateway msgGateway;
    private UsrGateway usrGateway;

    public ChatServer(int port) {
        super(port);
        msgGateway = new MsgGateway();
        usrGateway = new UsrGateway();
    }

    /**
     * Diese Methode der Server-Klasse wird hiermit überschrieben.
     * Der angemeldete Client bekommt die Meldung, dass er angenommen wurde.
     */
    @Override
    public void processNewConnection(String clientIP, int clientPort) {
        this.send(clientIP, clientPort, "+OK Verbindung hergestellt");
    }

    /**
     * Diese Methode der Server-Klasse wird hiermit überschrieben.
     * Der angemeldete Client bekommt die gesendete Meldung zurückgeschickt.
     */
    @Override
    public void processMessage(String clientIP, int clientPort, String message) {
        switch (gibBereich(message, 1)) {
            case "USR":
                boolean userExists = anmelden(gibBereich(message, 2), gibBereich(message, 3));
                if (userExists) {
                    send(clientIP, clientPort, "+USR Willkommen");
                } else {
                    send(clientIP, clientPort, "-ERR Ungültiger Benutzername oder Passwort");
                }
                break;
            case "REG":
                boolean registered = registieren(gibBereich(message, 2), gibBereich(message, 3));
                if (registered) {
                    send(clientIP, clientPort, "+OK Registrierung erfolgreich");
                } else {
                    send(clientIP, clientPort, "-ERR Registrierung fehlgeschlagen");
                }
                break;
            case "SND":
                boolean sent = sendeNachricht(gibBereich(message, 2));
                if (sent) {
                    send(clientIP, clientPort, "+OK Nachricht gesendet");
                } else {
                    send(clientIP, clientPort, "-ERR Senden fehlgeschlagen");
                }
                break;
            case "abmelden":
                send(clientIP, clientPort, "+OK Verbindung getrennt");
                processClosingConnection(clientIP, clientPort);
                break;
            default:
                send(clientIP, clientPort, "-ERR Unbekannter Befehl");
        }
    }

    /**
     * Diese Methode der Server-Klasse wird hiermit überschrieben.
     * Hier wird die Verbindung des Clients geschlossen.
     */
    @Override
    public void processClosingConnection(String clientIP, int clientPort) {
        // Cleanup code for when a client disconnects
        System.out.println("Client " + clientIP + ":" + clientPort + " hat die Verbindung geschlossen.");
        // Additional cleanup or notification logic can be added here if necessary
    }

    /**
     * @autor Fabian
     */
    private boolean istGueltigerName(String name) {
        return name.matches("[a-zA-Z]{1,20}");
    }

    /**
     * Methode zur Anmeldung eines Benutzers
     * @param name Benutzername
     * @param passwort Passwort
     * @return true, wenn Anmeldung erfolgreich, sonst false
     */
    private boolean anmelden(String name, String passwort) {
        User u = usrGateway.getUser(name);
        if (u != null && u.gibpasswort().equals(passwort)) { // Zugriff auf das Passwort-Attribut
            return true;
        }
        return false;
    }

    /**
     * Methode zur Registrierung eines neuen Benutzers
     * @param name Benutzername
     * @param passwort Passwort
     * @return true, wenn Registrierung erfolgreich, sonst false
     */
    private boolean registieren(String name, String passwort) {
        if (istGueltigerName(name) && usrGateway.getUser(name) == null) {
            usrGateway.(passwort, name);
            return true;
        }
        return false;
    }

    /**
     * Methode zum Senden einer Nachricht
     * @param clientIP IP-Adresse des Clients
     * @param clientPort Port des Clients
     * @param nachricht Nachricht
     * @return true, wenn Senden erfolgreich, sonst false
     */
    private boolean sendeNachricht(String nachricht, int userID, String name) {
        try {
            msgGateway.postMessage(nachricht, userID, name);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Diese Methode teilt die Nachricht auf Bereiche auf. 
     */
    private String gibBereich(String message, int bereich) {
        String[] parts = message.split(" ");
        if (bereich >= 0 && bereich < parts.length) {
            return parts[bereich];
        }
        return "";
    }


    /**
     * Diese Methode generiert einen String aus den Nachrichten der übergebenen Liste.
     * Dabei beachtet man das folgende Format:
     * @param liste Liste mit Objekten vom Typ Eintrag
     * @return String mit Einträgen durch Leerzeichen getrennt
     */
    private String generiereStringAusList(List<Message> liste) {
        StringBuilder sb = new StringBuilder();
        for (Message message : liste) {
            sb.append(message.toString()).append(" ");
        }
        return sb.toString().trim();
    }
}
