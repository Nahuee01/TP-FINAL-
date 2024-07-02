import java.util.LinkedList;
import javax.swing.JOptionPane;

public class equipo {

	private String nombre;
	private String ciudad;
	private LinkedList<jugador> jugadores = new LinkedList<jugador>();

	public equipo(String nombre, String ciudad) {
		this.nombre = nombre;
		this.ciudad = ciudad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public LinkedList<jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(LinkedList<jugador> jugadores) {
		this.jugadores = jugadores;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Equipo: ").append(nombre).append("\n");
		sb.append("Jugadores:\n");
		for (jugador jugador : jugadores) {
			sb.append(" - ").append(jugador).append("\n");
		}
		return sb.toString();
	}

	// --------------------------------------- Agregar Jugador
	// --------------------------------------- //
	public void agregarManualmente() {
		String nombre = JOptionPane.showInputDialog("Ingrese nombre del jugador");
		String posicion = JOptionPane.showInputDialog("Ingrese posicion del jugador");
		int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese edad del jugador"));
		int numcamiseta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese numero de jugador"));
		// ----------------------Verifica Edad----------------------//
		if (edad <= 18) {
			JOptionPane.showMessageDialog(null, "El jugador debe tener 18 años o más");
			return;
		}

		jugador nuevo = new jugador(nombre, posicion, edad, numcamiseta);
		boolean flag = true;
		for (jugador jugador : this.getJugadores()) {
			// ----------------------Verifica Num Camiseta----------------------//
			if (jugador.getNumcamiseta() == nuevo.getNumcamiseta()) {
				JOptionPane.showMessageDialog(null, "Este número ya existe");
				flag = false;
				break;
			}
		}
		if (flag) {
			this.getJugadores().add(nuevo);
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo agregar el jugador, el número ya existe");
		}
	}

	// --------------------------------------- Eliminar Jugador
	// --------------------------------------- //
	public void eliminarjugador(String nombreJugador) {
		jugadores.removeIf(jugador -> jugador.getNombre().equals(nombreJugador));
	}

//  ---------------------------------------  Buscar Jugador  ---------------------------------------  //
	public String buscarJugador(String nombreJugador) {
		for (jugador jugador : jugadores) {
			if (jugador.getNombre().equalsIgnoreCase(nombreJugador)) {
				return "Jugador encontrado: " + jugador;
			}
		}
		return "Jugador no encontrado.";
	}

	public void agregarJugadoresRandom(int cant) {

		for (int i = 0; i < cant; i++) {
			boolean flag = true;
			do {
				int camiseta = (int) (Math.random() * 99 + 1);
				for (jugador jugador : this.getJugadores()) {
					if (jugador.getNumcamiseta() == camiseta) {
						flag = false;
					}

				}
				this.getJugadores().add(new jugador("nombre", "posicion", 24, camiseta));
				break;

			} while (flag = false);

		}

	}
}
