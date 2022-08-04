import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Bicicletas {
    public static void ingresarBicicletas() throws SQLException {
        String sqlBici = "INSERT INTO bicicletas VALUES (?, ?, ?, ?)";
        PreparedStatement senBici = Principal.conectar().prepareStatement(sqlBici);
        System.out.print("ID: ");
        int id = Principal.sc.nextInt();
        senBici.setInt(1, id);
        Principal.sc.nextLine();

        System.out.print("Fabricante: ");
        String nombre = Principal.sc.nextLine();
        senBici.setString(2, nombre);

        System.out.print("Precio: ");
        int precio = Principal.sc.nextInt();
        senBici.setInt(3, precio);
        Principal.sc.nextLine();

        System.out.print("Año: ");
        int year = Principal.sc.nextInt();
        senBici.setInt(4, year);
        Principal.sc.nextLine();

        int filasIns = senBici.executeUpdate();
        if (filasIns > 0) {
            System.out.println("! Inserción exitosa !");
            System.out.println("------------------------------------");
        }
    }
}
