import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Esta clase es la principal donde inicializas tu programa y muestra un menu
 */
public class Main {
	public static void main(String[] args) throws IOException, SQLException, ParseException {
		Menu menu = new Menu();
		
		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
		Connection c = connectionFactory.connect();

		PilotoController pilotoController = new PilotoController(c);
		EscuderiaController escuderiaController = new EscuderiaController(c);
		TodoController todoController = new TodoController(c);


		int option = menu.mainMenu();
		while (option > 0 && option < 16) {
			switch (option) {
				case 1:
					pilotoController.borrarTabla();
					escuderiaController.borrarTabla();
					break;

				case 2:
					escuderiaController.crearTabla();
					pilotoController.crearTabla();
					break;

				case 3:
					todoController.rellenar();
					break;

				case 4:
					pilotoController.showPilotoPorEscuderia();
					break;

				case 5:
					pilotoController.showPilotoCon();
					break;

				case 6:
					pilotoController.showPilotoPor();
					break;

				case 7:
					pilotoController.modificarPiloto();
					break;

				case 8:
					pilotoController.borrarPiloto();
					break;

				case 9:
					pilotoController.borrarPilotoPorEscuderia();
					break;

				case 10:
					escuderiaController.createEscuderia();
					break;

				case 11:
					pilotoController.createPiloto();
					break;

				case 12:
					pilotoController.showPilotos();
					break;

				case 13:
					escuderiaController.showEscuderias();
					break;

				case 14:
					System.exit(0);
					break;

				default:
					System.out.println("Introdueixi una de les opcions anteriors");
					break;
				}
			option = menu.mainMenu();
		}
	}
}
