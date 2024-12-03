/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Producto;

/**
 *
 * @author sibaj
 */
public class ProductoDTO {
    private int id;
    private String nombre;
    private String categoria;
    private double precio;
    private int cantidadDisponible;
    private String proveedor;

    public ProductoDTO(int id, String nombre, String categoria, double precio, int cantidadDisponible, String proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.proveedor = proveedor;
    }

    public int getId() {
        return id;
    }

  

   

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public String getProveedor() {
        return proveedor;
    }
    

}
