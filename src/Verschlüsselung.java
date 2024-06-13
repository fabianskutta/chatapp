package src;

/**
 * @author 
 * @version 
 */

public interface Verschlüsselung
{
    // Dienste
    public String verschlüsseln(String kt, int schlüssel);
    
    public String entschlüsseln(String vt, int schlüssel);
    
    public void speichereSchlüssel(String schlüssel);
    
    public String ladeSchlüssel();
}
