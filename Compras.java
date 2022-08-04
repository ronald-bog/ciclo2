import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Compras {
    public static void ingresarCompras() throws SQLException {
        String sqlComp = "INSERT INTO compras VALUES (?, ?, ?, ?)";
        PreparedStatement senComp = Principal.conectar().prepareStatement(sqlComp);
        System.out.print("ID: ");
        int id = Principal.sc.nextInt();
        senComp.setInt(1, id);
        Principal.sc.nextLine();

        System.out.print("Alias Comprador: ");
        String alias = Principal.sc.nextLine();
        senComp.setString(2, alias);

        System.out.print("Fabricante: ");
        String nombre = Principal.sc.nextLine();
        senComp.setString(3, nombre);

        System.out.print("Fecha y Hora: ");
        String fechaHora = Principal.sc.nextLine();
        senComp.setString(4, fechaHora);

        int filasIns = senComp.executeUpdate();
        if (filasIns > 0) {
            System.out.println("! Inserción exitosa !");
            System.out.println("------------------------------------");
        }
    }
}
