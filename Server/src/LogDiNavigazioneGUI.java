import java.io.*;
import java.net.*;
import java.nio.file.*;

public class LogDiNavigazioneGUI {

    
    public static void main(String[] args) {
        try ( ServerSocket servs = new ServerSocket(8080);){
            while(true){
                try(Socket sd = servs.accept();
                DataInputStream din = new DataInputStream(sd.getInputStream()) 
                ){  String xml = din.readUTF();
                    if(ValidatoreXML.valida(xml, "src/evento.xsd", false))
                        archiviaSuFile(xml);
                }
            }
        }catch (Exception e) {e.printStackTrace();}
    }
    
    private static void archiviaSuFile(String fileXML){
        try { Files.write(Paths.get("src/log.xml"), fileXML.getBytes(), StandardOpenOption.valueOf("APPEND"));
        } catch (Exception e) {}
    }
    
}
