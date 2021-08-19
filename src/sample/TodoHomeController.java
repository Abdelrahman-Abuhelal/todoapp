package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TodoHomeController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    datePicker.setValue(LocalDate.now());
    }
    @FXML
    Button button_Add_task;
    @FXML
    DatePicker datePicker;
    @FXML
    TextField desc_text;
    @FXML
    ListView <LocalTask> eventList;

    ObservableList <LocalTask> list= FXCollections.observableArrayList();

    @FXML
    private void addTask(Event e){
        list.add(new LocalTask(datePicker.getValue(),desc_text.getText()));
        eventList.setItems(list);
    }


}
