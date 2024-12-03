/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DataBase.DatabaseConnection;
import Model.Usuario.Usuario;
import Model.Usuario.UsuarioDAO;
import Model.Usuario.UsuarioDTO;

import Model.Usuario.UsuarioMapper;
import Vista.View;
import java.sql.SQLException;
/**
 *
 * @author Pablo
 */
public class UsuarioController {

    private UsuarioDAO dao;
    private final View view;
    private final UsuarioMapper mapper;

    public UsuarioController(View view) {
        this.view = view;
        this.mapper = new UsuarioMapper();
        try {
            this.dao = new UsuarioDAO(DatabaseConnection.getConnection());
        } catch (RuntimeException ex) { // Cambia SQLException por RuntimeException si es necesario
            this.dao = null; // Inicialización segura en caso de error
            view.showError("Error al conectar con la Base de Datos: " + ex.getMessage());
        }
    }

    public void create(Usuario usuario) {
        if (usuario == null || !validateRequired(usuario)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (!validatePK(usuario.getUsername())) {
                view.showError("El nombre de usuario ya se encuentra registrado");
                return;
            }
            dao.Agregar(mapper.toDTO(usuario));
            view.showMessage("Usuario creado correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al crear el usuario: " + ex.getMessage());
        }
    }

    public void read(String username) {
        if (username == null || username.trim().isEmpty()) {
            view.showError("El nombre de usuario no puede estar vacío");
            return;
        }
        try {
            UsuarioDTO dto = dao.read(username);
            if (dto == null) {
                view.showError("No se encontró un usuario con el nombre especificado");
                return;
            }
            Usuario usuario = mapper.toEnt(dto);
            view.showMessage("Datos del usuario: " + usuario.getUsername());
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al buscar el usuario: " + ex.getMessage());
        }
    }

    public void update(Usuario usuario) {
        if (usuario == null || !validateRequired(usuario)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (validatePK(usuario.getUsername())) {
                view.showError("El nombre de usuario no se encuentra registrado");
                return;
            }
            dao.update(mapper.toDTO(usuario));
            view.showMessage("Usuario actualizado correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al actualizar el usuario: " + ex.getMessage());
        }
    }

    public void delete(String username) {
        if (username == null || username.trim().isEmpty()) {
            view.showError("El nombre de usuario no puede estar vacío");
            return;
        }
        try {
            if (validatePK(username)) {
                view.showError("El nombre de usuario no se encuentra registrado");
                return;
            }
            dao.delete(username);
            view.showMessage("Usuario eliminado correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al eliminar el usuario: " + ex.getMessage());
        }
    }

    private boolean validatePK(String username) {
        try {
            return dao.validatePK(username);
        } catch (SQLException ex) {
            view.showError("Error al validar el nombre de usuario: " + ex.getMessage());
            return false;
        }
    }

    private boolean validateRequired(Usuario usuario) {
        return usuario.getUsername() != null && !usuario.getUsername().trim().isEmpty()
                && usuario.getPassword() != null && !usuario.getPassword().trim().isEmpty()
                && usuario.getRol() != null && !usuario.getRol().trim().isEmpty();
    }
}

