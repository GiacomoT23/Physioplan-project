import com.thoughtworks.xstream.*;
import java.io.*;
import java.net.*;
import java.time.*;

public class EventoDiNavigazioneGUI {
    String applicazione;
    String IP;
    String dataOra;
    String etichetta;
    
    public EventoDiNavigazioneGUI(String applicazione, String IP, LocalDateTime dataOra, String etichetta){
        this.applicazione = applicazione;
        this.IP = IP;
        this.dataOra = dataOra.toString();
        this.etichetta = etichetta;
    }
    
    public void inviaXML(String IPServerLog, int portaServerLog){
        String xml = serializzaXML();
        if(ValidatoreXML.valida(xml, "src/evento.xsd", false)){
            try ( DataOutputStream dout = new DataOutputStream( (new Socket(IPServerLog, portaServerLog) ).getOutputStream())
            ) { dout.writeUTF(xml);} catch (Exception e) {e.printStackTrace();}
        }
    }
    
    private String serializzaXML(){
        XStream xs = new XStream();
        return xs.toXML(this);
    }
}
