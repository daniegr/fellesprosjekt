package group;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import client.ServerCodes;
import client.TCPClient;
import program.ControllerInterface;
import program.Main;
import program.ScreensController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import json.JsonArray;
import json.JsonValue;

public class ViewGroupsScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	
	@FXML
	Button backToMainPageButton;
	
	@FXML
	ListView<String> lvGroups;
	
	TCPClient client;
	
	//Metode for backToMainPageButton
	@FXML
	public void handleBackToMainPageButton(ActionEvent event) {
		mainController.setScreen(Main.mainPageID);
	}
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;
	}
	
	private void setTCPClient() throws UnknownHostException, IOException {
		client = new TCPClient();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		lvGroups.setStyle("-fx-font-size:30;");
		lvGroups.setMouseTransparent( true );
		lvGroups.setFocusTraversable( false );
		
		try {
			setTCPClient();
			String serverReply = client.customQuery(ServerCodes.GetAllGroups, "'None'");
			String[] answer= serverReply.split("#");
			
			JsonArray jsonArray = JsonArray.readFrom( answer[1] );
			
			List<String> groupList = new ArrayList<>();
			
			for( JsonValue value : jsonArray ) {
				String gruppeNavn = value.asObject().get( "navn" ).asString();
				groupList.add(gruppeNavn);
			}

			ObservableList<String> myObservableList = FXCollections.observableList(groupList);
			lvGroups.setItems(myObservableList);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
