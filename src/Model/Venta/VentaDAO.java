/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Venta;



import Model.Dao.DAO;
import Model.Producto.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sibaj
 */
public class VentaDAO extends DAO<VentaDTO>{

    public VentaDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean Agregar(VentaDTO dto) throws SQLException {
        String sql = "insert into ventas (fecha,clienteId,subtotal,impuesto,total)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(dto.getFecha().getTime()));
            stmt.setString(2, dto.getCliente());
            stmt.setDouble(3, dto.getSubtotal());
            stmt.setDouble(4, dto.getImpuesto());
            stmt.setDouble(5, dto.getTotal());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public VentaDTO read(Object id) throws SQLException {
String sql = "SELECT * FROM venta WHERE id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, (String) id);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                
                List<Producto> productosVendidos = new ArrayList<>(); 
                // Crear y retornar el objeto VentaDTO
                return new VentaDTO(
                        resultSet.getDate("fecha"),
                        resultSet.getString("Cliente"),
                        productosVendidos, // Lista inicializada
                        resultSet.getDouble("subtotal"),
                        resultSet.getDouble("impuesto"),
                        resultSet.getDouble("total")
                );
            }
        }
    }
return null;
    }

    @Override
    public boolean update(VentaDTO dto) throws SQLException {
        
        String sql = "update VEntas set fecga=?,Cliente=?,subtotal=?,impuesto=?,total=?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(dto.getFecha().getTime()));
            stmt.setString(1, dto.getCliente());
            stmt.setDouble(2, dto.getSubtotal());
            stmt.setDouble(3, dto.getImpuesto());
             stmt.setDouble(3, dto.getTotal());
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
