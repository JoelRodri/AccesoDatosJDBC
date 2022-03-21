import java.io.*;
import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Esta clase sirve para controlar la tabla rol situada en mi base de datos
 */
public class EscuderiaController {
    private Connection connection;
    Scanner sc;
    Menu menu = new Menu();

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la coneccion hacia postgres
     */
    public EscuderiaController(Connection connection) {
        this.connection = connection;
        this.sc = new Scanner(System.in);
    }

    /**
     * Este metodo sirve para crear una escuderia
     */
    public void createEscuderia() {
        try {
            System.out.println("----------------------");
            System.out.println("Crear Escuderia");
            System.out.println("----------------------");

            System.out.println("Nombre:");
            String escuderia = sc.nextLine().toUpperCase(Locale.ROOT);

            String sql = "INSERT INTO escuderia " +
                    "(escuderia) VALUES (?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, escuderia);

            pst.executeUpdate();

            pst.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para mostrar escuderias
     */
    public void showEscuderias(){
        System.out.println("\n" + "Escuderias: ");

        ResultSet rs = null;
        String sql = "SELECT * FROM escuderia";
        try{
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("- " + rs.getString("escuderia"));
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            System.out.println("Error: tabla escuderia no existe");
        }
    }

    /**
     * Este metodo sirve para borrar la tabla de escuderia
     */
    public void borrarTabla() {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DROP table escuderias");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: tabla escuderias no existe");
        }
    }

    /**
     * Este metodo sirve para crear la tabla de rol
     */
    public void crearTabla(){
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("CREATE TABLE escuderia(escuderia varchar(20) primary key)");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: tabla escuderia ya existe");

        }
    }
}
