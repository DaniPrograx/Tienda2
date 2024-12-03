/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DataBase.DatabaseConnection;
import Model.Cliente.Cliente;
import Model.Cliente.ClienteDAO;
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
    } catch (RuntimeException ex) { // Cambia SQLException por RuntimeException
        this.dao = null; // Inicialización segura en caso de error
        view.showError("Error al conectar con la Base de Datos: " + ex.getMessage());
        
        
    }
}



    public void create(Cliente cliente) {
        if (cliente == null || !validateRequired(cliente)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (!validatePK(cliente.getCedula())) {
                view.showError("La cedula ingresada ya se encuentra registrada");
                return;
            }
            dao.Agregar(mapper.toDTO(cliente));
            view.showMessage("Datos guardados correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al guardar los datos: " + ex.getMessage());
        }
    }

    public void read(Cliente cliente) {
        if (cliente == null || !validateRequired(cliente)) {
            view.showError("La cédula no puede estar vacía");
            return;
        }
        try {
            if (!validatePK(cliente.getCedula())) {
                view.showError("La cedula ingresada ya se encuentra registrada");
                return;
            }
            dao.read(mapper.toDTO(cliente));
            view.showMessage("Datos del cliente");
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al guardar los datos: " + ex.getMessage());

        }
    }
       
 public void update(Cliente cliente) {
        // Validación inicial del objeto cliente
        if (cliente == null || !validateRequired(cliente)) {
            view.showError("Faltan datos requeridos");
            return;
        }

        try {
            // Verifica si la cédula está registrada
            if (validatePK(cliente.getCedula())) {
                // Si no está registrada, mostramos error
                view.showError("La cédula ingresada no se encuentra registrada");
                return;
            }

            // Actualiza los datos si la cédula está registrada
            dao.update(mapper.toDTO(cliente));
            view.showMessage("Datos actualizados correctamente");
        } catch (SQLException ex) {
            // Manejo de excepciones de base de datos
            view.showError("Ocurrió un error al actualizar los datos: " + ex.getMessage());
        }
    }

    public void delete(Cliente cliente) {
        if (cliente == null || !validateRequired(cliente)) {
            view.showError("No hay ningun cliente cargado actualmente");
            return;
        }
        try {
            if (validatePK(cliente.getCedula())) {
                view.showError("La cedula ingresada no ya se encuentra registrada");
                return;
            }
            dao.delete(cliente.getCedula());
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al eliminar los datos: " + ex.getMessage());
        }
    }

    public boolean validateRequired(Cliente cliente) {
        return !cliente.getCedula().trim().isEmpty()
                && !cliente.getNombreCompleto().trim().isEmpty()
                && !cliente.getDireccion().trim().isEmpty();
          

    }

    public boolean validatePK(String cedula) {
        try {
            return dao.validatePK(cedula);
        } catch (SQLException ex) {
            return false;
        }
    }
}


