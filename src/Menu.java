import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

/**
 * Esta clase sirve para mostrar menus
 */
public class Menu {
	private int option;
	private String opciones;

	/**
	 * Este es un constructor
	 */
	public Menu() {
		super();
	}

	/**
	 * Este metodo sirve para mostrar un menu
	 * @return devuelte la opcion que elegiste en numero
	 */
	public int mainMenu() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		do {

			System.out.println(" \nMENU PRINCIPAL \n");

			System.out.println("1. Borrar Tablas");
			System.out.println("2. Craer Tablas");
			System.out.println("3. Rellenar Tablas");
			System.out.println("7. Modificar el nombre de un piloto");
			System.out.println("8. Eliminar un piloto");
			System.out.println("10. Crear escuderia");
			System.out.println("11. Crear piloto");
			System.out.println("12. Mostrar pilotos");
			System.out.println("13. Mostrar escuderias");
			System.out.println("14. Exit");
			System.out.println("Escoge opción: ");
			try {
				option = Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("valor no valido");
				e.printStackTrace();

			}

		} while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7
				&& option != 8 && option != 9 && option != 10 && option != 11 && option != 12 && option != 13 && option != 14 && option != 15);


		return option;
	}

	/**
	 * Este metodo sirve para mostrar el menu de escuderias
	 * @param c recibe la coneccion
	 * @return devuelve la escuderia que elegiste
	 */
	public String escuderiaMenu(Connection c){
		EscuderiaController escuderiaController = new EscuderiaController(c);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(;;){
			escuderiaController.showEscuderias();
			System.out.println("Elige la escuderia: ");
			try {
				opciones = br.readLine();
			} catch (NumberFormatException | IOException e) {
				System.out.println("valor no valido");
				e.printStackTrace();
			}
			return opciones;
		}
	}

	/**
	 * Este metodo sirve para mostrar un menu de nombres de piloto
	 * @param c recibe la coneccion
	 * @return devuelve el piloto que elegiste
	 */
	public String NombreMenu(Connection c){
		PilotoController pilotoController = new PilotoController(c);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("\n" + "Pilotos: ");
		for(;;){
			pilotoController.showPilotoNombre();
			try {
				opciones = br.readLine();
			} catch (NumberFormatException | IOException e) {
				System.out.println("valor no valido");
				e.printStackTrace();
			}
			return opciones;
		}
	}

	/**
	 * Este metodo sirve para auntenticar
	 * @param tries recibe la cantidad de intento
	 * @return devuelve unos datos de tipo Identity
	 * @throws IOException es un tipo de excepciones
	 */
	public Identity authenticate(int tries) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("============================ACB=============================");
		System.out.println("============================================================");
		System.out.println("Avís: tens " + (3 - tries) + " intents per loginarte");
		System.out.println("============================================================");
		System.out.println("Inserta nom del usuari: ");
		String user = br.readLine();
		System.out.println("Inserta contrasenya: ");
		String password = br.readLine();

		Identity identity = new Identity(user, password);
		return identity;
	}
}
