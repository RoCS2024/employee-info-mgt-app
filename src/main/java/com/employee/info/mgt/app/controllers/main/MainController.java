package com.employee.info.mgt.app.controllers.main;

import com.user.management.appl.facade.user.UserFacade;
import com.user.management.appl.facade.user.impl.UserFacadeImpl;
import com.user.management.appl.model.user.User;
import com.user.management.data.user.dao.UserDao;
import com.user.management.data.user.dao.impl.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainController {

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField userID;
    @FXML
    private TextField passwordShown;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ToggleButton toggleButton;

    private UserFacade userFacade;

    private UserDao userDao;

    public MainController() {
        userDao = new UserDaoImpl();

        userFacade = new UserFacadeImpl(userDao);
    }

    @FXML
    protected void logButtonOnAction(ActionEvent event) {
        String username = userID.getText();
        String password = passwordField.getCharacters().toString();
        String password2 = passwordShown.getText();

        try {
            User currentUser = userFacade.findUserByUsername(username);
            if (username.isEmpty() || password.isEmpty()) {
                showAlert("Login Failed", "Username and password are required. Please enter your username and password.", Alert.AlertType.ERROR);
            } else if (!username.matches("[a-zA-Z0-9~`!@#$%^&*()_={}|:;\"'<,>.?/]+")) {
                showAlert("Login Failed", "Username should only contain alpha-numeric characters. Please enter valid input", Alert.AlertType.ERROR);
            }

            else if (currentUser !=null) {
                if (password.equals(currentUser.getPassword()) || password2.equals(currentUser.getPassword())) {
                    showAlert("Login Successful", "Welcome " + username + "!", Alert.AlertType.INFORMATION);
                    openDashboardWindow(event);
                } else {
                    showAlert("Login Failed", "Incorrect password. Please double-check your password.", Alert.AlertType.ERROR);
                }
            }
            else {
                showAlert("Login Failed", "Username not registered. Please sign up or create an account first.", Alert.AlertType.ERROR);
            }
        } catch (Exception ex) {
            showAlert("Error", "An error occurred during login: " + ex.getMessage(), Alert.AlertType.ERROR);
            ex.printStackTrace();
        }
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void openDashboardWindow(ActionEvent event) {
        try {
            Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            previousStage.close();

            Stage dashboardStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/UserList.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);

            dashboardStage.initStyle(StageStyle.UNDECORATED);

            dashboardStage.show();
        } catch (IOException e) {
            System.err.println("Error opening dashboard window: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleForgotPsw(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ForgotPsw.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changeVisibility(ActionEvent event){
        if(toggleButton.isSelected()){
            passwordShown.setText(passwordField.getText());
            passwordShown.setVisible(true);
            passwordField.setVisible(false);
            return;
        }
        passwordField.setText(passwordShown.getText());
        passwordField.setVisible(true);
        passwordShown.setVisible(false);
    }

    public void signButtonOnAction(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/CreateAcc.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void quitApp(MouseEvent event) {
        try {
            Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            previousStage.close();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }








}
