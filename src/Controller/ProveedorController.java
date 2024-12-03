/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DataBase.DatabaseConnection;
import Model.Proveedor.Proveedor;
import Model.Proveedor.ProveedorDAO;

import Model.Proveedor.ProveedorMapper;
import Vista.View;
import java.sql.SQLException;
/**
 *
 * @author Pablo
 */
public class ProveedorController {
     private ProveedorDAO dao;
    private final View view;
    private final ProveedorMapper mapper;

    public ProveedorController(View view) {
        this.view = view;
        this.mapper = new ProveedorMapper();
        try {
            this.dao = new ProveedorDAO(DatabaseConnection.getConnection());
        } catch (RuntimeException ex) { // Cambia SQLException por RuntimeException
            this.dao = null; // Inicialización segura en caso de error
            view.showError("Error al conectar con la Base de Datos: " + ex.getMessage());

        }
    }

    public void create(Proveedor proveedor) {
        if (proveedor == null || !validateRequired(proveedor)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (!validatePK(proveedor.getId())) {
                view.showError("el Id ingresado  ya se encuentra registrado");
                return;
            }
            dao.Agregar(mapper.toDTO(proveedor));
            view.showMessage("Datos guardados correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al guardar los datos: " + ex.getMessage());
        }
    }

    public void read(Proveedor proveedor) {
        if (proveedor == null || !validateRequired(proveedor)) {
            view.showError("La cédula no puede estar vacía");
            return;
        }
        try {
            if (!validatePK(proveedor.getId())) {
                view.showError("La cedula ingresada ya se encuentra registrada");
                return;
            }
            dao.read(mapper.toDTO(proveedor));
            view.showMessage("Datos del producto");
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al guardar los datos: " + ex.getMessage());

        }
    }

    public void update(Proveedor proveedor) {
        if (proveedor == null || !validateRequired(proveedor)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (validatePK(proveedor.getId())) {
                view.showError("La cedula ingresada no se encuentra registrada");
                return;
            }
            dao.update(mapper.toDTO(proveedor));
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al actualizar los datos: " + ex.getMessage());
        }
    }

    public void delete(Proveedor proveedor) {
        if (proveedor == null || !validateRequired(proveedor)) {
            view.showError("No hay ningun proveedor cargado actualmente");
            return;
        }
        try {
            if (validatePK(proveedor.getId())) {
                view.showError("La cedula ingresada no ya se encuentra registrada");
                return;
            }
            dao.delete(proveedor.getId());
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al eliminar los datos: " + ex.getMessage());
        }
    }

    public boolean validateRequired(Proveedor proveedor) {
        return!Integer.toString(proveedor.getId()).trim().isEmpty()
                && !proveedor.getNombre().trim().isEmpty()
                && !proveedor.getContacto().trim().isEmpty()
                && !proveedor.getContacto().trim().isEmpty() ;
        
                
              
    }

    public boolean validatePK(int id) {
        try {
            return dao.validatePK(id);
        } catch (SQLException ex) {
            return false;
        }
    }

}
