package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;
import javafx.event.ActionEvent;

public class DepartmentController implements Initializable, DataChangeListener {
	
	private DepartmentService service;
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button Btnew;
	
	private ObservableList<Department> objList;
	
	@FXML
	public void onBtNewAction(ActionEvent event){
	Stage parentStage = Utils.currentStage(event);
	Department dep = new Department();
	createDialogForm(dep,"/gui/DepartmentForm.fxml", parentStage);
	
	}
	
	public void  SetDepartmentService(DepartmentService service) {
		this.service = service;
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}


	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		Stage stage =  (Stage) Main.getMainScene().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
		
		
	}
	
	public void updateTableView() {
		if (service== null) {
			throw new IllegalStateException("Serviçe was nullo");
			
		}
		List<Department > list = service.findAll();
		objList = FXCollections.observableArrayList(list);
		tableViewDepartment.setItems(objList);
		
	}
	
	
private void createDialogForm(Department dep,String absoluteName, Stage parentStage){
	try {
		FXMLLoader loader =  new FXMLLoader(getClass().getResource(absoluteName));
		Pane pane = loader.load();
		DepartmentFormController controller = loader.getController();
		controller.SetDepartment(dep);
		controller.SetDepartmentService(new DepartmentService());
		controller.subscribeDataChangeListener(this);
		controller.updateFormData();		
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Entre com os dados do Departamento");
		dialogStage.setScene(new Scene(pane));
		dialogStage.setResizable(false); 
		dialogStage.initOwner(parentStage);
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.showAndWait(); 

		
	} catch (IOException e) {
	Alerts.showAlert("Io Exception", "Error",e.getMessage(), AlertType.ERROR);
	}
	
	
}

@Override
public void onDataChanged() {
	updateTableView();
	
} 	
	

}
