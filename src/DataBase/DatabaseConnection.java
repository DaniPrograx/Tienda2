/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author sibaj
 */
public class DatabaseConnection {
    
    // Configuración de la conexión
    private static final String URL = "jdbc:mysql://localhost:3306/tienda"; // Cambia "tienda" por el nombre de tu base de datos
    private static final String USER = "root"; // Cambia "root" por el usuario de tu base de datos
    private static final String PASSWORD = ""; // Cambia por la contraseña de tu base de datos
    private static Connection connection = null;

    // Método para obtener la conexión
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Registrar el driver JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Crear conexión
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos.");
            } catch (ClassNotFoundException e) {
                System.err.println("Error: No se encontró el driver JDBC.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Error al conectar a la base de datos: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Método para cerrar la conexión
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada exitosamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
