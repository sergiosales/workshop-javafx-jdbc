package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.exceptions.ValidationException;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {
	
	private Department entinty;
	private DepartmentService service;
	private List <DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	
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
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
		
		
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
			notifyDataChangeListernes();
			Utils.currentStage(event).close();
			
		} catch(ValidationException e) {
			setErrorMessages(e.getErrors());
			
		} 
		catch (Exception e) {
		 Alerts.showAlert("Error Saving object","Null",e.getMessage(),AlertType.ERROR);
		}
		
		
	}

	private void notifyDataChangeListernes() {
	for(DataChangeListener listener :dataChangeListeners) {
		listener.onDataChanged();
		
		
	}	
		
	}
	private Department getFormData() {
	ValidationException  validacao = new ValidationException("Validações de Erro");	
	Department dep = new Department();
	dep.setId(Utils.tryParseToInt(txtId.getText()));
	if(txtName.getText()==null || txtName.getText().trim().equals("")) {
		validacao.addError("Name","O Campo nome não pode ser vazio");
		
	}
	dep.setName(txtName.getText());
	
	if(validacao.getErrors().size()>0) {
		
		throw validacao;
	}
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
	
	private void setErrorMessages(Map<String,String> errors) {
		Set<String> fields = errors.keySet();
		if(fields.contains("Name")) {
			LaberErrorName.setText(errors.get("Name"));
			
		}
		
	
	}

}
