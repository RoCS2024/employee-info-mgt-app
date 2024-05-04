package com.employee.info.mgt.app.controllers.item;

import com.employee.info.mgmt.appl.facade.employee.EmployeeFacade;
import com.employee.info.mgmt.appl.facade.employee.impl.EmployeeFacadeImpl;
import com.employee.info.mgmt.appl.model.Employee;
import com.employee.info.mgmt.data.employee.dao.EmployeeDao;
import com.employee.info.mgmt.data.employee.dao.impl.EmployeeDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {

    @FXML
    private TextField employeeId;

    @FXML
    private ComboBox<String> positionRc;

    @FXML
    private DatePicker dateEmployed;

    @FXML
    private TextField lastName;

    @FXML
    private TextField firstName;

    @FXML
    private TextField middleName;

    @FXML
    private ComboBox<String> sex;

    @FXML
    private DatePicker birthday;

    @FXML
    private TextField birthplace;

    @FXML
    private ComboBox<String> civilStatus;

    @FXML
    private TextField citizenship;

    @FXML
    private TextField religion;

    @FXML
    private TextField height;

    @FXML
    private TextField weight;

    @FXML
    private TextField email;

    @FXML
    private TextField sss;

    @FXML
    private TextField tin;

    @FXML
    private TextField pagibig;

    private Employee employee;

    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    private EmployeeFacade employeeFacade = new EmployeeFacadeImpl(employeeDao);

    @FXML
    protected void onAddEmployeeClicked(ActionEvent event) throws IOException {
        try{
            Employee addEmployee = new Employee();
            addEmployee.setEmployeeNo(employeeId.getText());
            addEmployee.setPositionInRC((String) positionRc.getValue());
            addEmployee.setLastName(lastName.getText());
            addEmployee.setFirstName(firstName.getText());
            addEmployee.setMiddleName(middleName.getText());
            addEmployee.setSex((String) sex.getValue());

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateEmployed.getValue().toString());
            long time = date.getTime();
            addEmployee.setDateEmployed(new Timestamp(time));

            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = dateFormat1.parse(birthday.getValue().toString());
            long time1 = date1.getTime();
            addEmployee.setBirthdate(new Timestamp(time1));

            addEmployee.setBirthplace(birthplace.getText());
            addEmployee.setCivilStatus((String) civilStatus.getValue());
            addEmployee.setCitizenship(citizenship.getText());
            addEmployee.setReligion(religion.getText());
            addEmployee.setHeight(Double.parseDouble(height.getText()));
            addEmployee.setWeight(Double.parseDouble(weight.getText()));
            addEmployee.setEmail(email.getText());
            addEmployee.setSssNo(sss.getText());
            addEmployee.setTinNo(tin.getText());
            addEmployee.setPagibigNo(pagibig.getText());

            employeeFacade.addEmployee(addEmployee);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Stage dashboardStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/EmployeeList.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        dashboardStage.setScene(scene);

        dashboardStage.show();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }



    @FXML
    protected void handleCancelAddEmployeetButton(ActionEvent event) {
        try {

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
            Parent root = loader.load();


            Stage loginStage = new Stage();
            Scene scene = new Scene(root);
            loginStage.setScene(scene);


            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sex.getItems().addAll("Male","Female");
        civilStatus.getItems().addAll("Single", "Married");
        positionRc.getItems().addAll("MIS", "Dean", "Discipline Officer", "Teacher", "Cashier", "Registrar");
    }


}
