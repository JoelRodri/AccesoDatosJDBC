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
 * Esta clase sirve para leer fichero CSV y meter en la base de datos
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
        String[] rata;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("f1.csv")));
            String linia;
            while ((linia = br.readLine()) != null) {
                if (cont > 0) {
                    rata = linia.split("\"");

                    try {
                        String rol = rata[3];

                        String sql = "INSERT INTO rol " +
                                "(rol) VALUES (?)";

                        PreparedStatement pst = connection.prepareStatement(sql);
                        pst.setString(1, rol);

                        pst.executeUpdate();

                        pst.close();

                    } catch (SQLException e) {
                    }

                    try {
                        String nom = rata[1];
                        String rol = rata[3];
                        String historia = rata[5];

                        String sql = "INSERT INTO campeon " +
                                "(nom,rol,historia) VALUES (?,?,?)";

                        PreparedStatement pst = connection.prepareStatement(sql);
                        pst.setString(1, nom);
                        pst.setString(2, rol);
                        pst.setString(3, historia);

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
