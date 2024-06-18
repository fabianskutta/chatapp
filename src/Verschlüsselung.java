package src;

/**
 * @author 
 * @version 
 */

public interface Verschlüsselung
{
    // Dienste
    public String verschlüsseln(String kt);
    
    public String entschlüsseln(String vt);
    
    public void speichereSchlüssel(String schlüssel);
    
    public String ladeSchlüssel();
}
