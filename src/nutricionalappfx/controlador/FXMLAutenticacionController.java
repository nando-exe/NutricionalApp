/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nutricionalappfx.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class FXMLAutenticacionController implements Initializable {

    @FXML
    private Label lblUsuario;
    @FXML
    private TextField tftUsuario;
    @FXML
    private Label lblPassword;
    @FXML
    private PasswordField tftPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleBtnIngresar(ActionEvent event) {
        
    }
    
    private void irPantallaPrincipal(){
        //TODO parent vista = new Parent
    }
}
