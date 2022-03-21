import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Esta clase sirve para controlar la tabla campeon situada en mi base de datos
 */
public class PilotoController {
    private Connection connection;
    Scanner sc;
    Menu menu = new Menu();

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la coneccion hacia postgres
     */
    public PilotoController(Connection connection) {
        this.connection = connection;
        this.sc = new Scanner(System.in);
    }

    /**
     * Este metodo sirve para crear un piloto
     */
    public void createPiloto() {
        try {
            System.out.println("----------------------");
            System.out.println("Crear Piloto");
            System.out.println("----------------------");

            System.out.println("Numero: ");
            String numero = sc.nextLine().toUpperCase(Locale.ROOT);

            System.out.println("Nombre: ");
            String nombre = sc.nextLine().toUpperCase(Locale.ROOT);

            System.out.println("Elige una escuderia: ");
            String escuderia = menu.escuderiaMenu(connection).toUpperCase(Locale.ROOT);

            System.out.println("Elije un pais: ");
            String pais = sc.nextLine().toUpperCase(Locale.ROOT);

            System.out.println("Podiums: ");
            String podiums = sc.nextLine();

            System.out.println("Puntos totales: ");
            String puntosTotales = sc.nextLine();

            System.out.println("gpCompletados: ");
            String gpCompletados = sc.nextLine();

            System.out.println("Titulos mundiales: ");
            String titulosMundiales = sc.nextLine();

            System.out.println("Mejor posición: ");
            String mejorPos = sc.nextLine();

            System.out.println("Mejor clasificación: ");
            String mejorClas = sc.nextLine();

            System.out.println("Fecha de nacimiento: ");
            String fechaNaci = sc.nextLine();

            System.out.println("Nacionalidad: ");
            String nacionalidad = sc.nextLine();

            String sql = "INSERT INTO campeon " +
                    "(numero, nombre, escuderia, pais, podiums, puntosTotales, gpCompletados, titulosMundiales, mejorPos, mejorClas, fechaNaci, nacionalidad) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, numero);
            pst.setString(2,nombre);
            pst.setString(3, escuderia);
            pst.setString(4, pais);
            pst.setString(5, podiums);
            pst.setString(6, puntosTotales);
            pst.setString(7, gpCompletados);
            pst.setString(8, titulosMundiales);
            pst.setString(9, mejorPos);
            pst.setString(10, mejorClas);
            pst.setString(11, fechaNaci);
            pst.setString(12, nacionalidad);

            pst.executeUpdate();

            pst.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para borrar la tabla de piloto
     */
    public void borrarTabla() {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DROP table piloto");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: tabla piloto no existe");
        }
    }

