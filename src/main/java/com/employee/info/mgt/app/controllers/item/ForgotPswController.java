package com.employee.info.mgt.app.controllers.item;

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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ForgotPswController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField show_newPsw;

    @FXML
    private TextField nicknameField;

    @FXML
    private PasswordField newPswField;

    @FXML
    private Button saveForgotPswButton;

    @FXML
    private ToggleButton toggle_button7;

    @FXML
    private ToggleButton toggle_button8;

    private User user;
    private UserFacade userFacade;

    private UserDao userDao;

    public ForgotPswController() {
        userDao = new UserDaoImpl();

        userFacade = new UserFacadeImpl(userDao);
    }


    @FXML
    protected void saveForgotPswClicked(ActionEvent event) {
        User forgotPsw = new User();
        forgotPsw.setUsername(usernameField.getText());
        forgotPsw.setPassword(newPswField.getText());

        String username = usernameField.getText();
        String nickname = nicknameField.getText();
        String newPassword  = newPswField.getText();

        try {
            userFacade.forgotPassword(username, nickname, newPassword);
        } catch(Exception ex) {
            ex.printStackTrace();;
        }
        finally {
            try {
                Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                previousStage.close();

                Stage dashboardStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/MainView.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                dashboardStage.setScene(scene);
                dashboardStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    protected void handleCancelForgotPsw(MouseEvent event) {
        try {
            Stage previousStage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            previousStage2.close();

            Stage dashboardStage2 = new Stage();
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("/views/MainView.fxml"));
            Parent root2 = loader2.load();
            Scene scene2 = new Scene(root2);
            dashboardStage2.setScene(scene2);
            dashboardStage2.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changeVisibility7(ActionEvent event){
        if (toggle_button8.isSelected()){
            show_newPsw.setText(newPswField.getText());
            show_newPsw.setVisible(true);
            newPswField.setVisible(false);
            toggle_button8.setVisible(false);
            return;
        }
        newPswField.setText(show_newPsw.getText());
        newPswField.setVisible(true);
        show_newPsw.setVisible(false);
        toggle_button8.setVisible(true);
    }

    @FXML
    void changeVisibility8(ActionEvent event){
        if (toggle_button7.isSelected()){
            newPswField.setText(show_newPsw.getText());
            newPswField.setVisible(true);
            show_newPsw.setVisible(false);
            toggle_button8.setVisible(true);
            return;
        }
        show_newPsw.setText(newPswField.getText());
        show_newPsw.setVisible(true);
        newPswField.setVisible(false);
        toggle_button8.setVisible(false);
    }

}
