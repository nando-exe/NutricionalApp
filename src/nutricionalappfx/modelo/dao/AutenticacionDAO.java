package nutricionalappfx.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import nutricionalappfx.dominio.contrato.IAutenticacionRepository;
import nutricionalappfx.dominio.pojo.Colaborador;
import nutricionalappfx.dominio.pojo.ColaboradorEstatus;
import nutricionalappfx.modelo.ConexionBD;

/**
 *
 * @author andre
 */
public class AutenticacionDAO implements  IAutenticacionRepository{

    @Override
    public int verificarCredenciales(String noPersonal, String contrasenia) throws RuntimeException {
        int idColaborador = 0;
        String consulta = "SELECT idColaborador FROM Colaboradores WHERE noPersonal = ? AND password = ?";
        try(Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement ps = conexion.prepareStatement(consulta)){
            ps.setString(1, noPersonal);
            ps.setString(2, contrasenia);
            ResultSet resultado = ps.executeQuery();
            if (resultado.next()) {
               idColaborador = resultado.getInt("idColaborador");
            }else{
                return -1;
            }
            resultado.close();
            return idColaborador;
           
        }catch(SQLException ex){
            throw new RuntimeException("Error de conexion con el almacenamiento");
        }
    }

    @Override
    public ColaboradorEstatus verificarEstatusColaborador(int idColaborador) {
        ColaboradorEstatus estatus = new ColaboradorEstatus();
        String consulta = "SELECT Colaboradores.idEstatus, ColaboradoresEstatus.estatus " +
                            "FROM Colaboradores " +
                            "INNER JOIN ColaboradoresEstatus ON ColaboradoresEstatus.idEstatus = Colaboradores.idEstatus " +
                            "WHERE idColaborador = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(consulta)){
            ps.setInt(1, idColaborador);
            try(ResultSet resultado = ps.executeQuery()){
                if(resultado.next()){
                    estatus.setIdEstatus(resultado.getInt("idEstatus"));
                    estatus.setEstatus(resultado.getString("estatus"));
                    return estatus;
                }else{
                    throw new RuntimeException("Error en la integridad de los datos");
                }
            }
        }catch (SQLException ex){
            throw new RuntimeException("Error de conexion con el almacenamiento");
        }
    }

    @Override
    public Colaborador obtenerInformacionColaborador(int idColaborador) {
        Colaborador colaborador = new Colaborador();
        String consulta = "SELECT idColaborador, noPersonal, nombre, apellidoPaterno, apellidoMaterno, " +
                            "fechaContratacion, Colaboradores.idRol, Roles.rol, Colaboradores.idEstatus, ColaboradoresEstatus.estatus " +
                            "FROM Colaboradores " +
                            "INNER JOIN Roles ON Roles.idRol = Colaboradores.idRol " +
                            "INNER JOIN ColaboradoresEstatus ON ColaboradoresEstatus.idEstatus = Colaboradores.idEstatus " +
                            "WHERE idColaborador = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(consulta)){
            ps.setInt(1, idColaborador);
            try(ResultSet resultado = ps.executeQuery()){
                if(resultado.next()){
                    colaborador.setIdColaborador(resultado.getInt("idColaborador"));
                    colaborador.setNoPersonal(resultado.getString("noPersonal"));
                    colaborador.setNombre(resultado.getString("nombre"));
                    colaborador.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                    colaborador.setApellidoMaterno(
                        (resultado.getString("apellidoMaterno")) != null ? resultado.getString("apellidoMaterno") : "");
                    colaborador.setFechaContratacion(resultado.getString("fechaContratacion"));
                    colaborador.setIdRol(resultado.getInt("idRol"));
                    colaborador.setRol(resultado.getString("rol"));
                    colaborador.setIdEstatus(resultado.getInt("idEstatus"));
                    colaborador.setEstatus(resultado.getString("estatus"));
                    return colaborador;
                }else{
                    throw new RuntimeException("Error en la integridad de los datos");
                }
            }
        }catch (SQLException ex){
            throw new RuntimeException("Error de conexion con el almacenamiento");
        }
    }
    
}