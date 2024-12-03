/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Venta;

import Model.Mapper.Mapper;
import java.sql.SQLException;

/**
 *
 * @author Pablo
 */
public class VentaMapper implements  Mapper<Venta, VentaDTO> {

    @Override
    public VentaDTO toDTO(Venta ent) {
if (ent == null) {
            return null;
        }

        return new VentaDTO(
                ent.getFecha(),
                ent.getCliente(),
                ent.getProductosVendidos(),
                ent.getSubtotal(),
                ent.getImpuesto(),
                ent.getTotal()
               );     }

    @Override
    public Venta toEnt(VentaDTO dto) throws SQLException {
if (dto==null ) return null;
            
       
        return new Venta(
        dto.getFecha(),
       dto .getCliente(),
        dto.getProductosVendidos(),
        dto.getSubtotal(),
        dto.getImpuesto(),
        dto.getTotal()
        
       
        );
                
    }     
}
    

