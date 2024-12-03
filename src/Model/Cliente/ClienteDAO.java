/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Cliente;

import Model.Dao.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author sibaj
 */
public class ClienteDAO extends DAO<ClienteDTO>{

    public ClienteDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean Agregar(ClienteDTO dto) throws SQLException {
      
        if (dto == null || !validatePK(dto.getCedula())) {
            return false;
        }
        String query = "Call ClienteAgregar(?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, dto.getCedula());
            stmt.setString(2, dto.getNombreCompleto());
            stmt.setString(3, dto.getDireccion());
            stmt.setString(4, dto.getTelefono());
            stmt.setString(5, dto.getCorreoElectronico());
            return stmt.executeUpdate() > 0;
        }
 
    }

    @Override
    public ClienteDTO read(Object id) throws SQLException {
    String sql = "SELECT * FROM Cliente WHERE cedula = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, (String) id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new ClienteDTO(
                            resultSet.getString("cedula"),
                            resultSet.getString("nombreCompleto"),
                            resultSet.getString("direccion"),
                            resultSet.getString("telefono"),
                            resultSet.getString("correo")
                            );
          }
        }
}
        return null;
    }


    @Override
    public boolean update(ClienteDTO dto) throws SQLException {
       if (dto == null) {
            return false;
        }
        String query = "Call ClienteUpdate(?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, dto.getDireccion());
            stmt.setString(2, dto.getTelefono());
            stmt.setString(3, dto.getCorreoElectronico());
            return stmt.executeUpdate() > 0;

        }
        
    }

    @Override
    public boolean delete(Object id) throws SQLException {
         String query = "Call ClienteDelete(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, String.valueOf(id));
            return stmt.executeUpdate() > 0;

        }
    }
public boolean validatePK(Object id)throws SQLException{
       return read (id)==null;
    }
    
}
