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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

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

    private String getInvalidInputMessage() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date selectedBirthday = null;
        try {
            selectedBirthday = dateFormat.parse(birthday.getValue().toString());
        } catch (ParseException e) {
            return "Invalid input for Birthday. Please select a valid date.";
        }
        Calendar selectedCal = Calendar.getInstance();
        selectedCal.setTime(selectedBirthday);


        Calendar seventeenYearsAgo = Calendar.getInstance();
        seventeenYearsAgo.add(Calendar.YEAR, -17);

        if (selectedCal.after(seventeenYearsAgo)) {
            return "Invalid input for Birthday. Please select a date from at least 17 years ago.";
        }
        return null;
    }

    @FXML
    protected void onAddEmployeeClicked(ActionEvent event) throws IOException {
        try{
            Map<String, String> invalidFields = getInvalidFields();
            String emailInput = email.getText();
            if (!isValidEmail(emailInput)) {
                showAlert("Invalid Email Address", " Invalid Email Address. and not accept email without “@gmail.com“.");
                return;
            }
            if (!invalidFields.isEmpty()) {
                displayError("Invalid input in the following fields:", invalidFields);
                return;
            }
            try {
                String invalidInputMessage = getInvalidInputMessage();
                if (invalidInputMessage != null) {
                    showAlert("Invalid Input", invalidInputMessage);
                    return;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

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
    private boolean isValidEmail(String email) {
        return email != null && (email.toLowerCase().endsWith("@gmail.com") || email.toLowerCase().endsWith(".com")) && email.contains("@");
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




    @FXML
    protected void handleCancelAddEmployeetButton(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.hide();
        showDashboard1();
    }

    private void showDashboard1() {
        try {
            Stage dashboardStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/EmployeeList.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
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
    private Map<String, String> getInvalidFields() {
        Map<String, String> invalidFields = new HashMap<>();


        if (!isValidInput("Email", email.getText())) {
            invalidFields.put("Email", email.getText());
        }


        return invalidFields;
    }
    private boolean isValidInput(String fieldName, String input) {
        switch (fieldName) {
            case "Email":
                return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", input);



            default:
                return false;
        }
    }
    private void displayError(String header, Map<String, String> invalidFields) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(header);
        StringBuilder content = new StringBuilder();
        invalidFields.forEach((field, value) -> content.append(field).append(": ").append(value).append("\n"));
        alert.setContentText(content.toString());
        alert.showAndWait();
    }
}



