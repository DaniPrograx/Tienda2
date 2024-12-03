/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Venta;

import Model.Producto.Producto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sibaj
 */
public class VentaDTO {
      private Date fecha;
    private String cliente;
    private List<Producto> productosVendidos;
    private double subtotal;
    private double impuesto;
    private double total;

    public VentaDTO(Date fecha, String cliente, List<Producto> productosVendidos, double subtotal, double impuesto, double total) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.productosVendidos = productosVendidos;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public List<Producto> getProductosVendidos() {
        return productosVendidos;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public double getTotal() {
        return total;
    }
    
}
