package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;
import javafx.scene.Node;

public class MainViewController implements Initializable {
	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem  menuItemAbout;
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("SEller");
		
		
	}
	@FXML
	public void onMenuItemDepartmentAction() {
		LoadView("/gui/DepartmentList.fxml",(DepartmentController controller) -> {
			 controller.SetDepartmentService(new DepartmentService());
			 controller.updateTableView(); 
		});
		
		
		
	}
	@FXML
	public void onMenuItemAboutAction() {
	LoadView("/gui/About.fxml",x ->{});
		
		
	}
	
	
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	
	private synchronized<T> void LoadView(String absoluteName, Consumer<T>initializingAction) {
		try {
		FXMLLoader loader =  new FXMLLoader(getClass().getResource(absoluteName));
		VBox newBox = loader.load();
		Scene mainScene = Main.getMainScene();
		VBox mainBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
		Node mainMenu = mainBox.getChildren().get(0);
		mainBox.getChildren().clear();
		mainBox.getChildren().add(mainMenu);
		mainBox.getChildren().addAll(newBox.getChildren());
		T controller= 	loader.getController();
		initializingAction.accept(controller);
		
		}catch (IOException e) {
			Alerts.showAlert("IOException","Error loading view",e.getMessage(),AlertType.ERROR);
		}
	}

	
/*	private synchronized void LoadView2(String absoluteName) {
		try {
		FXMLLoader loader =  new FXMLLoader(getClass().getResource(absoluteName));
		VBox newBox = loader.load();
		Scene mainScene = Main.getMainScene();
		VBox mainBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
		Node mainMenu = mainBox.getChildren().get(0);
		mainBox.getChildren().clear();
		mainBox.getChildren().add(mainMenu);
		mainBox.getChildren().addAll(newBox.getChildren());
		
		
		}catch (IOException e) {
			Alerts.showAlert("IOException","Error loading view",e.getMessage(),AlertType.ERROR);
		}
	}*/
	
	
}
