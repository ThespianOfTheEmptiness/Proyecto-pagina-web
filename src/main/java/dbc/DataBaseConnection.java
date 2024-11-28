/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author samue
 */
public class DataBaseConnection {
    private static final String URL_DB = "jdbc:mariadb://localhost:3306/likes";
  private static final String USER_DB = "root";
  private static final String PASSWORD_DB = "Samuel1311";

  // Conexión a la base de datos
  public static Connection getConnection() {
    Connection conexion = null;
    try {
      //Importante esta línea para que el driver sepa que se va a conectar a una base de datos MariaDB
      Class.forName("org.mariadb.jdbc.Driver");
      System.out.println("Conectando a la base de datos...");
      // Le pasamos la URL de la base de datos, el usuario y la contraseña para
      // conectarnos a la base de datos
      conexion = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
      System.out.println(conexion);
    } catch (ClassNotFoundException e) {
      System.out.println("Error: MariaDB JDBC Driver no encontrado.");
    } catch (SQLException e) {
      System.out.println("Error al conectar a la base de datos: " + e.getMessage());
      e.printStackTrace();
    }
    return conexion;
  }
}
