/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Proveedor;

import Model.Cliente.ClienteDTO;
import Model.Dao.DAO;
import Model.Producto.ProductoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sibaj
 */
public class ProveedorDAO extends DAO<ProveedorDTO>{

    public ProveedorDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean Agregar(ProveedorDTO dto) throws SQLException {
        String query = "Call ProveedorAgregar(?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, dto.getId());
            stmt.setString(2, dto.getNombre());
            stmt.setString(3, dto.getContacto());
            stmt.setString(4, dto.getDireccion());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public ProveedorDTO read(Object id) throws SQLException {
        String sql = "SELECT * FROM Proveedor WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, (String) id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new ProveedorDTO(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"),
                            resultSet.getString("comtacto"),
                            resultSet.getString("direccion")    
                            );
          }
        }
}
        return null;
    }

    @Override
    public boolean update(ProveedorDTO dto) throws SQLException {
       if (dto == null) {
            return false;
        }
        String query = "Call ProveedorUpdate(?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, dto.getId());
            stmt.setString(2, dto.getNombre());
            stmt.setString(3, dto.getContacto());
            stmt.setString(4,dto.getDireccion());
            return stmt.executeUpdate() > 0;

        }
    }

    @Override
    public boolean delete(Object id) throws SQLException {
           String query = "Call ProveedorDelete(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, String.valueOf(id));
            return stmt.executeUpdate() > 0;

        }
    }
     public boolean validatePK(Object id)throws SQLException{
       return read (id)==null;
    }
}
