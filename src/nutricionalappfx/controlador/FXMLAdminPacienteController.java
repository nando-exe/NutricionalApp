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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nutricionalappfx.NutricionalAppFX;
import nutricionalappfx.dominio.pojo.Colaborador;
import nutricionalappfx.dominio.pojo.Paciente;
import nutricionalappfx.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class FXMLAdminPacienteController implements Initializable {
    
    private Colaborador colaborador;
    private int idNutriologo;
    @FXML
    private TableView<Paciente> tblTablaPacientes;
    @FXML
    private TextField tfBuscar;
    
   
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colPaterno;
    @FXML
    private TableColumn colMaterno;
    @FXML
    private TableColumn colNacimiento;
    @FXML
    private TableColumn colPeso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColumnasTabla();
        //tblTablaPacientes.setItems();
    }    
    
    private void configurarColumnasTabla(){
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        colNacimiento.setCellValueFactory(new PropertyValueFactory("fechaNacimiento"));
        colPeso.setCellValueFactory(new PropertyValueFactory("pesoInicial"));
    }
    
    public void recibirInformacion(int idNutriologo){
        this.idNutriologo = idNutriologo;
    }
    
    public void inicializarInformacion(Colaborador colaborador){
        this.colaborador = colaborador;
    }

    @FXML
    private void btnBuscar(ActionEvent event) {
    }

    @FXML
    private void btnEditar(ActionEvent event) {
    }

    @FXML
    private void btnBaja(ActionEvent event) {
        
    }

    @FXML
    private void btnAlta(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
            NutricionalAppFX.class.getResource("vista/FXMLFormularioPaciente.fxml"));
            Parent vista = loader.load();
            
            Scene escena = new Scene(vista);
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Formulario de paciente");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
            Utilidades.mostrarAlertaSimple("ERROR", "No se pudo abrir el formularioss", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleBtnRegresar(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(
            NutricionalAppFX.class.getResource("vista/FXMLPrincipal.fxml"));
            Parent vista = loader.load();
            FXMLPrincipalController controlador = loader.getController();
            controlador.inicializarInformacion(colaborador);
            
            Scene escena = new Scene(vista);
            Stage escenario = (Stage) tfBuscar.getScene().getWindow();
            escenario.setScene(escena);
            escenario.setTitle("Control nutricional");
            escenario.show();
        }catch(IOException ex){
            ex.printStackTrace();
            Utilidades.mostrarAlertaSimple("Error", "No se pudo regresar a la pantalla principal", Alert.AlertType.ERROR);
        }
    }
    
}
