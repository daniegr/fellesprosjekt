package group;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import json.JsonArray;
import json.JsonValue;

public class ManageGroupsScreenController implements Initializable, ControllerInterface {
	
	ScreensController mainController;
	Calendar calendar = Calendar.getInstance();
	
	@FXML
	Button previousWeekButton2;
	@FXML
	Button nextWeekButton2;
	
	@FXML
	Text year;
	@FXML
	Text weekNumber;
	@FXML
	Text mondayDate;
	@FXML
	Text mondayMonth;
	@FXML
	Text tuesdayDate;
	@FXML
	Text tuesdayMonth;
	@FXML
	Text wednesdayDate;
	@FXML
	Text wednesdayMonth;
	@FXML
	Text thursdayDate;
	@FXML
	Text thursdayMonth;
	@FXML
	Text fridayDate;
	@FXML
	Text fridayMonth;
	@FXML
	Text saturdayDate;
	@FXML
	Text saturdayMonth;
	@FXML
	Text sundayDate;
	@FXML
	Text sundayMonth;
	
	@FXML
	TableView mondayTable;
	
	@FXML
	TableView tuesdayTable; 
	
	@FXML
	TableView wednesdayTable;
	
	@FXML
	TableView thursdayTable;
	
	@FXML
	TableView fridayTable;
	
	@FXML
	TableView saturdayTable;
	
	@FXML
	TableView sundayTable;
	
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		mainController = screenParent;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		weekFiller(calendar, 0);
		tuesdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		wednesdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		thursdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		fridayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		saturdayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
		sundayTable.getStylesheets().addAll(getClass().getResource("/css/hide-tableview-header.css").toExternalForm());
	}
	
	@FXML
	public void handleNextWeekButton(ActionEvent event) {
		weekFiller(calendar, 1);
	}

	
	@FXML
	public void handlePreviousWeekButton(ActionEvent event) {
		weekFiller(calendar, -1);
	}
	
	
	private void weekFiller(Calendar calendar, int increment) {
		int counter = 0;
		if (increment == 0) {
			calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
			weekNumber.setText(Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
			year.setText(Integer.toString(calendar.get(Calendar.YEAR)));
		}
		else if (increment == 1) {
			weekNumber.setText(Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
			year.setText(Integer.toString(calendar.get(Calendar.YEAR)));
		}
		else if (increment == -1) {
			calendar.add(Calendar.WEEK_OF_YEAR, increment-1);
			weekNumber.setText(Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
			year.setText(Integer.toString(calendar.get(Calendar.YEAR)));
		}
		while (counter < 7) {
			if (counter == 0) {
				mondayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				mondayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
			}
			else if (counter == 1) {
				tuesdayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				tuesdayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
			}
			else if (counter == 2) {
				wednesdayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				wednesdayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
			}
			else if (counter == 3) {
				thursdayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				thursdayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
			}
			else if (counter == 4) {
				fridayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				fridayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
			}
			else if (counter == 5) {
				saturdayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				saturdayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
			}
			else if (counter == 6) {
				sundayDate.setText(Integer.toString(calendar.get(Calendar.DATE)));
				sundayMonth.setText(getMonthFromInt(calendar.get(Calendar.MONTH)));
			}
			if (calendar.get(Calendar.DATE) == calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
				if (calendar.get(Calendar.MONTH) == calendar.getActualMaximum(Calendar.MONTH)) {
					calendar.add(Calendar.YEAR, 1);
					calendar.set(Calendar.MONTH, 0);
				}
				else {
					calendar.add(Calendar.MONTH, 1);
				}
				calendar.set(Calendar.DATE, 1);
			}
			else {
				calendar.add(Calendar.DATE, 1);
			}
			counter++;
		}
	}
	
	
	private static String getMonthFromInt(int month) {
	    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	    return monthNames[month];
	}
}

