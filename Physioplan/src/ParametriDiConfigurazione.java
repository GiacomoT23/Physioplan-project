import java.nio.file.*;
import com.thoughtworks.xstream.*;

public class ParametriDiConfigurazione {
    public int nRigheTabella;
    public int difficoltaMin;
    public int difficoltaMax;
    public int nUltimiGiorniGrafico;
    public String font;
    dimensioneFont dimensioneFont;
    public String coloreBackground;
    public String usernameDBMS;
    public String IPDBMS;
    public String passwordDBMS;
    public String portaDBMS;
    public String IPClient;
    public String IPServerLog;
    public int portaServerLog;
    
    public ParametriDiConfigurazione(){
        XStream xs = new XStream();
        xs.useAttributeFor(dimensioneFont.class, "unita");
        String x = null;
        ValidatoreXML.valida("src/configurazione.xml", "src/configurazione.xsd", true);
        try{
            x = new String(Files.readAllBytes(Paths.get("src/configurazione.xml")));
        }catch(Exception e){}
        
        ParametriDiConfigurazione parametri = (ParametriDiConfigurazione)xs.fromXML(x);
        this.nRigheTabella = parametri.nRigheTabella;
        this.difficoltaMin = parametri.difficoltaMin;
        this.difficoltaMax = parametri.difficoltaMax;
        this.nUltimiGiorniGrafico = parametri.nUltimiGiorniGrafico;
        this.font = parametri.font;
        this.dimensioneFont = new dimensioneFont();
        this.dimensioneFont.valore = parametri.dimensioneFont.valore;
        this.dimensioneFont.unita = parametri.dimensioneFont.unita;
        this.coloreBackground = parametri.coloreBackground;
        this.usernameDBMS = parametri.usernameDBMS;
        this.IPDBMS = parametri.IPDBMS;
        this.passwordDBMS = parametri.passwordDBMS;
        this.portaDBMS = parametri.portaDBMS;
        this.IPClient = parametri.IPClient;
        this.IPServerLog = parametri.IPServerLog;
        this.portaServerLog = parametri.portaServerLog;
    } 
    
}

class dimensioneFont {
        int valore;
        String unita;
}
