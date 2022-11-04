package com.example.lab3a;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ImageView formula;
    @FXML
    private TextField to;
    @FXML
    private TextField from;
    @FXML
    private TableView<MclarenDto> resultTable;
    @FXML
    private TextField step;
    @FXML
    private TextField xValues;
    @FXML
    private ComboBox<String> epsValues;
    @FXML
    private Label result;
    @FXML
    private Label resultIncluded;
    @FXML
    public void clear(ActionEvent ae){
        xValues.clear();
        epsValues.getSelectionModel().selectFirst();
        resultTable.getColumns().clear();
        to.setText("0.9");
        from.setText("-0.9");
        step.setText("0.1");
    }
    @FXML
    public void calculateClick(ActionEvent actionEvent) {
        if(Parser.stringParseDouble(xValues.getText()) > 0.9) xValues.setText("0.9");
        else if(Parser.stringParseDouble(xValues.getText()) < -0.9) xValues.setText("-0.9");
        result.setText(String.valueOf(
                Calculator.calculateMclaren(
                Parser.stringParseDouble(xValues.getText()),
                Double.parseDouble(
                        epsValues
                                .getSelectionModel()
                                .getSelectedItem())
                )
        ));
        resultIncluded.setText(String.valueOf(
                Math.sqrt(
                        Parser.stringParseDouble(xValues.getText()) + 1
                )
        ));
    }
    @FXML
    public void calculateAreaClick(ActionEvent ae){
        resultTable.getColumns().clear();
        resultTable.getItems().clear();
        try{
            double dTo = Parser.stringParseDouble(to.getText());
            double dFrom = Parser.stringParseDouble(from.getText());
            double dStep = Parser.stringParseDouble(step.getText());
            if(dTo > dFrom){
                if(Math.abs(dFrom) < 1
                        && Math.abs(dTo) < 1){
                    if(dStep < dTo - dFrom){
                        ObservableList<MclarenDto> observableList = Calculator.calculateAreaMclaren(dFrom, dTo, dStep,
                                Double.parseDouble(epsValues.getSelectionModel().getSelectedItem()));
                        TableColumn<MclarenDto, String> xColumn = new TableColumn<>("x");
                        TableColumn<MclarenDto, String> valueColumn = new TableColumn<>("value");
                        TableColumn<MclarenDto, String> standardColumn = new TableColumn<>("standardValue");
                        xColumn.setCellValueFactory(new PropertyValueFactory<MclarenDto, String>("x"));
                        valueColumn.setCellValueFactory(new PropertyValueFactory<MclarenDto, String>("value"));
                        standardColumn.setCellValueFactory(new PropertyValueFactory<MclarenDto, String>("standardValue"));
                        resultTable.getColumns().clear();
                        resultTable.getColumns().add(xColumn);
                        resultTable.getColumns().add(valueColumn);
                        resultTable.getColumns().add(standardColumn);
                        resultTable.getItems().addAll(observableList);
                    }
                }
            }
        }catch (NumberFormatException exception){
            Alert alert = new Alert(Alert.AlertType.ERROR, "You have entered the wrong value");
            alert.show();
        }
    }
    @FXML
    public void close(ActionEvent ae){
        Stage stage = (Stage)((Node)ae.getSource()).getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("src/main/resources/com/example/lab3a/img/img_4.png");
        Image image = new Image(file.toURI().toString());
        formula.setImage(image);
        to.setText("0.9");
        from.setText("-0.9");
        step.setText("0.1");
    }
}