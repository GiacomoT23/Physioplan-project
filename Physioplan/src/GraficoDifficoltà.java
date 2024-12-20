import java.time.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.chart.*;

public class GraficoDifficoltà extends LineChart{

    private ObservableList<XYChart.Series> listaGrafici = FXCollections.observableArrayList();
    private ObservableList<XYChart.Data> datiGraficoUtente;
    private ObservableList<XYChart.Data> datiGraficoMedia;
    private ObservableList<String> elementiAsseX = FXCollections.observableArrayList();
    
    public GraficoDifficoltà(Axis xAxis, Axis yAxis, ObservableList<XYChart.Series> listaGrafici){
        super(xAxis, yAxis, listaGrafici);
    }
    
    public void setDateAsseX(LocalDate dataDa, LocalDate dataA, ObservableList<String> elementiAsseX){
        elementiAsseX.clear();
        LocalDate dataDaScorrere = dataDa;
        while(true){
            elementiAsseX.add(dataDaScorrere.toString());
            dataDaScorrere = dataDaScorrere.plusDays(1);
            if(dataDaScorrere.isAfter(dataA))
                break;
        }
    }
    
    public void setGraficoUtente(List<PuntiGrafico> punti, ObservableList<XYChart.Series> listaGrafici){
        listaGrafici.clear();
        datiGraficoUtente = FXCollections.observableArrayList();
        punti.stream().forEach((punto) -> {
            datiGraficoUtente.add(new XYChart.Data(punto.data, punto.difficoltaMedia));
        });
        listaGrafici.add(new XYChart.Series("Andamento difficoltà", datiGraficoUtente));
    }
    
    public void setGraficoMedia(LocalDate dataDa, LocalDate dataA, double media, ObservableList<XYChart.Series> listaGrafici){
        datiGraficoMedia = FXCollections.observableArrayList();
        datiGraficoMedia.add(new XYChart.Data(dataDa.toString(), media));
        if(!dataDa.equals(dataA))
            datiGraficoMedia.add(new XYChart.Data(dataA.toString(), media));
        listaGrafici.add(new XYChart.Series("Media", datiGraficoMedia));
    }
}
