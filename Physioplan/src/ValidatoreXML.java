import javax.xml.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import javax.xml.transform.*;
import org.xml.sax.*;
import javax.xml.validation.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

public class ValidatoreXML {
    public static boolean valida(String XML, String fileXSD, boolean daFile) {
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder(); 
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);  
            Schema s = sf.newSchema(new StreamSource(new File(fileXSD)));
            if(daFile){
                Document d = db.parse(new File(XML));
                s.newValidator().validate(new DOMSource(d)); 
            }else{
                Source source = new StreamSource(new StringReader(XML));
                s.newValidator().validate(source); 
            }
        } catch (Exception e) {
            if (e instanceof SAXException)
                System.out.println("Errore di validazione: " + e.getMessage());
            else
                System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}