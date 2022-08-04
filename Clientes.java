import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Clientes {

    public static void ingresarClientes() throws SQLException {
        String sql = "INSERT INTO clientes VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia = Principal.conectar().prepareStatement(sql);
        System.out.print("Alias: ");
        String alias = Principal.sc.nextLine();
        sentencia.setString(1, alias);

        System.out.print("Nombre: ");
        String nombre = Principal.sc.nextLine();
        sentencia.setString(2, nombre);

        System.out.print("Apellido: ");
        String apellido = Principal.sc.nextLine();
        sentencia.setString(3, apellido);

        System.out.print("e-mail: ");
        String email = Principal.sc.nextLine();
        sentencia.setString(4, email);

        System.out.print("Celular: ");
        String celular = Principal.sc.nextLine();
        sentencia.setString(5, celular);

        System.out.print("Contraseña: ");
        int contrasena = Principal.sc.nextInt();
        sentencia.setInt(6, contrasena);
        Principal.sc.nextLine();

        System.out.print("F.Nacimiento: ");
        String nacimiento = Principal.sc.nextLine();
        sentencia.setString(7, nacimiento);

        int filasIns = sentencia.executeUpdate();
        if (filasIns > 0) {
            System.out.println("* Ejecutado con exito *");
        }
    }
}
