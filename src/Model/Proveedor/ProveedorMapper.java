/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Proveedor;

import Model.Mapper.Mapper;
import java.sql.SQLException;

/**
 *
 * @author Pablo
 */
public class ProveedorMapper implements  Mapper<Proveedor, ProveedorDTO> {

    @Override
    public ProveedorDTO toDTO(Proveedor ent) {
    if (ent == null) {
            return null;
        }

        return new ProveedorDTO(
                ent.getId(),
                ent.getNombre(),
                ent.getContacto(),
                ent.getDireccion()
               );    }

    @Override
    public Proveedor toEnt(ProveedorDTO dto) throws SQLException {
if (dto==null ) return null;
            
       
        return new Proveedor(
        dto.getId(),
       dto .getNombre(),
        dto.getContacto(),
        dto.getDireccion()
       
        );
                
    }        
    
}
