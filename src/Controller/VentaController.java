/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DataBase.DatabaseConnection;

import Model.Venta.Venta;
import Model.Venta.VentaDAO;
import Model.Venta.VentaDTO;
import Model.Venta.VentaMapper;
import Vista.View;
import java.sql.SQLException;
/**
 *
 * @author Pablo
 */
public class VentaController {
    private VentaDAO dao;
    private final View view;
    private final VentaMapper mapper;

    public VentaController(View view) {
        this.view = view;
        this.mapper = new VentaMapper();
        try {
            this.dao = new VentaDAO(DatabaseConnection.getConnection());
        } catch (RuntimeException ex) {
            this.dao = null;
            view.showError("Error al conectar con la Base de Datos: " + ex.getMessage());
        }
    }

    // Método para manejar la creación de una venta en un hilo
    public void create(Venta venta) {
        if (venta == null || !validateRequired(venta)) {
            view.showError("Faltan datos requeridos");
            return;
        }

        new Thread(() -> {
            try {
                // Verificar si el cliente ya existe
                if (!validatePK(venta.getCliente())) {
                    view.showError("El cliente ingresado no se encuentra registrado");
                    return;
                }

                // Realizar la inserción de la venta
                boolean success = dao.Agregar(mapper.toDTO(venta));
                if (success) {
                    view.showMessage("Venta guardada correctamente");
                } else {
                    view.showError("Error al guardar la venta");
                }
            } catch (SQLException ex) {
                view.showError("Ocurrió un error al guardar la venta: " + ex.getMessage());
            }
        }).start();
    }

    // Método para manejar la lectura de una venta en un hilo
    public void read(String idVenta) {
        if (idVenta == null || idVenta.isEmpty()) {
            view.showError("La venta no puede estar vacía");
            return;
        }

        new Thread(() -> {
            try {
                VentaDTO ventaDTO = dao.read(idVenta);
                if (ventaDTO != null) {
                    view.showMessage("Datos de la venta: " + ventaDTO.toString());
                } else {
                    view.showError("Venta no encontrada");
                }
            } catch (SQLException ex) {
                view.showError("Ocurrió un error al leer los datos de la venta: " + ex.getMessage());
            }
        }).start();
    }

    // Método para manejar la actualización de una venta en un hilo
    public void update(Venta venta) {
        if (venta == null || !validateRequired(venta)) {
            view.showError("Faltan datos requeridos");
            return;
        }

        new Thread(() -> {
            try {
                // Verifica si la venta ya está registrada
                if (!validatePK(venta.getCliente())) {
                    view.showError("La venta no puede actualizarse, el cliente no está registrado");
                    return;
                }

                boolean success = dao.update(mapper.toDTO(venta));
                if (success) {
                    view.showMessage("Venta actualizada correctamente");
                } else {
                    view.showError("Error al actualizar la venta");
                }
            } catch (SQLException ex) {
                view.showError("Ocurrió un error al actualizar la venta: " + ex.getMessage());
            }
        }).start();
    }

    // Método para manejar la eliminación de una venta en un hilo
    public void delete(String idVenta) {
        if (idVenta == null || idVenta.isEmpty()) {
            view.showError("La venta no puede estar vacía");
            return;
        }

        new Thread(() -> {
            try {
                boolean success = dao.delete(idVenta);
                if (success) {
                    view.showMessage("Venta eliminada correctamente");
                } else {
                    view.showError("Error al eliminar la venta");
                }
            } catch (SQLException ex) {
                view.showError("Ocurrió un error al eliminar la venta: " + ex.getMessage());
            }
        }).start();
    }

    // Validación de campos requeridos
    private boolean validateRequired(Venta venta) {
        return !(venta.getCliente() == null || venta.getCliente().isEmpty() || 
                 venta.getProductosVendidos() == null || venta.getProductosVendidos().isEmpty());
    }

    // Validación de la clave primaria (cliente)
    private boolean validatePK(String cliente) throws SQLException {
        return dao.read(cliente) == null;  // Si no encuentra el cliente, entonces está libre
    }
}
