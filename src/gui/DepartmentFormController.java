package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormController implements Initializable {
	
	private Department entinty;
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
	
	@FXML
	public void onBtSaveAction() {
		System.out.println("OnBtSaveAction");
		
	}

	@FXML
	public void onBtCancelAction() {
		System.out.println("OnBtSaveAction");
		
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
