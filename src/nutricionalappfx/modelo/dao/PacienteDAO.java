/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nutricionalappfx.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.spec.PSource;
import javax.management.RuntimeErrorException;
import nutricionalappfx.dominio.contrato.IPacienteRepository;
import nutricionalappfx.dominio.pojo.OperacionResultado;
import nutricionalappfx.dominio.pojo.Paciente;
import nutricionalappfx.modelo.ConexionBD;

/**
 *
 * @author andre
 */
public class PacienteDAO implements IPacienteRepository {

    @Override
    public List<Paciente> obtenerPacientesNutriologo(int idNutriologo) throws RuntimeException{
        List <Paciente> pacientes = new ArrayList<>();
        String consulta = "SELECT codigo, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, pesoInicial "
                        + "FROM Pacientes"
                        + "WHERE idNutriologo = ?";
        
        try(Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement ps = conexion.prepareStatement(consulta)){
            ps.setInt(1, idNutriologo);
            try(ResultSet resultado = ps.executeQuery()){
                while(resultado.next()){
                    Paciente paciente = new Paciente();
                    paciente.setCodigo(resultado.getString("codigo"));
                    paciente.setNombre(resultado.getString("nombre"));
                    paciente.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                    paciente.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                    paciente.setFechaNacimiento(resultado.getString("fechaNacimiento"));
                    paciente.setPesoInicial(resultado.getFloat("pesoInicial"));
                    pacientes.add(paciente);
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException("Error de conexion con el almacenamiento");
        }
        return pacientes;
    }

    @Override
    public OperacionResultado registrarPaciente(Paciente paciente) throws RuntimeException {
        OperacionResultado resultado  = new OperacionResultado();
        String sentencia = "INSERT INTO Pacientes (codigo, nombre, apellidoPaterno, fechaNacimiento, altura, pesoInicial, sexo, telefono, correo, idNutriologo) VALUES"
                            + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement ps = conexion.prepareStatement(sentencia)) {
                ps.setString(1, paciente.getCodigo());
                ps.setString(2, paciente.getNombre());
                ps.setString(3, paciente.getApellidoPaterno());
                ps.setString(4, paciente.getApellidoMaterno());
                ps.setString(5, paciente.getFechaNacimiento());
                ps.setInt(6, paciente.getAltura());
                ps.setFloat(7, paciente.getPesoInicial());
                ps.setString(8, "" + paciente.getSexo());
                ps.setString(9, paciente.getTelefono());
                ps.setString(10, paciente.getCorreo());
                ps.setInt(11, paciente.getIdNutriologo());
                int filasAfectadas = ps.executeUpdate();
                if(filasAfectadas > 0){
                    resultado.setError(false);
                    resultado.setMensaje("Informacion del pacienmte guardada correctamente");
                }else{
                    resultado.setError(true);
                    resultado.setMensaje("Lo sentimos la informacion no pudo ser registrada, verifique la informacion e intentelo nuevamente");
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error en el almacenamiento.");
        }
        return null;
    }

    @Override
    public OperacionResultado editarPaciente(Paciente paciente) throws RuntimeException {
        
        return null; 
    }

    @Override
    public OperacionResultado eliminarPaciente(int idPaciente)  throws RuntimeException{
        
        return null;
    }

    @Override
    public boolean verificarExistenciaCodigoPaciente(String codigo) throws RuntimeException {
       return true;
    }
    
}
