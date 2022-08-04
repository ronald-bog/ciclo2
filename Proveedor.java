import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Proveedor {
    public static void ingresarProveedor() throws SQLException {
        String sqlProv = "INSERT INTO proveedor VALUES (?, ?, ?, ?)";
        PreparedStatement senProv = Principal.conectar().prepareStatement(sqlProv);
        System.out.print("ID: ");
        int id = Principal.sc.nextInt();
        senProv.setInt(1, id);
        Principal.sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = Principal.sc.nextLine();
        senProv.setString(2, nombre);

        System.out.print("Dirección: ");
        String direccion = Principal.sc.nextLine();
        senProv.setString(3, direccion);

        System.out.print("Telefono: ");
        String telefono = Principal.sc.nextLine();
        senProv.setString(4, telefono);

        int filasIns = senProv.executeUpdate();
        if (filasIns > 0) {
            System.out.println("! Inserción exitosa !");
            System.out.println("------------------------------------");
        }
    }
}
