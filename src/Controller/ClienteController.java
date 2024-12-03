/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DataBase.DatabaseConnection;
import Model.Cliente.Cliente;
import Model.Cliente.ClienteDAO;
import Model.Cliente.ClienteDTO;
import Model.Cliente.ClienteMapper;
import Vista.View;
import java.sql.SQLException;

/**
 *
 * @author Pablo
 */
public class ClienteController {
   private ClienteDAO dao;
    private final View view;
    private final ClienteMapper mapper;

    public ClienteController(View view) {
        this.view = view;
        this.mapper = new ClienteMapper();
        try {
            this.dao = new ClienteDAO(DatabaseConnection.getConnection());
        } catch (RuntimeException ex) { 
            this.dao = null; 
            view.showError("Error al conectar con la Base de Datos: " + ex.getMessage());
        }
    }

    // Método para manejar la creación de un cliente en un hilo
    public void create(Cliente cliente) {
        if (cliente == null || !validateRequired(cliente)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        
        new Thread(() -> {
            try {
                if (!validatePK(cliente.getCedula())) {
                    view.showError("La cédula ingresada ya se encuentra registrada");
                    return;
                }
                boolean success = dao.Agregar(mapper.toDTO(cliente));
                if (success) {
                    view.showMessage("Datos guardados correctamente");
                } else {
                    view.showError("Error al guardar los datos");
                }
            } catch (SQLException ex) {
                view.showError("Ocurrió un error al guardar los datos: " + ex.getMessage());
            }
        }).start();
    }

    // Método para manejar la lectura de un cliente en un hilo
    public void read(Cliente cliente) {
        if (cliente == null || !validateRequired(cliente)) {
            view.showError("La cédula no puede estar vacía");
            return;
        }
        
        new Thread(() -> {
            try {
                ClienteDTO clienteDTO = dao.read(cliente.getCedula());
                if (clienteDTO != null) {
                    view.showMessage("Datos del cliente: " + clienteDTO.toString());
                } else {
                    view.showError("Cliente no encontrado");
                }
            } catch (SQLException ex) {
                view.showError("Ocurrió un error al leer los datos: " + ex.getMessage());
            }
        }).start();
    }

    // Método para manejar la actualización de un cliente en un hilo
    public void update(Cliente cliente) {
        if (cliente == null || !validateRequired(cliente)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        
        new Thread(() -> {
            try {
                if (validatePK(cliente.getCedula())) {
                    view.showError("La cédula ingresada no se encuentra registrada");
                    return;
                }

                boolean success = dao.update(mapper.toDTO(cliente));
                if (success) {
                    view.showMessage("Datos actualizados correctamente");
                } else {
                    view.showError("Error al actualizar los datos");
                }
            } catch (SQLException ex) {
                view.showError("Ocurrió un error al actualizar los datos: " + ex.getMessage());
            }
        }).start();
    }

    // Método para manejar la eliminación de un cliente en un hilo
    public void delete(Cliente cliente) {
        if (cliente == null || !validateRequired(cliente)) {
            view.showError("No hay ningun cliente cargado actualmente");
            return;
        }

        new Thread(() -> {
            try {
                if (validatePK(cliente.getCedula())) {
                    view.showError("La cédula ingresada no se encuentra registrada");
                    return;
                }
                boolean success = dao.delete(cliente.getCedula());
                if (success) {
                    view.showMessage("Cliente eliminado correctamente");
                } else {
                    view.showError("Error al eliminar los datos");
                }
            } catch (SQLException ex) {
                view.showError("Ocurrió un error al eliminar los datos: " + ex.getMessage());
            }
        }).start();
    }

    // Validación de campos requeridos
    public boolean validateRequired(Cliente cliente) {
        return !cliente.getCedula().trim().isEmpty()
                && !cliente.getNombreCompleto().trim().isEmpty()
                && !cliente.getDireccion().trim().isEmpty();
    }

    // Validación de clave primaria (cedula)
    public boolean validatePK(String cedula) {
        try {
            return dao.validatePK(cedula);
        } catch (SQLException ex) {
            return false;
        }
    }
}