    /**
     * Este metodo sirve para crear la tabla de piloto
     */
    public void crearTabla(){
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("CREATE TABLE piloto(id smallint primary key GENERATED ALWAYS AS IDENTITY, " +
                    "numero varchar(5), " +
                    "nombre varchar(30), " +
                    "escuderia varchar (20) references escuderia(escuderia), " +
                    "pais varchar (20), " +
                    "podiums varchar (10), " +
                    "puntosTotales varchar (10), " +
                    "gpCompletados varchar (5), " +
                    "titulosMundiales varchar (5), " +
                    "mejorPos varchar (10), " +
                    "mejorClas varchar (5), " +
                    "fechaNacimiento varchar (20), " +
                    "nacionalidad varchar (30)");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: tabla piloto ya existe");
        }
    }

    /**
     * Este metodo sirve para mostrar piloto por pais
     */
    public void showPilotoPorEscuderia(){
        ResultSet rs = null;
        String escuderia = menu.escuderiaMenu(connection).toUpperCase(Locale.ROOT);
        String sql = "SELECT * FROM piloto where escuderia='" + escuderia + "'";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + "Numero: " + rs.getString("numero") + "\n" +
                        "Nombre: " + rs.getString("nombre") + "\n" +
                        "Escuderia: " + rs.getString("escuderia") + "\n" +
                        "Pais: " + rs.getString("pais") + "\n" +
                        "Podiums: " + rs.getString("podiums") + "\n" +
                        "Puntos totales: " + rs.getString("puntosTotales") + "\n" +
                        "GP completados: " + rs.getString("gpCompletados") + "\n" +
                        "Titulos mundiales: " + rs.getString("titulosMundiales") + "\n" +
                        "Mejor posición: " + rs.getString("mejorPos") + "\n" +
                        "Mejor clasificación: " + rs.getString("mejorClas") + "\n" +
                        "Fecha nacimiento: " + rs.getString("fechaNaci") + "\n" +
                        "Nacionalidad: " + rs.getString("nacionalidad"));
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para mostrar piloto con un texto especificado
     */
    public void showPilotoCon(){
        ResultSet rs = null;
        System.out.println("Escribe el texto a contener: ");
        String letra = sc.nextLine().toUpperCase(Locale.ROOT);
        String sql = "select * from piloto where nombre like '%" + letra + "%'";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + "Numero: " + rs.getString("numero") + "\n" +
                        "Nombre: " + rs.getString("nombre") + "\n" +
                        "Escuderia: " + rs.getString("escuderia") + "\n" +
                        "Pais: " + rs.getString("pais") + "\n" +
                        "Podiums: " + rs.getString("podiums") + "\n" +
                        "Puntos totales: " + rs.getString("puntosTotales") + "\n" +
                        "GP completados: " + rs.getString("gpCompletados") + "\n" +
                        "Titulos mundiales: " + rs.getString("titulosMundiales") + "\n" +
                        "Mejor posición: " + rs.getString("mejorPos") + "\n" +
                        "Mejor clasificación: " + rs.getString("mejorClas") + "\n" +
                        "Fecha nacimiento: " + rs.getString("fechaNaci") + "\n" +
                        "Nacionalidad: " + rs.getString("nacionalidad"));
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para mostrar piloto que empiezan por la letra que digamos
     */
    public void showPilotoPor(){
        ResultSet rs = null;
        System.out.println("Escribe la letra de inicio: ");
        String letra = sc.nextLine().toUpperCase(Locale.ROOT);
        String sql = "select * from piloto where nombre like '" + letra + "%'";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + "Numero: " + rs.getString("numero") + "\n" +
                        "Nombre: " + rs.getString("nombre") + "\n" +
                        "Escuderia: " + rs.getString("escuderia") + "\n" +
                        "Pais: " + rs.getString("pais") + "\n" +
                        "Podiums: " + rs.getString("podiums") + "\n" +
                        "Puntos totales: " + rs.getString("puntosTotales") + "\n" +
                        "GP completados: " + rs.getString("gpCompletados") + "\n" +
                        "Titulos mundiales: " + rs.getString("titulosMundiales") + "\n" +
                        "Mejor posición: " + rs.getString("mejorPos") + "\n" +
                        "Mejor clasificación: " + rs.getString("mejorClas") + "\n" +
                        "Fecha nacimiento: " + rs.getString("fechaNaci") + "\n" +
                        "Nacionalidad: " + rs.getString("nacionalidad"));
            }


            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para mostrar los nombres de piloto que hay
     */
    public void showPilotoNombre(){
        ResultSet rs = null;
        String sql = "SELECT nombre FROM piloto";
        try{
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("- " + rs.getString("nombre"));
            }


            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para mostrar campeones
     */
    public void showPilotos(){
        System.out.println("\n" + "Pilotos: ");
        ResultSet rs = null;
        String sql = "SELECT * FROM piloto";
        try{
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + "Numero: " + rs.getString("numero") + "\n" +
                        "Nombre: " + rs.getString("nombre") + "\n" +
                        "Escuderia: " + rs.getString("escuderia") + "\n" +
                        "Pais: " + rs.getString("pais") + "\n" +
                        "Podiums: " + rs.getString("podiums") + "\n" +
                        "Puntos totales: " + rs.getString("puntosTotales") + "\n" +
                        "GP completados: " + rs.getString("gpCompletados") + "\n" +
                        "Titulos mundiales: " + rs.getString("titulosMundiales") + "\n" +
                        "Mejor posición: " + rs.getString("mejorPos") + "\n" +
                        "Mejor clasificación: " + rs.getString("mejorClas") + "\n" +
                        "Fecha nacimiento: " + rs.getString("fechaNaci") + "\n" +
                        "Nacionalidad: " + rs.getString("nacionalidad"));
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            System.out.println("Error: tabla piloto no existe");
        }
    }

    /**
     * Este metodo sirve para modificar el nombre de un piloto
     */
    public void modificarPiloto(){
        try {
            Statement st = connection.createStatement();
            String nombre = menu.NombreMenu(connection).toUpperCase(Locale.ROOT);
            System.out.println("Escribe el nuevo nombre: ");
            String nombreNuevo = sc.nextLine().toUpperCase(Locale.ROOT);

            st.executeUpdate("update piloto set nombre='" + nombreNuevo + "' where nombre='" + nombre + "'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para borrar un piloto
     */
    public void borrarPiloto(){
        try {
            Statement st = connection.createStatement();
            System.out.println("A quien quieres eliminar: ");
            String nombre = menu.NombreMenu(connection).toUpperCase(Locale.ROOT);
            st.executeUpdate("delete from piloto where nombre='" + nombre + "'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para borrar pilotos por escuderias
     */
    public void borrarPilotoPorEscuderia(){
        try {
            Statement st = connection.createStatement();
            System.out.println("Que escuderia quieres eliminar: ");
            String escuderia = menu.escuderiaMenu(connection).toUpperCase(Locale.ROOT);
            st.executeUpdate("delete from piloto where escuderia='" + escuderia + "'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
