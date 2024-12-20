import java.sql.*;
import java.time.*;
import java.util.*;

public class ArchivioEsercitazioni {
    private final ParametriDiConfigurazione parametri;
    
    public ArchivioEsercitazioni(ParametriDiConfigurazione parametri){
        this.parametri = parametri;
    }
    
    public List<Esercitazione> fornisciPianoEsercizi(String utente, LocalDate da, LocalDate a){
        List<Esercitazione> piano = new ArrayList();
        try ( Connection co = DriverManager.getConnection("jdbc:mysql://" + parametri.IPDBMS + ":" + parametri.portaDBMS + "/physioplan", parametri.usernameDBMS, parametri.passwordDBMS);   
            Statement st = co.createStatement(); 
      ) { 
            ResultSet rs = st.executeQuery("SELECT * FROM esercitazioni WHERE nomeUtente = '" + utente + "' AND data >= '" + da.toString() + "' AND data <= '" + a.toString() + "' ORDER BY data");
            while (rs.next()){ 
              String nomeEsercizio = rs.getString("nomeEsercizio");
              String ripetizioni = ((Integer)rs.getInt("ripetizioni")).toString();
              String serieConsigliate = ((Integer)rs.getInt("serieConsigliate")).toString();
              String serieFatte = ((Integer)rs.getInt("serieFatte")).toString();
              double media = rs.getDouble("difficoltaMedia");
              media  = Math.floor(media*100)/100;
              String difficoltaMedia = ((Double)media).toString();
              String data = rs.getDate("data").toString();
              piano.add(new Esercitazione(nomeEsercizio, ripetizioni, serieConsigliate, serieFatte, difficoltaMedia, data));
        }
        } catch (SQLException e) {System.err.println(e.getMessage());}   
        piano.add(new Esercitazione("","", "", "", "", ""));
        return piano;
    }
    
    public void aggiungiEsercitazione(String nomeUtente, Esercitazione esercitazione){
        String nomeEsercizio = esercitazione.getNomeEsercizio();
        String data = esercitazione.getData();
        String ripetizioni = esercitazione.getRipetizioni();
        String serieConsigliate = esercitazione.getSerieConsigliate();
        try ( Connection co = DriverManager.getConnection("jdbc:mysql://" + parametri.IPDBMS + ":" + parametri.portaDBMS + "/physioplan", parametri.usernameDBMS, parametri.passwordDBMS);   
            PreparedStatement ps = co.prepareStatement("INSERT INTO esercitazioni VALUES (?, ?, ?, ?, ?, '0', '0.0')"); 
      ) { 
            ps.setString(1, nomeUtente); ps.setString(2, nomeEsercizio); ps.setString(3, data); 
            ps.setString(4, ripetizioni); ps.setString(5, serieConsigliate);
            System.out.println("rows affected: " + ps.executeUpdate());
        } catch (SQLException e) {System.err.println(e.getMessage());}   
    }
    
    public void rimuoviEsercitazione(String utente, Esercitazione esercitazione){
        String nomeEsercizio = esercitazione.getNomeEsercizio();
        String data = esercitazione.getData();
        try ( Connection co = DriverManager.getConnection("jdbc:mysql://" + parametri.IPDBMS + ":" + parametri.portaDBMS + "/physioplan", parametri.usernameDBMS, parametri.passwordDBMS);   
            PreparedStatement ps = co.prepareStatement("DELETE FROM esercitazioni WHERE nomeUtente = ? AND nomeEsercizio = ? AND data = ?"); 
        ) { 
            ps.setString(1,utente); ps.setString(2,nomeEsercizio); ps.setString(3,data);
            System.out.println("rows affected:" + ps.executeUpdate()); //11
        } catch (SQLException e) {System.err.println(e.getMessage());}
    }
    
    public void aggiornaEsercitazione(String utente,String difficoltaMedia, Esercitazione esercitazione){
        String nomeEsercizio = esercitazione.getNomeEsercizio();
        String data = esercitazione.getData();
        try ( Connection co = DriverManager.getConnection("jdbc:mysql://" + parametri.IPDBMS + ":" + parametri.portaDBMS + "/physioplan", parametri.usernameDBMS, parametri.passwordDBMS);   
            PreparedStatement ps1 = co.prepareStatement("UPDATE esercitazioni SET serieFatte = serieFatte+1 WHERE nomeUtente = ? AND nomeEsercizio = ? AND data = ?"); 
            PreparedStatement ps2 = co.prepareStatement("UPDATE esercitazioni SET difficoltaMedia = '" + difficoltaMedia + "' WHERE nomeUtente = ? AND nomeEsercizio = ? AND data = ?"); 
        ) { 
            ps1.setString(1,utente); ps1.setString(2,nomeEsercizio); ps1.setString(3,data);
            System.out.println("rows affected:" + ps1.executeUpdate());
            ps2.setString(1,utente); ps2.setString(2,nomeEsercizio); ps2.setString(3,data);
            System.out.println("rows affected:" + ps2.executeUpdate());
        } catch (SQLException e) {System.err.println(e.getMessage());}
    }
    
    public List<PuntiGrafico> fornisciDatiGrafico(String nomeUtente, String nomeEsercizio, LocalDate da, LocalDate a){
        List<PuntiGrafico> punti = new ArrayList();
        try ( Connection co = DriverManager.getConnection("jdbc:mysql://" + parametri.IPDBMS + ":" + parametri.portaDBMS + "/physioplan", parametri.usernameDBMS, parametri.passwordDBMS);   
            Statement st = co.createStatement(); 
        ) { 
            ResultSet rs = st.executeQuery("SELECT * FROM esercitazioni WHERE nomeUtente = '" + nomeUtente + "' AND nomeEsercizio = '"+ nomeEsercizio +"' AND data >= '" + da.toString() + "' AND data <= '" + a.toString() + "' ORDER BY data");
            while (rs.next()){
            double difficoltaMedia = rs.getDouble("difficoltaMedia");
            String data = rs.getDate("data").toString();
            punti.add(new PuntiGrafico(data, difficoltaMedia));
        }
        } catch (SQLException e) {System.err.println(e.getMessage());}   
        return punti;
    }
    
    public double fornisciMedia(String nomeEsercizio, LocalDate da, LocalDate a){
        double media = 5;
        try ( Connection co = DriverManager.getConnection("jdbc:mysql://" + parametri.IPDBMS + ":" + parametri.portaDBMS + "/physioplan", parametri.usernameDBMS, parametri.passwordDBMS);   
            Statement st = co.createStatement(); 
        ) { 
            ResultSet rs = st.executeQuery("SELECT AVG(difficoltaMedia) as media FROM esercitazioni WHERE nomeEsercizio = '"+ nomeEsercizio +"' AND data >= '" + da.toString() + "' AND data <= '" + a.toString() + "'");
            if(rs.next()){
                media = rs.getDouble("media");   
            }    
        } catch (SQLException e) {System.err.println(e.getMessage());}   
        return media;
    }
}
