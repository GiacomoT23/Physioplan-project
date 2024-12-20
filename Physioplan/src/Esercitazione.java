import java.io.*;
import javafx.beans.property.*;

public class Esercitazione {
    private final SimpleStringProperty nomeEsercizio;
    private final SimpleStringProperty ripetizioni;
    private final SimpleStringProperty serieConsigliate;
    private final SimpleStringProperty serieFatte;
    private final SimpleStringProperty difficoltaMedia;
    private final SimpleStringProperty data;
    
    public Esercitazione(String nomeEsercizio, String ripetizioni, String serieConsigliate, String serieFatte, String difficoltàMedia, String data){
        this.nomeEsercizio = new SimpleStringProperty(nomeEsercizio);
        this.ripetizioni = new SimpleStringProperty(ripetizioni);
        this.serieConsigliate = new SimpleStringProperty(serieConsigliate);
        this.serieFatte = new SimpleStringProperty(serieFatte);
        this.difficoltaMedia = new SimpleStringProperty(difficoltàMedia);
        this.data = new SimpleStringProperty(data);
    }
    
    public String getNomeEsercizio(){return nomeEsercizio.get();}
    public void setNomeEsercizio(String nomeEsercizio){this.nomeEsercizio.set(nomeEsercizio);}
    public String getRipetizioni(){return ripetizioni.get();}
    public void setRipetizioni(String ripetizioni){this.ripetizioni.set(ripetizioni);}
    public String getSerieConsigliate(){return serieConsigliate.get();}
    public void setSerieConsigliate(String serieConsigliate){this.serieConsigliate.set(serieConsigliate);}
    public String getSerieFatte(){return serieFatte.get();}
    public void setSerieFatte(String serieFatte){this.serieFatte.set(serieFatte);}
    public String getDifficoltaMedia(){return difficoltaMedia.get();}
    public void setDifficoltaMedia(String difficoltàMedia){this.difficoltaMedia.set(difficoltàMedia);}
    public String getData(){return data.get();}
    public void setData(String data){this.data.set(data);}
    
}
