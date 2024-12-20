import java.io.*;
import java.time.*;
import java.util.*;

public class Cache {
    private final InterfacciaPianoFisioterapia interfaccia;
    
    public Cache(InterfacciaPianoFisioterapia interfaccia){
        this.interfaccia = interfaccia;
    }
    
    public void prelevaDatiInterfaccia(){
        String email = interfaccia.inputEmail.getText();
        int difficolta = (int)interfaccia.inputDifficolta.getSelectionModel().getSelectedItem();
        LocalDate da = interfaccia.inputDa.getValue();
        LocalDate a = interfaccia.inputA.getValue();
        DatiInterfaccia dati = new DatiInterfaccia(email, difficolta, da ,a);
        List<Esercitazione> list = interfaccia.tabellaEsercitazioni.getItems();
        dati.arrayList = new ArrayList();
        for(Esercitazione esercizio : list){
            EsercitazioneSerializzabile sEsercizio = new EsercitazioneSerializzabile(esercizio.getNomeEsercizio(), 
                    esercizio.getRipetizioni(), esercizio.getSerieConsigliate(), esercizio.getSerieFatte(), 
                    esercizio.getDifficoltaMedia(), esercizio.getData());
            dati.arrayList.add(sEsercizio);
        }
        archiviaSuFile(dati, "./src/datiInterfaccia.bin");
    }
    
    public void ripristinaInterfaccia(){
        DatiInterfaccia dati = prelevaDatiDaFile();
        if(dati!=null){
            ArrayList<Esercitazione> list = new ArrayList();
            for(EsercitazioneSerializzabile sEsercizio : dati.arrayList){
                Esercitazione esercizio = new Esercitazione(sEsercizio.nomeEsercizio, 
                sEsercizio.ripetizioni, sEsercizio.serieConsigliate, sEsercizio.serieFatte, 
                    sEsercizio.difficoltaMedia, sEsercizio.data);
                list.add(esercizio);
            }
            interfaccia.inputEmail.setText(dati.email);
            interfaccia.inputDifficolta.getSelectionModel().select(dati.difficolta);
            interfaccia.inputDa.setValue(dati.da);
            interfaccia.inputA.setValue(dati.a);
            interfaccia.tabellaEsercitazioni.aggiornaTabella(list);
        }
    }
    
    private void archiviaSuFile(DatiInterfaccia dati, String fileBin){
        try ( FileOutputStream fout = new FileOutputStream(fileBin);
            ObjectOutputStream oout = new ObjectOutputStream(fout);) {  
            oout.writeObject(dati);
        } catch (IOException ex) {
            System.out.println( "errore: impossibile conservare i dati interfaccia!" );
        }
    }
    
    private DatiInterfaccia prelevaDatiDaFile(){
        DatiInterfaccia dati = null;
        try ( FileInputStream fin = new FileInputStream( "./src/datiInterfaccia.bin" );
            ObjectInputStream oin = new ObjectInputStream(fin);
            ) { 
                dati = ( DatiInterfaccia ) oin.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System. out .println( "Cache non disponibile." );
        }
        return dati;
    }    
}

class DatiInterfaccia implements Serializable{
        
        public String email;
        public int difficolta;
        public LocalDate da;
        public LocalDate a;
        public ArrayList<EsercitazioneSerializzabile> arrayList;
        
        
        public DatiInterfaccia(String email, int difficolta, LocalDate da, LocalDate a) {
            this.email = email;
            this.difficolta = difficolta;
            this.da = da;
            this.a = a;
        }
    }

class EsercitazioneSerializzabile implements Serializable{
        public String nomeEsercizio;
        public String ripetizioni;
        public String serieConsigliate;
        public String serieFatte;
        public String difficoltaMedia;
        public String data;
        
        public EsercitazioneSerializzabile(String nomeEsercizio, String ripetizioni, String serieConsigliate, 
                String serieFatte, String difficoltaMedia, String data){
            this.nomeEsercizio = nomeEsercizio;
            this.ripetizioni = ripetizioni;
            this.serieConsigliate = serieConsigliate;
            this.serieFatte = serieFatte;
            this.difficoltaMedia = difficoltaMedia;
            this.data = data;
        }
    }
