/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Cliente;

import Model.Mapper.Mapper;
import java.sql.SQLException;

/**
 *
 * @author Pablo
 */
public class ClienteMapper implements Mapper<Cliente ,ClienteDTO >{

    @Override
    public ClienteDTO toDTO(Cliente ent) {
 if (ent==null ) return null;
            
       
        return new ClienteDTO(
        ent.getCedula(),
        ent.getNombreCompleto(),
                ent.getDireccion(),
        ent.getTelefono(),
        ent.getCorreoElectronico());
                    }

    @Override
    public Cliente toEnt(ClienteDTO dto) throws SQLException {
   if (dto==null ) return null;
            
       
        return new Cliente(
        dto.getCedula(),
       dto .getNombreCompleto(),
        dto.getDireccion(),
        dto.getTelefono(),
        dto.getCorreoElectronico()
        );
                
    }
}
    

