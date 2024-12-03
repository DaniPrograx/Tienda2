/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Usuario;


import Model.Cliente.ClienteDTO;
import Model.Dao.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sibaj
 */
public class UsuarioDAO extends DAO<UsuarioDTO>{

    public UsuarioDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean Agregar(UsuarioDTO dto) throws SQLException {
      String query = "Call UsuarioAgregar(?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, dto.getUsername());
            stmt.setString(2, dto.getPassword());
            stmt.setString(3, dto.getRol());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public UsuarioDTO read(Object id) throws SQLException {
       String sql = "SELECT * FROM Cliente WHERE cedula = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, (String) id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new UsuarioDTO(
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("rol")
                            );
          }
        }
}
        return null;
    }

    @Override
    public boolean update(UsuarioDTO dto) throws SQLException {
        if (dto == null) {
            return false;
        }
        String query = "Call usuarioUpdate(?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, dto.getUsername());
            stmt.setString(2, dto.getPassword());
            stmt.setString(3, dto.getRol());
            return stmt.executeUpdate() > 0;

        }
    }

    @Override
    public boolean delete(Object id) throws SQLException {
        String query = "Call usuarioDelete(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, String.valueOf(id));
            return stmt.executeUpdate() > 0;

        }
    }
     public boolean validatePK(Object id)throws SQLException{
       return read (id)==null;
    }
    }
    

