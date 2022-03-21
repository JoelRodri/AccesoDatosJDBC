import org.postgresql.util.PSQLException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Esta clase sirve para leer el fichero CSV y meter la info en la base de datos
 */
public class TodoController {
    private Connection connection;
    Scanner sc;

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la coneccion hacia postgres
     */
    public TodoController(java.sql.Connection connection) {
        this.connection = connection;
        this.sc = new Scanner(System.in);
    }

    /**
     * Este metodo sirve para rellenar datos de un fichero hacia las tablas de base de datos
     */
    public void rellenar() {
        int cont = 0;
        String[] array;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("f1.csv")));
            String linia;
            while ((linia = br.readLine()) != null) {
                if (cont > 0) {
                    array = linia.split("\"");

                    try {
                        String escuderia = array[5];

                        String sql = "INSERT INTO escuderia " +
                                "(escuderia) VALUES (?)";

                        PreparedStatement pst = connection.prepareStatement(sql);
                        pst.setString(1, escuderia);

                        pst.executeUpdate();

                        pst.close();

                    } catch (SQLException e) {
                    }

                    try {
                        String nombre = array[1];
                        String numero = array[3];
                        String escuderia = array[5];
                        String pais = array[7];
                        String podiums = array[9];
                        String puntosTotales = array[11];
                        String gpCompletados = array[13];
                        String titulosMundiales = array[15];
                        String mejorPos = array[17];
                        String mejorClas = array[19];
                        String fechaNaci = array[21];
                        String nacionalidad = array[23];

                        String sql = "INSERT INTO piloto" +
                                "(numero, nombre, escuderia, pais, podiums, puntosTotales, gpCompletados, titulosMundiales, mejorPos, mejorClas, fechaNacimiento, nacionalidad) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

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

                    } catch (SQLException e) {
                    }
                } else {
                    cont++;
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
