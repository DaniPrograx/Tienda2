/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DataBase.DatabaseConnection;
import Model.Producto.Producto;
import Model.Producto.ProductoDAO;

import Model.Producto.ProductoMapper;
import Vista.View;
import java.sql.SQLException;


/**
 *
 * @author Pablo
 */
public class ProductoController {

    private ProductoDAO dao;
    private final View view;
    private final ProductoMapper mapper;

    public ProductoController(View view) {
        this.view = view;
        this.mapper = new ProductoMapper();
        try {
            this.dao = new ProductoDAO(DatabaseConnection.getConnection());
        } catch (RuntimeException ex) { // Cambia SQLException por RuntimeException
            this.dao = null; // Inicialización segura en caso de error
            view.showError("Error al conectar con la Base de Datos: " + ex.getMessage());

        }
    }

    public void create(Producto producto) {
        if (producto == null || !validateRequired(producto)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (!validatePK(producto.getId())) {
                view.showError("el Id ingresado  ya se encuentra registrado");
                return;
            }
            dao.Agregar(mapper.toDTO(producto));
            view.showMessage("Datos guardados correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al guardar los datos: " + ex.getMessage());
        }
    }

    public void read(Producto producto) {
        if (producto == null || !validateRequired(producto)) {
            view.showError("La cédula no puede estar vacía");
            return;
        }
        try {
            if (!validatePK(producto.getId())) {
                view.showError("La cedula ingresada ya se encuentra registrada");
                return;
            }
            dao.read(mapper.toDTO(producto));
            view.showMessage("Datos del producto");
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al guardar los datos: " + ex.getMessage());

        }
    }

    public void update(Producto producto) {
        if (producto == null || !validateRequired(producto)) {
            view.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (validatePK(producto.getId())) {
                view.showError("La cedula ingresada no se encuentra registrada");
                return;
            }
            dao.update(mapper.toDTO(producto));
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al actualizar los datos: " + ex.getMessage());
        }
    }

    public void delete(Producto producto) {
        if (producto == null || !validateRequired(producto)) {
            view.showError("No hay ningun cliente cargado actualmente");
            return;
        }
        try {
            if (validatePK(producto.getId())) {
                view.showError("La cedula ingresada no ya se encuentra registrada");
                return;
            }
            dao.delete(producto.getId());
        } catch (SQLException ex) {
            view.showError("Ocurrio un error al eliminar los datos: " + ex.getMessage());
        }
    }

    public boolean validateRequired(Producto producto) {
        return!Integer.toString(producto.getId()).trim().isEmpty()
                && !producto.getNombre().trim().isEmpty()
                && !producto.getCategoria().trim().isEmpty()
                && !producto.getProveedor().trim().isEmpty() ;
        
                
              
    }

    public boolean validatePK(int id) {
        try {
            return dao.validatePK(id);
        } catch (SQLException ex) {
            return false;
        }
    }

}
