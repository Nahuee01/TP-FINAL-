import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.JOptionPane;

public class gestorequipo {

	private LinkedList<equipo> equipos = new LinkedList<equipo>();
	private LinkedList<partido> partido = new LinkedList<partido>();
	private LinkedList<equipo> gandores = new LinkedList<equipo>();
	private LinkedList<equipo> finalistas = new LinkedList<equipo>();

	@Override
	public String toString() {
		StringBuilder lista = new StringBuilder("Equipos:\n");
		for (equipo equipo : equipos) {
			lista.append(equipo).append("\n");
		}
		return lista.toString();
	}

	public LinkedList<equipo> getFinalistas() {
		return finalistas;
	}

	public void setFinalistas(LinkedList<equipo> finalistas) {
		this.finalistas = finalistas;
	}

	public LinkedList<equipo> getGandores() {
		return gandores;
	}

	public void setGandores(LinkedList<equipo> gandores) {
		this.gandores = gandores;
	}

	public LinkedList<partido> getPartido() {
		return partido;
	}

	public void setPartido(LinkedList<partido> partido) {
		this.partido = partido;
	}

	public LinkedList<equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(LinkedList<equipo> equipos) {
		this.equipos = equipos;
	}

	// --------------------------------------- Menu
	// --------------------------------------- //

