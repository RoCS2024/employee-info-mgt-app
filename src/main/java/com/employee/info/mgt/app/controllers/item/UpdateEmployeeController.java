package com.employee.info.mgt.app.controllers.item;

import com.employee.info.mgmt.appl.facade.employee.EmployeeFacade;
import com.employee.info.mgmt.appl.facade.employee.impl.EmployeeFacadeImpl;
import com.employee.info.mgmt.appl.model.Employee;
import com.employee.info.mgmt.data.employee.dao.EmployeeDao;
import com.employee.info.mgmt.data.employee.dao.impl.EmployeeDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UpdateEmployeeController {

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

    private Stage stage;

    @FXML
    private Button employeeUpdateButton;

    private Employee employee;

    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    private EmployeeFacade employeeFacade = new EmployeeFacadeImpl(employeeDao);

    public void setEmployee(Employee employee) {
        this.employee = employee;
        if (employee != null) {
            employeeId.setText(employee.getEmployeeNo());
            positionRc.setValue(employee.getPositionInRC());
            lastName.setText(employee.getLastName());
            firstName.setText(employee.getFirstName());
            middleName.setText(employee.getMiddleName());
            sex.setValue(employee.getSex());
            birthplace.setText(employee.getBirthplace());
            civilStatus.setValue(employee.getCivilStatus());
            citizenship.setText(employee.getCitizenship());
            religion.setText(employee.getReligion());

            Double employeeHeight = employee.getHeight();
            if (employeeHeight != null) {
                height.setText(String.valueOf(employeeHeight));
            } else {
                height.setText("");
            }

            Double employeeWeight = employee.getWeight();
            if (employeeWeight != null) {
                weight.setText(String.valueOf(employeeWeight));
            } else {
                weight.setText("");
            }

            email.setText(employee.getEmail());
            sss.setText(employee.getSssNo());
            tin.setText(employee.getTinNo());
            pagibig.setText(employee.getPagibigNo());

            if (employee.getBirthdate() != null) {
                birthday.setValue(employee.getBirthdate().toLocalDateTime().toLocalDate());
            } else {
                birthday.setValue(null);
            }

            if (employee.getDateEmployed() != null) {
                dateEmployed.setValue(employee.getDateEmployed().toLocalDateTime().toLocalDate());
            } else {
                dateEmployed.setValue(null);
            }
        }
    }




    @FXML
    protected void handleCancelUpdateViewButton(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.hide();
        showDashboard();
    }

    private void showDashboard() {
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

    @FXML
    protected void onUpdateEmployeeClicked(ActionEvent event) {
        Employee updateEmployee = new Employee();
        updateEmployee.setEmployeeNo(employeeId.getText());
        updateEmployee.setPositionInRC((String) positionRc.getValue());
        updateEmployee.setLastName(lastName.getText());
        updateEmployee.setFirstName(firstName.getText());
        updateEmployee.setMiddleName(middleName.getText());
        updateEmployee.setSex((String) sex.getValue());
        updateEmployee.setBirthplace(birthplace.getText());
        updateEmployee.setCivilStatus((String) civilStatus.getValue());
        updateEmployee.setCitizenship(citizenship.getText());
        updateEmployee.setReligion(religion.getText());

        try {
            updateEmployee.setHeight(Double.parseDouble(height.getText()));
        } catch (NumberFormatException e) {
            System.err.println("Invalid height format: " + height.getText());
            e.printStackTrace();
            return;
        }

        try {
            updateEmployee.setWeight(Double.parseDouble(weight.getText()));
        } catch (NumberFormatException e) {
            System.err.println("Invalid weight format: " + weight.getText());
            e.printStackTrace();
            return;
        }

        updateEmployee.setEmail(email.getText());
        updateEmployee.setSssNo(sss.getText());
        updateEmployee.setTinNo(tin.getText());
        updateEmployee.setPagibigNo(pagibig.getText());

        LocalDate selectedDate = birthday.getValue();
        if (selectedDate != null) {
            LocalDateTime localDateTime = selectedDate.atStartOfDay();
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            updateEmployee.setBirthdate(timestamp);
        }

        LocalDate selectedDateEmployed = dateEmployed.getValue();
        if (selectedDateEmployed != null) {
            LocalDateTime localDateTimeEmployed = selectedDateEmployed.atStartOfDay();
            Timestamp employedTimestamp = Timestamp.valueOf(localDateTimeEmployed);
            updateEmployee.setDateEmployed(employedTimestamp);
        }

        try {
            employeeFacade.updateEmployee(updateEmployee);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            Stage dashboardStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EmployeeList.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}











