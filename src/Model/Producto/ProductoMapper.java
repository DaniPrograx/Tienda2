/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Producto;

import Model.Mapper.Mapper;
import java.sql.SQLException;

/**
 *
 * @author Pablo
 */
public class ProductoMapper implements Mapper<Producto, ProductoDTO> {

    @Override
    public ProductoDTO toDTO(Producto ent) {
        if (ent == null) {
            return null;
        }

        return new ProductoDTO(
                ent.getId(),
                ent.getNombre(),
                ent.getCategoria(),
                ent.getPrecio(),
                ent.getCantidadDisponible(),
                ent.getProveedor());
    }

    @Override
    public Producto toEnt(ProductoDTO dto) throws SQLException {
 if (dto==null ) return null;
            
       
        return new Producto(
        dto.getId(),
       dto .getNombre(),
        dto.getCategoria(),
        dto.getPrecio(),
        dto.getCantidadDisponible(),
        dto.getProveedor()
        );
                
    }    

}
