
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class main {

	public static void main(String[] args) {
		mostrarMenuPrincipal();
	}
	

	public static void mostrarMenuPrincipal() {
		gestorequipo nuevo = new gestorequipo();

		nuevo.getEquipos().add(new equipo("Boca", "La Boca "));
		nuevo.getEquipos().add(new equipo("River", "Nuñez"));
		nuevo.getEquipos().add(new equipo("Lanus", "Lanus"));
		nuevo.getEquipos().add(new equipo("Banfield", "Lomas de Zamora"));
		nuevo.getEquipos().add(new equipo("Independiente", "Avellaneda"));
		nuevo.getEquipos().add(new equipo("Racing", "Avellaneda"));
		nuevo.getEquipos().add(new equipo("Rosario Central", "Rosario"));
		nuevo.getEquipos().add(new equipo("Newells", "Rosario"));

		for (equipo equipo : nuevo.getEquipos()) {
			equipo.agregarJugadoresRandom(11);
		}

		String[] MenuPrin = { "Jugar Partido ", "Apostar", "Equipos"
				+ ""
				+ ""
				+ ""
				+ "","Partidos terminados", "Salir" };
		gestorequipo gestor = new gestorequipo();
		int opc;

		do {
			opc = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Menu Principal", 0, 0, null, MenuPrin,

					MenuPrin[0]);

			switch (opc) {
			case 0:

				if (partido.getCantpartidos() < 4) {
					partido jugado = nuevo.jugarpartido(seleccionarEquipo(nuevo.getEquipos()),
							seleccionarEquipo(nuevo.getEquipos()));
					JOptionPane.showMessageDialog(null, "Se jugo el partido: " + jugado);
					nuevo.getGandores().add(jugado.ganador());
					nuevo.getPartido().add(jugado);
					double ganancias = jugado.calcularGanancias();
                    JOptionPane.showMessageDialog(null, "Ganancias de apuestas: " + ganancias);
				} else if (partido.getCantpartidos() >= 4 && partido.getCantpartidos() < 6) {
					partido jugado = nuevo.jugarpartido(seleccionarEquipo(nuevo.getGandores()),
							seleccionarEquipo(nuevo.getGandores()));
					JOptionPane.showMessageDialog(null, "Se jugo el partido: " + jugado);
					nuevo.getFinalistas().add(jugado.ganador());
					nuevo.getPartido().add(jugado);
					double ganancias = jugado.calcularGanancias();
                    JOptionPane.showMessageDialog(null, "Ganancias de apuestas: " + ganancias);
				} else {
					partido jugado = nuevo.jugarpartido(nuevo.getFinalistas().get(0), nuevo.getFinalistas().get(1));
					JOptionPane.showMessageDialog(null, "Se jugo el partido: " + jugado);
					JOptionPane.showMessageDialog(null, "El campeon es: " + jugado.ganador().getNombre());
					nuevo.getPartido().add(jugado);
					double ganancias = jugado.calcularGanancias();
                    JOptionPane.showMessageDialog(null, "Ganancias de apuestas: " + ganancias);
				}

				break;
			case 1:
				realizarApuesta(nuevo);
				break;
			case 2:
				nuevo.menuequipos();
				break;
			case 3:
				JOptionPane.showMessageDialog(null, nuevo.getPartido());
				break;
			case 4: // Salir
				return;
			default:
				break;
			}
		} while (opc != 2);
	}

	public static equipo seleccionarEquipo(LinkedList<equipo> equipos) {

		String[] equiposarray = new String[equipos.size()];
		for (int i = 0; i < equipos.size(); i++) {
			equiposarray[i] = equipos.get(i).getNombre();
		}
		int opcion = JOptionPane.showOptionDialog(null, "Seleccione equipo", null, 0, 0, null, equiposarray,
				equiposarray[0]);
		equipo seleccionado = equipos.get(opcion);
		equipos.remove(seleccionado);
		return seleccionado;
	}
	
	public static void realizarApuesta(gestorequipo gestor) {
	    LinkedList<partido> partidosPendientes = gestor.getPartido();

	    if (partidosPendientes.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No hay partidos disponibles para apostar.");
	        return;
	    }

	    partido ultimoPartido = partidosPendientes.getLast();
	    equipo equipo1 = ultimoPartido.getEquipo1();
	    equipo equipo2 = ultimoPartido.getEquipo2();

	    String[] opcionesEquipos = { equipo1.getNombre(), equipo2.getNombre() };
	    int seleccion = JOptionPane.showOptionDialog(null, "Seleccione el equipo en el que quiere apostar", "Realizar Apuesta", 0, JOptionPane.QUESTION_MESSAGE, null, opcionesEquipos, opcionesEquipos[0]);

	    equipo equipoApostado = (seleccion == 0) ? equipo1 : equipo2;
	    double monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto de su apuesta:"));

	    apuesta nuevaApuesta = new apuesta();
	    ultimoPartido.agregarApuesta(nuevaApuesta);

	    JOptionPane.showMessageDialog(null, "Apuesta realizada: " + monto + " en " + equipoApostado.getNombre());
	}
}