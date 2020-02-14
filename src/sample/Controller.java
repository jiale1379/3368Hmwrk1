package sample;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    private ListView<Employee> employeeListView;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private CheckBox isActiveCheckBox;
    @FXML
    public Button delete;

    public void deleteClick(ActionEvent event){
                employeeListView.getItems().removeAll(employeeListView.getSelectionModel().getSelectedItems());
    }
    public void clearClick(ActionEvent event){
        firstNameTextField.clear();
        lastNameTextField.clear();
        isActiveCheckBox.setSelected(false);
    }
    public void addClick(ActionEvent event){
        Employee newemployee = new Employee();
        newemployee.firstName = firstNameTextField.getText();
        newemployee.lastName = lastNameTextField.getText();
        if(isActiveCheckBox.isSelected()){
            newemployee.hire();
        }
        employeeListView.getItems().add(newemployee);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        employeeListView.getSelectionModel().selectedItemProperty().addListener((
                ObservableValue < ? extends Worker> ov, Worker old_val, Worker new_val)->
                {
                    Worker selectedItem = employeeListView.getSelectionModel().getSelectedItem();

                    firstNameTextField.setText(((Employee)selectedItem).firstName);
                    lastNameTextField.setText(((Employee)selectedItem).lastName);
                    isActiveCheckBox.setSelected(((Employee)selectedItem).isActive);
                }
                );

        ObservableList<Employee> items = employeeListView.getItems();
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        employee1.firstName = "Robert";
        employee1.lastName = "Smith";
        employee2.firstName = "Lisa";
        employee2.lastName = "Smith";

        items.add(employee1);
        items.add(employee2);

        for(int i = 0; i < 10; i++)
        {
            Employee employee = new Employee();
            employee.firstName = "Generic";
            employee.lastName = "Employee" + " " + i;
            employee.hire();
            items.add(employee);
        }

        Staff staff1 = new Staff();
        staff1.firstName = "StaffPerson";
        staff1.lastName = "GoodWorker";

        Faculty faculty1 = new Faculty();
        faculty1.firstName = "FacultyPerson";
        faculty1.lastName = "TerribleWorker";

        items.add(staff1);
        items.add(faculty1);

    }
}
