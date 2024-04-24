package com.employee.info.mgt.app.controllers.dashboard;

import com.employee.info.mgmt.appl.facade.employee.impl.EmployeeFacadeImpl;
import com.employee.info.mgmt.appl.model.Employee;
import com.employee.info.mgmt.data.employee.dao.EmployeeDao;
import com.employee.info.mgmt.data.employee.dao.impl.EmployeeDaoImpl;
import com.employee.info.mgt.app.controllers.item.UpdateEmployeeController;
import com.employee.info.mgmt.appl.facade.employee.EmployeeFacade;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeListController implements Initializable {

    @FXML
    TableView table;

    private EmployeeFacade employeeFacade;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        employeeFacade = new EmployeeFacadeImpl(employeeDao);
        table.getItems().clear();

        List<Employee> employees = employeeFacade.getAllEmployees();
        table.getItems().addAll(employees);

        ObservableList<Employee> data = FXCollections.observableArrayList(employees);
        table.setItems(data);

        TableColumn employeeIdColumn = new TableColumn("Employee ID");
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeNo"));
        employeeIdColumn.getStyleClass().addAll("employee-id-column");

        TableColumn lastNameColumn = new TableColumn(" Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameColumn.getStyleClass().addAll("lastName-column");

        TableColumn firstNameColumn = new TableColumn(" First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameColumn.getStyleClass().addAll("firstName-column");

        TableColumn middleNameColumn = new TableColumn(" Middle Name");
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        middleNameColumn.getStyleClass().addAll("middleName-column");

        TableColumn actionColumn = new TableColumn("ACTION");
        actionColumn.setCellValueFactory(new PropertyValueFactory<>(" "));
        actionColumn.getStyleClass().addAll("action-column");

        Callback<TableColumn<Employee, String>, TableCell<Employee, String>> cellFactory
                = //
                new Callback<TableColumn<Employee, String>, TableCell<Employee, String>>() {

                    @Override
                    public TableCell call(final TableColumn<Employee, String> param) {
                        final TableCell<Employee, String> cell = new TableCell<Employee, String>() {
                            final Button editButton = new Button();

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    editButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/assets/editbutton.png"))));
                                    editButton.setOnAction(event -> {
                                        Employee updateEmployee = getTableView().getItems().get(getIndex());
                                        showEmployeeUpdate(updateEmployee, (ActionEvent) event);
                                    });

                                    HBox hbox = new HBox(editButton);
                                    hbox.setSpacing(10);
                                    hbox.setAlignment(Pos.BASELINE_CENTER);
                                    setGraphic(hbox);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        actionColumn.setCellFactory(cellFactory);
        table.getColumns().addAll(employeeIdColumn, lastNameColumn, firstNameColumn, middleNameColumn, actionColumn);

    }

    @FXML
    protected void handleIconUserList(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UserList.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleIconEmployeeList(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EmployeeList.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleAddEmployeeList(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddEmployee.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleIconLogout(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEmployeeUpdate(Employee updateEmployee, ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

            Stage editEmployee = new Stage();
            editEmployee.initStyle(StageStyle.UNDECORATED);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/UpdateEmployee.fxml"));
            AnchorPane employeeViewLayout = new AnchorPane();
            employeeViewLayout = loader.load();
            UpdateEmployeeController updateEmployeeController = loader.getController();
            updateEmployeeController.setEmployee(updateEmployee);
            Scene scene = new Scene(employeeViewLayout);
            editEmployee.setScene(scene);
            editEmployee.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
