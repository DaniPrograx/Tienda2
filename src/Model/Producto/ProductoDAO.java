/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Producto;


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
public class ProductoDAO extends DAO<ProductoDTO>{

    public ProductoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean Agregar(ProductoDTO dto) throws SQLException {
          
//        if (dto == null || !validatePK(dto.getCedula())) {
//            return false;
//        }
        String query = "Call ProductoAgregar(?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, dto.getId());
            stmt.setString(2, dto.getNombre());
            stmt.setString(3, dto.getCategoria());
            stmt.setDouble(4, dto.getPrecio());
            stmt.setInt(5, dto.getCantidadDisponible());
            stmt.setString(6, dto.getProveedor());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public ProductoDTO read(Object id) throws SQLException {
        String sql = "SELECT * FROM Producto WHERE Codigo = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (int) id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new ProductoDTO(
                            resultSet.getInt("codigo"),
                            resultSet.getString("nombre"),
                            resultSet.getString("Categoria"),
                            resultSet.getDouble("Precio"),
                            resultSet.getInt("CantidadDisponible"),
                            resultSet.getString("proveedor")
                            );
          }
        }
}
        return null;
    }

    @Override
    public boolean update(ProductoDTO dto) throws SQLException {
      if (dto == null) {
            return false;
        }
        String query = "Call ProductoUpdate(?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, dto.getId());
            stmt.setDouble(2, dto.getPrecio());
            stmt.setString(3, dto.getProveedor());
            stmt.setInt(4,dto.getCantidadDisponible());
            return stmt.executeUpdate() > 0;

        }
    }

    @Override
    public boolean delete(Object id) throws SQLException {
      String query = "Call ProductoDelete(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, String.valueOf(id));
            return stmt.executeUpdate() > 0;

        }
    }
    public boolean validatePK(Object id)throws SQLException{
       return read (id)==null;
    }
}
