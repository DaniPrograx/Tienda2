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
public class Venta {
    private Date fecha;
    private String cliente;
    private List<Producto> productosVendidos;
    private double subtotal;
    private double impuesto;
    private double total;

    // Constructor
    public Venta(Date fecha, String cliente, List<Producto> productosVendidos, double subtotal, double impuesto, double total) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.productosVendidos = productosVendidos;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
    }

    // Getters y setters
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(List<Producto> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
