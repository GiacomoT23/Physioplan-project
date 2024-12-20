import java.time.*;
import java.util.*;
import javafx.application.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class InterfacciaPianoFisioterapia extends Application {
    
    private final Label titolo = new Label("Physioplan");
    private final Label email = new Label("Email");
    public TextField inputEmail = new TextField("email");
    private final Button visualizza = new Button("Visualizza");
    private final Button aggiungi = new Button("Aggiungi");
    private final Button rimuovi = new Button("Rimuovi");
    private Label difficolta;
    public ComboBox inputDifficolta;
    private final ObservableList<Integer> listaDifficolta = FXCollections.observableArrayList();
    private final Button fatto = new Button("Fatto");
    private final Label da = new Label("Da");
    public DatePicker inputDa;
    private final Label a = new Label("A");
    public DatePicker inputA;
    private final ParametriDiConfigurazione parametri = new ParametriDiConfigurazione();
    public TabellaEsercitazioni tabellaEsercitazioni = new TabellaEsercitazioni(parametri);
    private GraficoDifficoltà graficoDifficolta;
    private final ObservableList<XYChart.Series> listaGrafici = FXCollections.observableArrayList();
    private final ObservableList<String> elementiAsseX = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage primaryStage) {
                
        LocalDate dataa = LocalDate.now();
        int n = parametri.nUltimiGiorniGrafico;
        LocalDate datada = dataa.minusDays(n);
        inputDa = new DatePicker(datada);
        inputA = new DatePicker(dataa);
        difficolta = new Label("Difficoltà("+parametri.difficoltaMin+"-"+parametri.difficoltaMax+")");
        setInputDifficolta();
                
        Axis xAxis = new CategoryAxis(elementiAsseX);
        Axis yAxis = new NumberAxis("difficoltà", parametri.difficoltaMin, parametri.difficoltaMax, 1);
        graficoDifficolta = new GraficoDifficoltà(xAxis, yAxis, listaGrafici);
        
        VBox vBox = new VBox();
        GridPane grid = new GridPane();
        setLayout(vBox, grid, parametri);
        setFont(parametri, vBox);
        
        visualizza.setOnAction((ActionEvent ev)->{visualizzaProgramma();});
        aggiungi.setOnAction((ActionEvent ev)->{aggiungiEsercizio();});
        rimuovi.setOnAction((ActionEvent ev)->{rimuoviEsercizio();});
        fatto.setOnAction((ActionEvent ev)->{esercizioCompletato();});
        tabellaEsercitazioni.setOnMouseClicked(e -> {visualizzaGrafico();});
        inputDa.setOnAction((ActionEvent ev)->{eventoDatePicker("DA");});
        inputA.setOnAction((ActionEvent ev)->{eventoDatePicker("A");});

        Group root = new Group(vBox);
        Scene scene = new Scene(root, 1300, 950);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Cache cache = new Cache(this);
        primaryStage.setOnCloseRequest((WindowEvent we) -> {cache.prelevaDatiInterfaccia();});
        cache.ripristinaInterfaccia();
        root.requestFocus();
    }

    private void visualizzaProgramma(){
        EventoDiNavigazioneGUI evento = new EventoDiNavigazioneGUI("Physioplan", parametri.IPClient, LocalDateTime.now(), "VISUALIZZA");
        evento.inviaXML(parametri.IPServerLog, parametri.portaServerLog);
        ArchivioEsercitazioni archivio = new ArchivioEsercitazioni(parametri);
        String utente = inputEmail.getText();
        LocalDate dataDa = inputDa.getValue();
        LocalDate dataA = inputA.getValue();
        tabellaEsercitazioni.aggiornaTabella(archivio.fornisciPianoEsercizi(utente, dataDa, dataA));
    }
    
    private void aggiungiEsercizio(){
        EventoDiNavigazioneGUI evento = new EventoDiNavigazioneGUI("Physioplan", parametri.IPClient, LocalDateTime.now(), "AGGIUNGI");
        evento.inviaXML(parametri.IPServerLog, parametri.portaServerLog);
        ArchivioEsercitazioni archivio = new ArchivioEsercitazioni(parametri);
        String utente = inputEmail.getText();
        Esercitazione esercitazione = tabellaEsercitazioni.getUltimoElemento();
        archivio.aggiungiEsercitazione(utente, esercitazione);
        tabellaEsercitazioni.getSelectionModel().clearSelection();
        visualizzaProgramma();
    }
    
    private void rimuoviEsercizio(){
        EventoDiNavigazioneGUI evento = new EventoDiNavigazioneGUI("Physioplan", parametri.IPClient, LocalDateTime.now(), "RIMUOVI");
        evento.inviaXML(parametri.IPServerLog, parametri.portaServerLog);
        ArchivioEsercitazioni archivio = new ArchivioEsercitazioni(parametri);
        String utente = inputEmail.getText();
        Esercitazione esercitazione = (Esercitazione)tabellaEsercitazioni.getSelectionModel().getSelectedItem();
        archivio.rimuoviEsercitazione(utente, esercitazione);
        tabellaEsercitazioni.getSelectionModel().clearSelection();
        visualizzaProgramma();
    }
    
    private void esercizioCompletato(){
        EventoDiNavigazioneGUI evento = new EventoDiNavigazioneGUI("Physioplan", parametri.IPClient, LocalDateTime.now(), "FATTO");
        evento.inviaXML(parametri.IPServerLog, parametri.portaServerLog);
        ArchivioEsercitazioni archivio = new ArchivioEsercitazioni(parametri);
        String utente = inputEmail.getText();
        Esercitazione esercitazione = (Esercitazione)tabellaEsercitazioni.getSelectionModel().getSelectedItem();
        Double difficoltaMedia = Double.parseDouble(esercitazione.getDifficoltaMedia());
        int serieFatte = Integer.parseInt(esercitazione.getSerieFatte());
        int difficoltaSerieAttuale = (int)inputDifficolta.getSelectionModel().getSelectedItem();
        difficoltaMedia = ((serieFatte * difficoltaMedia) + difficoltaSerieAttuale)/(serieFatte + 1);
        archivio.aggiornaEsercitazione(utente, difficoltaMedia.toString(), esercitazione);
        tabellaEsercitazioni.getSelectionModel().clearSelection();
        visualizzaProgramma();
    }
    
    private void visualizzaGrafico(){
        EventoDiNavigazioneGUI evento = new EventoDiNavigazioneGUI("Physioplan", parametri.IPClient, LocalDateTime.now(), "GRAFICO");
        evento.inviaXML(parametri.IPServerLog, parametri.portaServerLog);
        LocalDate dataDa = inputDa.getValue();
        LocalDate dataA = inputA.getValue();
        graficoDifficolta.setDateAsseX(dataDa, dataA, elementiAsseX);
        String nomeUtente = inputEmail.getText();
        Esercitazione esercitazione = (Esercitazione)tabellaEsercitazioni.getSelectionModel().getSelectedItem();
        String nomeEsercizio = esercitazione.getNomeEsercizio();
        ArchivioEsercitazioni archivio = new ArchivioEsercitazioni(parametri);
        List<PuntiGrafico> punti = archivio.fornisciDatiGrafico(nomeUtente, nomeEsercizio, dataDa, dataA);
        graficoDifficolta.setGraficoUtente(punti, listaGrafici);
        double media = archivio.fornisciMedia(nomeEsercizio, dataDa, dataA);
        graficoDifficolta.setGraficoMedia(dataDa, dataA, media, listaGrafici);
    }
    
    private void setLayout(VBox vBox, GridPane grid, ParametriDiConfigurazione parametri){
        grid.setHgap(50);
        grid.setVgap(20);
        grid.setPadding(new Insets(20, 30, 0, 30));
        grid.add(email, 0, 0);
        grid.add(da, 1, 0);
        grid.add(a, 2, 0);
        grid.add(inputEmail, 0, 1);
        grid.add(inputDa, 1, 1);
        grid.add(inputA, 2, 1);
        grid.add(visualizza, 3, 1);
        grid.add(difficolta, 2, 2);
        grid.add(aggiungi, 0, 3);
        grid.add(rimuovi, 1, 3);
        grid.add(inputDifficolta, 2, 3);
        grid.add(fatto, 3, 3);
        vBox.getChildren().addAll(titolo, grid, tabellaEsercitazioni, graficoDifficolta);
        vBox.setPadding(new Insets(0, 30, 0, 30));
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
    }
    
    public void setFont(ParametriDiConfigurazione parametri, VBox vBox){
        vBox.setStyle("-fx-font: "+parametri.dimensioneFont.valore+parametri.dimensioneFont.unita+" "+parametri.font
                        +"; -fx-background-color: "+parametri.coloreBackground+";");
        titolo.setStyle("-fx-font-size: 40px; -fx-text-fill: blue; -fx-font-weight: bold;");
        email.setStyle("-fx-text-fill: blue;");
        difficolta.setStyle("-fx-text-fill: blue;");
        da.setStyle("-fx-text-fill: blue;");
        a.setStyle("-fx-text-fill: blue;");
    }
    
    public void setInputDifficolta(){
        inputDifficolta = new ComboBox();
        int difficoltaDaAggiungere;
        for(difficoltaDaAggiungere = parametri.difficoltaMin; difficoltaDaAggiungere
                <= parametri.difficoltaMax; difficoltaDaAggiungere++){
            listaDifficolta.add((Integer)difficoltaDaAggiungere);
        }
        inputDifficolta.setItems(listaDifficolta);
        inputDifficolta.getSelectionModel().select(parametri.difficoltaMin);
    }
    
    public void eventoDatePicker(String tipo){
        EventoDiNavigazioneGUI evento = new EventoDiNavigazioneGUI("Physioplan", parametri.IPClient, LocalDateTime.now(), "DATEPICKER "+tipo);
        evento.inviaXML(parametri.IPServerLog, parametri.portaServerLog);
    }
    
    
}
