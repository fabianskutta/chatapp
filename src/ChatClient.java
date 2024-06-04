package src;

import javax.swing.*;
/**
 * Klasse fuer einen SpielClient
 * @author Henning Ainödhofer
 * @version 21.3.2017
 */

public class ChatClient extends Client { 
    public ChatClient(String ip, int p) {
        super(ip, p);
    }

    /**
     * @ author 
     * Hier wird anhand des Protokolls die richtige Komunikation sichergestellt.
     * Diese Methode der Server-Klasse wird hiermit ueberschrieben.
     * Der Client gibt die erhaltende Meldung, auf dem Textfeld aus.
     */

    public void processMessage(String message){
        
    }

    /**
     * Diese Methode gibt den Befehl zurück die die message beinhaltet
     * 
     * @param message
     * 
     * @return Befehl
     */
    private String gibBefehlsbereich(String message)
    {
        return message.split(" ")[0];
    }

    /**
     * Diese Methode gibt den Text zurück die die message beinhaltet
     * 
     * @param message
     * 
     * @return Text
     */
    private String gibTextbereich(String message)
    {
        String [] messageArray = message.split(" ");
        String text = "";
        for(int i = 1; i < messageArray.length; i++)
        {
            text = text+" "+ messageArray[i];
        }
        return text;
    }

    /**
     * @ author^^
     * Diese Methode druckt die Nachrichten auf der Konsole aus.
     * @param message
     */
    private void NachrichtenDrucken(String message)
    {
        
    }
}
