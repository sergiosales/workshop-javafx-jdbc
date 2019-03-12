package gui;

import java.net.URL;
import java.util.ResourceBundle;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {
	
	private Department entinty;
	private DepartmentService service;
	
	
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private Label LaberErrorName;
	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;
	
	
	public void SetDepartment(Department entity) {
		this.entinty = entity;
		
	}
	public void SetDepartmentService(DepartmentService service) {
		this.service = service;	
		
	}
	
	
	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if(entinty == null) {
			throw new IllegalStateException("Entidade esta Vazia");
		
		}
		if(service == null) {
			
			throw new IllegalStateException("O Serviço esta vazio");
		}
		try {
			entinty = getFormData();
			service.SaveOrUpdate(entinty);
			Utils.currentStage(event).close();
			
		} catch (Exception e) {
		 Alerts.showAlert("Error Saving object","Null",e.getMessage(),AlertType.ERROR);
		}
		
		
	}

	private Department getFormData() {
	Department dep = new Department();
	dep.setId(Utils.tryParseToInt(txtId.getText()));
	dep.setName(txtName.getText());
	return dep;
	}
	@FXML
	public void onBtCancelAction(ActionEvent event) {
	Utils.currentStage(event);
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initilizeNodes();
		
	}
	
	
	private void initilizeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName,30);
		
		
	}
	
	public void updateFormData() {
		if(entinty == null) {
			throw new IllegalStateException("Entidade esta vazia!!!");
			
		}
		txtId.setText(String.valueOf(entinty.getId()));
		txtId.setText(entinty.getName());
		
		
	}

}
