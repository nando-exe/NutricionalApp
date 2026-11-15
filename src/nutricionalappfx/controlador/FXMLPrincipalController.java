/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nutricionalappfx.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nutricionalappfx.NutricionalAppFX;
import nutricionalappfx.dominio.pojo.Colaborador;
import nutricionalappfx.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class FXMLPrincipalController implements Initializable {
    private Colaborador colaborador;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblRol;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void inicializarInformacion(Colaborador colaborador){
        this.colaborador = colaborador;
        mostrarInfoPantalla();
    }
    private void mostrarInfoPantalla(){
        if(colaborador != null){
            lblNombre.setText("Bienvenido(a)" +colaborador.toString());
            lblRol.setText("Rol: " +colaborador.getRol());
        }
    }

    @FXML
    private void handleBtnColaboradores(ActionEvent event) {
    }

    @FXML
    private void handleBtnPacientes(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(
            NutricionalAppFX.class.getResource("vista/FXMLAdminPaciente.fxml"));
            Parent vista = loader.load();
            FXMLAdminPacienteController controlador = loader.getController();
            controlador.inicializarInformacion(colaborador);
            
            Scene escena = new Scene(vista);
            Stage escenario = (Stage) lblNombre.getScene().getWindow();
            escenario.setScene(escena);
            escenario.sizeToScene();
            escenario.setTitle("Administracion de pacientes");
            escenario.show();
        }catch(IOException ex){
            ex.printStackTrace();
            Utilidades.mostrarAlertaSimple("ERROR", "No se logro mostrar la pantalla de pacientes", Alert.AlertType.ERROR);
        }

    }

    @FXML
    private void handleBtnCitas(ActionEvent event) {
    }

    @FXML
    private void handleBtnConsutas(ActionEvent event) {
    }

    @FXML
    private void handleCerrarSesion(MouseEvent event) {
        try{
            Parent vista = FXMLLoader.load(
            NutricionalAppFX.class.getResource("vista/FXMLAutenticacion.fxml"));
            Scene escena = new Scene(vista);
            Stage escenario = (Stage) lblNombre.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Inicio de sesion");
            escenario.show();
        }catch(IOException ex){
            ex.printStackTrace();
            Utilidades.mostrarAlertaSimple("ERROR", "No se pudo cerrar la sesion", Alert.AlertType.ERROR);
        }
        
    }
    
}
