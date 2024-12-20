import java.util.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;


public class TabellaEsercitazioni extends TableView {
    private ObservableList<Esercitazione> listaEsercitazioni;
    
    public TabellaEsercitazioni(ParametriDiConfigurazione parametri){
        super();
        listaEsercitazioni = FXCollections.observableArrayList();
        this.setEditable(true);
        TableColumn nomeCol = new TableColumn("Nome_esercizio");
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nomeEsercizio"));
        nomeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nomeCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Esercitazione, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Esercitazione, String> t) {
                    ((Esercitazione) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setNomeEsercizio(t.getNewValue());
                }
            }
        );
        nomeCol.setPrefWidth(199);
        TableColumn ripetizioniCol = new TableColumn("Ripetizioni");
        ripetizioniCol.setCellValueFactory(new PropertyValueFactory<>("ripetizioni"));
        ripetizioniCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ripetizioniCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Esercitazione, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Esercitazione, String> t) {
                    ((Esercitazione) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setRipetizioni(t.getNewValue());
                }
            }
        );
        ripetizioniCol.setPrefWidth(199);
        TableColumn serieConsigliateCol = new TableColumn("Serie_consigliate");
        serieConsigliateCol.setCellValueFactory(new PropertyValueFactory<>("serieConsigliate"));
        serieConsigliateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        serieConsigliateCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Esercitazione, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Esercitazione, String> t) {
                    ((Esercitazione) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setSerieConsigliate(t.getNewValue());
                }
            }
        );
        serieConsigliateCol.setPrefWidth(199);
        TableColumn serieFatteCol = new TableColumn("Serie_fatte");
        serieFatteCol.setCellValueFactory(new PropertyValueFactory<>("serieFatte"));
        serieFatteCol.setCellFactory(TextFieldTableCell.forTableColumn());
        serieFatteCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Esercitazione, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Esercitazione, String> t) {
                    ((Esercitazione) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setSerieFatte(t.getNewValue());
                }
            }
        );
        serieFatteCol.setPrefWidth(199);
        TableColumn difficoltaCol = new TableColumn("Difficoltà_media");
        difficoltaCol.setCellValueFactory(new PropertyValueFactory<>("difficoltaMedia"));
        difficoltaCol.setCellFactory(TextFieldTableCell.forTableColumn());
        difficoltaCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Esercitazione, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Esercitazione, String> t) {
                    ((Esercitazione) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setDifficoltaMedia(t.getNewValue());
                }
            }
        );
        difficoltaCol.setPrefWidth(199);
        TableColumn dataCol = new TableColumn("Data");
        dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));
        dataCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dataCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Esercitazione, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Esercitazione, String> t) {
                    ((Esercitazione) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setData(t.getNewValue());
                }
            }
        );
        dataCol.setPrefWidth(199);
        
        this.getColumns().addAll(nomeCol, ripetizioniCol, serieConsigliateCol, serieFatteCol, difficoltaCol, dataCol);
        setStyle(parametri);
    }
    
    public void aggiornaTabella(List<Esercitazione> esercitazioni){
        listaEsercitazioni = FXCollections.observableArrayList(esercitazioni);
        this.setItems(listaEsercitazioni);
    }
    
    public Esercitazione getUltimoElemento(){
        return listaEsercitazioni.get(listaEsercitazioni.size()-1);
    }
    
    private void setStyle(ParametriDiConfigurazione parametri){
        int n = parametri.nRigheTabella;
        n++;
        int cellSize = 35;
        this.setFixedCellSize(cellSize);
        this.setPrefHeight(cellSize*n);
        this.setMinHeight(cellSize*n);
        this.setMaxHeight(cellSize*n);
        this.setPrefWidth(1200);
    }
    
}
