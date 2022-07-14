/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.nyc;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.nyc.model.City;
import it.polito.tdp.nyc.model.CityWithDistance;
import it.polito.tdp.nyc.model.Model;
import it.polito.tdp.nyc.model.Provider;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAdiacenti"
    private Button btnAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaLista"
    private Button btnCreaLista; // Value injected by FXMLLoader

    @FXML // fx:id="cmbProvider"
    private ComboBox<Provider> cmbProvider; // Value injected by FXMLLoader

    @FXML // fx:id="cmbQuartiere"
    private ComboBox<City> cmbQuartiere; // Value injected by FXMLLoader

    @FXML // fx:id="txtMemoria"
    private TextField txtMemoria; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML // fx:id="clQuartiere"
    private TableColumn<CityWithDistance, String> clQuartiere; // Value injected by FXMLLoader
 
    @FXML // fx:id="clDistanza"
    private TableColumn<CityWithDistance, Double> clDistanza; // Value injected by FXMLLoader
    
    @FXML // fx:id="tblQuartieri"
    private TableView<CityWithDistance> tblQuartieri; // Value injected by FXMLLoader

    @FXML
    public void doCreaGrafo(ActionEvent event) {
    txtResult.clear();
    Provider p = this.cmbProvider.getValue();
    if(p == null) {
    	txtResult.appendText("Seleziona un Provider!");
    	return;
    }
    this.model.creaGrafo(p);
    txtResult.appendText("Numero di quartieri con almeno un hotspot del provider selezionato: " + model.getNVertici() + "\n");
    txtResult.appendText("Numero di archi presenti nel grafo: " + model.getNArchi());
    this.cmbQuartiere.getItems().clear();
    this.cmbQuartiere.getItems().addAll(this.model.getAllCitiesWithThatProvider());
    }
    

    @FXML
    void doQuartieriAdiacenti(ActionEvent event) {
    	txtResult.clear();
    	City selezionata = cmbQuartiere.getValue();
    	if(selezionata==(null)) {
    		txtResult.clear();
    		txtResult.appendText("Seleziona un Quartiere di NY. \n");
    		return;
    	}
    	if(this.model.getCityVicineWithTheirDistances(selezionata).isEmpty()) txtResult.appendText("Non ci sono quartieri adiacenti al quartiere selezionato. \n");
    	List<CityWithDistance> distanze = model.getCityVicineWithTheirDistances(selezionata);
    	
    	tblQuartieri.setItems(FXCollections.observableArrayList(distanze));
    	
    }

    @FXML
    void doSimula(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAdiacenti != null : "fx:id=\"btnAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaLista != null : "fx:id=\"btnCreaLista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbProvider != null : "fx:id=\"cmbProvider\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbQuartiere != null : "fx:id=\"cmbQuartiere\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMemoria != null : "fx:id=\"txtMemoria\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
       assert clDistanza != null : "fx:id=\"clDistanza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clQuartiere != null : "fx:id=\"clQuartiere\" was not injected: check your FXML file 'Scene.fxml'.";

       clQuartiere.setCellValueFactory(new PropertyValueFactory<CityWithDistance, String>("name"));
		clDistanza.setCellValueFactory(new PropertyValueFactory<CityWithDistance, Double>("distanza"));
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbProvider.getItems().addAll(this.model.getAllProviders());
    	
    }

}
