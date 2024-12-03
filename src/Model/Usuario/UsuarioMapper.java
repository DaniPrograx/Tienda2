/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Usuario;

import Model.Mapper.Mapper;
import java.sql.SQLException;

/**
 *
 * @author Pablo
 */
public class UsuarioMapper implements  Mapper<Usuario, UsuarioDTO> {

    @Override
    public UsuarioDTO toDTO(Usuario ent) {
if (ent == null) {
            return null;
        }

        return new UsuarioDTO(
                ent.getUsername(),
                ent.getPassword(),
                ent.getRol()
               );      }

    @Override
    public Usuario toEnt(UsuarioDTO dto) throws SQLException {
if (dto==null ) return null;
            
       
        return new Usuario(
        dto.getUsername(),
       dto .getPassword(),
        dto.getRol()
        
       
        );
                
    }            
    
}
