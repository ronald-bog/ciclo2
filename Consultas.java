import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Consultas {
    public static void crearTablas(){
        try{
            String[] sqls = {
                    "CREATE TABLE proveedor (prov_id int PRIMARY KEY,prov_nombre char(20),prov_direccion char(45),prov_telefono char(20))",
                    "CREATE TABLE clientes (alias char(20) PRIMARY KEY,nombre char(20),apellidos char(20),email varchar(45),celular char(20) ,contraseña int ,f_nacimiento date)",
                    "CREATE TABLE fabricantes (  fabricante char(20) PRIMARY KEY )",
                    "CREATE TABLE bicicletas (  id int PRIMARY KEY,  fabricante char(20) ,  precio int,  año int,  FOREIGN KEY (fabricante) REFERENCES fabricantes (fabricante) )",
                    "CREATE TABLE motocicletas (  id int PRIMARY KEY,  fabricante char(20) ,  precio int,  autonomia int,  id_prov  int,  FOREIGN KEY (fabricante) REFERENCES fabricantes (fabricante),  FOREIGN KEY (id_prov) REFERENCES proveedor (prov_id)  )",
                    "CREATE TABLE compras (  id int PRIMARY KEY,  alias char(20),  fabricante char(20) ,  fecha_hora timestamp,  FOREIGN KEY (alias) REFERENCES clientes (alias),  FOREIGN KEY (fabricante) REFERENCES fabricantes (fabricante)  )"
            };
            for (String i:sqls){
                PreparedStatement sentencia = Principal.conectar().prepareStatement(i);
                sentencia.executeUpdate();
            }
        }catch (Exception e){
            System.out.println("Tablas ya Creadas");
        }
    }

    public static void modificaYear() throws SQLException {
        String sql = "UPDATE bicicletas SET año=? WHERE fabricante=?";
        PreparedStatement sentencia = Principal.conectar().prepareStatement(sql);
        System.out.println("Por favor ingrese fabricante de la bicicleta y el año a modificar. ");
        System.out.print("Fabricante: ");
        String nombre = Principal.sc.nextLine();
        sentencia.setString(2, nombre);

        System.out.print("Año: ");
        int year = Principal.sc.nextInt();
        sentencia.setInt(1, year);
        Principal.sc.nextLine();

        int filasIns = sentencia.executeUpdate();
        if (filasIns > 0) {
            System.out.println("! Año Cambiado Correctamente !");
        }
    }
    public static void modificaTelCliente() throws SQLException {
        String sql = "UPDATE clientes SET celular=? WHERE alias=?";
        PreparedStatement sentencia = Principal.conectar().prepareStatement(sql);
        System.out.println("Por favor ingrese Alias del Cliente y el nuenvo número de celular. ");
        System.out.print("Alias: ");
        String alias = Principal.sc.nextLine();
        sentencia.setString(2, alias);

        System.out.print("Celular: ");
        String cel = Principal.sc.nextLine();
        sentencia.setString(1, cel);

        int filasIns = sentencia.executeUpdate();
        if (filasIns > 0) {
            System.out.println("! Número de Celular Cambiado Correctamente !");
         }
    }

    public static void borraCompra() throws SQLException {
        String sql = "DELETE FROM compras WHERE alias=? AND fabricante=?";
        PreparedStatement sentencia = Principal.conectar().prepareStatement(sql);
        System.out.println("Por favor ingrese Alias del Cliente y Fabricante de la intención de compra a eliminar. ");
        System.out.print("Alias: ");
        String alias = Principal.sc.nextLine();
        sentencia.setString(1, alias);

        System.out.print("Fabricante: ");
        String fab = Principal.sc.nextLine();
        sentencia.setString(2, fab);

        int filasIns = sentencia.executeUpdate();
        if (filasIns > 0) {
            System.out.println("! Intención de compra ELIMINADA Correctamente !");
        }
    }

    public static void consultaFabricantes() throws SQLException {
        String sql = "SELECT fabricante FROM fabricantes ORDER BY fabricante";
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery(sql);

        while (consulta.next()){
            String col1 = consulta.getNString(1);
            System.out.println(col1);
        }
    }

    public static void consultaInfoBici() throws SQLException {
        String sql = "SELECT fabricante,precio,año FROM bicicletas WHERE año >= 2019 ORDER BY fabricante";
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery(sql);

        while (consulta.next()) {
            String col1 = consulta.getString(1);
            int col2 = consulta.getInt(2);
            String col3 = consulta.getString(3);
            System.out.println(col1 + " " + col2 + " " + col3);
        }
    }

    public static void consultaFabriMoto() throws SQLException {
        String sql = "select fabricante from motocicletas where id_prov=101";
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery(sql);

        while (consulta.next()) {
            String col1 = consulta.getString(1);
            System.out.println(col1);
        }
    }

    public static void consultaCompBiMo() throws SQLException {
        String sql = "select fabricante from compras where alias=\"lucky\" order by fabricante";
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery(sql);

        while (consulta.next()) {
            String col1 = consulta.getString(1);
            System.out.println(col1);
        }
    }

    public static void consultaCompBiCli() throws SQLException {
        String sql = "select c.alias, c.nombre, c.apellidos from clientes c,compras p where  p.alias=c.alias and p.fabricante=\"Yeti\" order by nombre";
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery(sql);

        while (consulta.next()) {
            String col1 = consulta.getString(1);
            String col2 = consulta.getString(2);
            String col3 = consulta.getString(3);
            System.out.println(col1 + " " + col2 + " " + col3);
        }
    }

    public static void consultaBiciFab() throws SQLException {
        System.out.println("Por favor ingrese Año ");
        System.out.print("Año: ");
        int year = Principal.sc.nextInt();
        Principal.sc.nextLine();

        String sql = "select count(fabricante) from bicicletas where año >=" + year;
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery(sql);

        while (consulta.next()) {
            int col1 = consulta.getInt(1);
            System.out.println(col1);
        }
    }
}
