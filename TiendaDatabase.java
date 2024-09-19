import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TiendaDatabase {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "usuario", "password");

            // Crear una declaración para ejecutar las consultas SQL
            statement = connection.createStatement();

            // Crear la tabla de Usuario
            String usuarioTable = "CREATE TABLE IF NOT EXISTS Usuario (" +
                                  "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                  "nombre VARCHAR(50), " +
                                  "email VARCHAR(100) UNIQUE, " +
                                  "password VARCHAR(255), " +
                                  "telefono VARCHAR(15), " +
                                  "direccion VARCHAR(255))";
            statement.execute(usuarioTable);

            // Crear la tabla de Producto
            String productoTable = "CREATE TABLE IF NOT EXISTS Producto (" +
                                   "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                   "nombre VARCHAR(100), " +
                                   "descripcion TEXT, " +
                                   "precio DECIMAL(10, 2), " +
                                   "stock INT)";
            statement.execute(productoTable);

            // Crear la tabla de Menu (opciones de menú de la tienda)
            String menuTable = "CREATE TABLE IF NOT EXISTS Menu (" +
                               "id INT AUTO_INCREMENT PRIMARY KEY, " +
                               "nombre VARCHAR(50), " +
                               "url VARCHAR(255))";
            statement.execute(menuTable);

            // Crear la tabla de Compras (registro de compras de usuarios)
            String comprasTable = "CREATE TABLE IF NOT EXISTS Compras (" +
                                  "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                  "id_usuario INT, " +
                                  "id_producto INT, " +
                                  "cantidad INT, " +
                                  "total DECIMAL(10, 2), " +
                                  "fecha DATETIME, " +
                                  "FOREIGN KEY (id_usuario) REFERENCES Usuario(id), " +
                                  "FOREIGN KEY (id_producto) REFERENCES Producto(id))";
            statement.execute(comprasTable);

            // Crear la tabla de Métodos de Pago
            String metodoPagoTable = "CREATE TABLE IF NOT EXISTS MetodoPago (" +
                                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                     "nombre VARCHAR(50), " +
                                     "descripcion VARCHAR(255))";
            statement.execute(metodoPagoTable);

            // Crear la tabla de Atención al Cliente
            String atencionClienteTable = "CREATE TABLE IF NOT EXISTS AtencionCliente (" +
                                          "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                          "id_usuario INT, " +
                                          "mensaje TEXT, " +
                                          "fecha DATETIME, " +
                                          "respuesta TEXT, " +
                                          "FOREIGN KEY (id_usuario) REFERENCES Usuario(id))";
            statement.execute(atencionClienteTable);

            // Crear la tabla de Registro (historial de registros de usuarios)
            String registroTable = "CREATE TABLE IF NOT EXISTS Registro (" +
                                   "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                   "id_usuario INT, " +
                                   "fecha_registro DATETIME, " +
                                   "FOREIGN KEY (id_usuario) REFERENCES Usuario(id))";
            statement.execute(registroTable);

            // Crear la tabla de Login (historial de inicios de sesión)
            String loginTable = "CREATE TABLE IF NOT EXISTS Login (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                "id_usuario INT, " +
                                "fecha_login DATETIME, " +
                                "FOREIGN KEY (id_usuario) REFERENCES Usuario(id))";
            statement.execute(loginTable);

            System.out.println("Tablas creadas con éxito");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