	public void menuequipos() {

		String[] nombresEquipos = new String[equipos.size() + 2];
		for (int i = 0; i < equipos.size(); i++) {
			nombresEquipos[i] = equipos.get(i).getNombre();
		}
		nombresEquipos[equipos.size()] = "Ajustes";
		nombresEquipos[equipos.size() + 1] = "Salir";
		int equi;
		do {
			equi = JOptionPane.showOptionDialog(null, "Seleccione un equipo", "Equipos", 0, 0, null, nombresEquipos,
					nombresEquipos[0]);

			if (equi < equipos.size()) {
				equipo equipoSeleccionado = equipos.get(equi);
				String[] accionar = { "Agregar Jugador", "Ver Lista", "Eliminar Jugador", "Buscar Un Jugador",
						"Volver Al Menu" };
				int accion = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Equipos", 0, 0, null,
						accionar, accionar[0]);

				switch (accion) {
				case 0:
					equipoSeleccionado.agregarManualmente();
					JOptionPane.showMessageDialog(null, equipoSeleccionado);
					break;
				case 1:
					JOptionPane.showMessageDialog(null, equipoSeleccionado);
					break;
				case 2:
					eliminarJugador(equipoSeleccionado);
					break;
				case 3:
					String nombreJugador = JOptionPane.showInputDialog("Ingrese el nombre del jugador a buscar:");
					String resultadoBusqueda = equipoSeleccionado.buscarJugador(nombreJugador);
					JOptionPane.showMessageDialog(null, resultadoBusqueda);
					break;
				default:
					break;
				}
			} else if (equi == equipos.size()) {
				Ajustes();
			}

		} while (equi != equipos.size() + 1);
	}

	// --------------------------------------- Funcion Eliminar Un Jugador
	// --------------------------------------- //

	private void eliminarJugador(equipo equipo) {
		if (equipo.getJugadores().isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay jugadores para eliminar");
			return;
		}

		String[] nombresJugadores = equipo.getJugadores().stream().map(jugador::getNombre).toArray(String[]::new);
		String jugadorAEliminar = (String) JOptionPane.showInputDialog(null, "Seleccione el jugador a eliminar",
				"Eliminar Jugador", JOptionPane.QUESTION_MESSAGE, null, nombresJugadores, nombresJugadores[0]);

		if (jugadorAEliminar != null) {
			equipo.eliminarjugador(jugadorAEliminar);
			JOptionPane.showMessageDialog(null, "Jugador eliminado: " + jugadorAEliminar);
		}
	}

	// --------------------------------------- Funcion Jugar Partido
	// --------------------------------------- //

	public partido jugarpartido(equipo equipo1, equipo equipo2) {

		String[] opcionesEquipos = { equipo1.getNombre(), equipo2.getNombre() };
		int seleccion = JOptionPane.showOptionDialog(null, "Seleccione su equipo", "Jugar Partido", 0,
				JOptionPane.QUESTION_MESSAGE, null, opcionesEquipos, opcionesEquipos[0]);

		equipo local;
		equipo visitante;
		if (seleccion == 0) {
			local = equipo1;
			visitante = equipo2;
		} else {
			local = equipo2;
			visitante = equipo1;
		}
		if (equipo1.getJugadores().size() >= 8 && equipo2.getJugadores().size() >= 8) {
			int goles1 = (int) (Math.random() * 2);
			int goles2 = (int) (Math.random() * 2);

			if (goles1 == goles2) {
				JOptionPane.showMessageDialog(null, "Empataron: " + goles1 + "-" + goles2);

				// ----------------------Penales----------------------//
				int penalesEquipo1 = 0;
				int penalesEquipo2 = 0;

				for (int i = 0; i < 2; i++) {
					if (Math.random() > 0.5) {
						penalesEquipo1++;
					}
					if (Math.random() > 0.5) {
						penalesEquipo2++;
					}
				}
				JOptionPane.showMessageDialog(null, "Resultado penales: " + equipo1.getNombre() + " " + penalesEquipo1
						+ " - " + equipo2.getNombre() + " " + penalesEquipo2);
				while (penalesEquipo1 == penalesEquipo2) {
					if (Math.random() > 0.5) {
						penalesEquipo1++;
					} else {
						penalesEquipo2++;
					}
					JOptionPane.showMessageDialog(null, "Nueva ronda penales: " + equipo1.getNombre() + " "
							+ penalesEquipo1 + " - " + equipo2.getNombre() + " " + penalesEquipo2);
				}

				if (goles1 > goles2 || penalesEquipo1 > penalesEquipo2) {
					JOptionPane.showMessageDialog(null, "Ganó el equipo: " + equipo1.getNombre());
					return new partido(equipo1, equipo2, goles1, goles2);
				} else {
					JOptionPane.showMessageDialog(null, "Ganó el equipo: " + equipo2.getNombre());
					return new partido(equipo1, equipo2, goles1, goles2);
				}

			} else {
				// ----------------------Solo Goles----------------------//
				if (goles1 > goles2) {
					JOptionPane.showMessageDialog(null, "Ganó el equipo: " + equipo1.getNombre());
					return new partido(equipo1, equipo2, goles1, goles2);
				} else {
					JOptionPane.showMessageDialog(null, "Ganó el equipo: " + equipo2.getNombre());
					return new partido(equipo1, equipo2, goles1, goles2);
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "No hay suficientes jugadores");
		}
		return null;
	}

	// --------------------------------------- Menu Ajustes
	// --------------------------------------- //

	public void Ajustes() {

		getEquipos();

		String[] ajustes = { "Agregar Equipo", "Eliminar Equipo", "Buscar Equipo", "Ver Equipos", "Salir" };
		int ajus;
		do {
			ajus = JOptionPane.showOptionDialog(null, "Opciones", "Menu de Gestión de Equipos",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, ajustes, ajustes[0]);

			switch (ajus) {
			case 0:
				agregarequipo();

				break;
			case 1:
				String nombreEliminar = JOptionPane.showInputDialog("Ingrese el nombre del equipo a eliminar:");
				if (nombreEliminar != null && !nombreEliminar.trim().isEmpty()) {
					eliminarequipo(nombreEliminar);
				}
				break;
			case 2:
				String nombreBuscar = JOptionPane.showInputDialog("Ingrese el nombre del equipo a buscar:");
				if (nombreBuscar != null && !nombreBuscar.trim().isEmpty()) {
					String resultado = buscarequipo(nombreBuscar);
					JOptionPane.showMessageDialog(null, resultado);
				}
				break;
			case 3:
				JOptionPane.showMessageDialog(null, equipos);
				break;
			case 4:
				// Opción para salir
				break;
			default:
				break;
			}
		} while (ajus != 4);

	}

//  ---------------------------------------  Eliminar Equipo  ---------------------------------------  //

	public String eliminarequipo(String nombreEquipo) {
		boolean eliminado = equipos.removeIf(equipo -> equipo.getNombre().equalsIgnoreCase(nombreEquipo));
		if (eliminado) {
			JOptionPane.showMessageDialog(null, "Equipo Eliminado: " + nombreEquipo);
		} else {
			JOptionPane.showMessageDialog(null, "Equipo no Encontrado: " + nombreEquipo);
		}
		return nombreEquipo;
	}

//  ---------------------------------------  Buscar Equipo  ---------------------------------------  //

	public String buscarequipo(String nombreEquipo) {
		for (equipo equipo : equipos) {
			if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
				return "Equipo encontrado: " + equipo;
			}
		}
		return "Equipo no encontrado.";
	}

	// --------------------------------------- Agregar Equipo
	// --------------------------------------- //

	public void agregarequipo() {

		String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nuevo equipo:");
		String ciudad = JOptionPane.showInputDialog("Ingrese la ciudad del nuevo equipo:");

		equipo nuevo = new equipo(nombre, ciudad);

		boolean flag = true;
		for (equipo equipo : equipos) {
			// ----------------------Verifica el nombre ----------------------//
			if (equipo.getNombre() == nuevo.getNombre()) {
				JOptionPane.showMessageDialog(null, "Este nombre de equipo ya existe");
				flag = false;
				break;
			}
		}
		if (flag) {
			equipos.add(nuevo);
			JOptionPane.showMessageDialog(null, "Equipo agregado exitosamente.");
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo agregar el equipo, el nombre ya existe");
		}
	}

	public static void apostar(gestorequipo nuevo) {
		if (nuevo.getPartido().isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay partidos disponibles para apostar.");
			return;
		}

		String[] partidosArray = new String[nuevo.getPartido().size()];
		for (int i = 0; i < nuevo.getPartido().size(); i++) {
			partidosArray[i] = nuevo.getPartido().get(i).toString();
		}

		int partidoSeleccionado = JOptionPane.showOptionDialog(null, "Seleccione un partido para apostar", "Apostar", 0,
				0, null, partidosArray, partidosArray[0]);
		partido partidoElegido = nuevo.getPartido().get(partidoSeleccionado);

		String equipo1 = partidoElegido.getEquipo1().getNombre();
		String equipo2 = partidoElegido.getEquipo2().getNombre();

		String[] opcionesEquipos = { equipo1, equipo2 };
		int equipoApostado = JOptionPane.showOptionDialog(null, "Seleccione el equipo al que desea apostar", "Apostar",
				0, 0, null, opcionesEquipos, opcionesEquipos[0]);

		double montoApostado = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a apostar"));

		equipo equipoGanador = partidoElegido.ganador();
		if ((equipoApostado == 0 && equipoGanador.getNombre().equals(equipo1))
				|| (equipoApostado == 1 && equipoGanador.getNombre().equals(equipo2))) {
			double ganancias = montoApostado * 2;
			JOptionPane.showMessageDialog(null, "¡Felicidades! Ha ganado la apuesta. Sus ganancias son: " + ganancias);
		} else {
			JOptionPane.showMessageDialog(null, "Lo siento, ha perdido la apuesta.");
		}
	}

}
