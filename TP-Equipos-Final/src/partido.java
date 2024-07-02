import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class partido {

	private equipo equipo1;
	private equipo equipo2;
	private int goles1;
	private int goles2;
	private String fase;
	private LocalDate fecha;
	private static int cantpartidos = 0;
	private String estado;
	private List<apuesta> apuestas;

	public partido(equipo equipo1, equipo equipo2,int goles1, int goles2) {
		super();
		cantpartidos++;
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.goles1 = goles1;
		this.goles2 = goles2;
		fase = generarfase();
		fecha = generarfecha();
		this.estado = "Pendiente";
		this.apuestas = new ArrayList<>();
	}

	
	public List<apuesta> getApuestas() {
		return apuestas;
	}


	public void setApuestas(List<apuesta> apuestas) {
		this.apuestas = apuestas;
	}


	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(equipo equipo2) {
		this.equipo2 = equipo2;
	}

	public int getGoles1() {
		return goles1;
	}

	public void setGoles1(int goles1) {
		this.goles1 = goles1;
	}

	public int getGoles2() {
		return goles2;
	}

	public void setGoles2(int goles2) {
		this.goles2 = goles2;
	}

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public static int getCantpartidos() {
		return cantpartidos;
	}

	public static void setCantpartidos(int cantpartidos) {
		partido.cantpartidos = cantpartidos;
	}

	@Override
	public String toString() {
		return "\n "+ "Partido: " + equipo1.getNombre() +
				" " + goles1 + 
				" VS " + 
				equipo2.getNombre() +  
				" "+ goles2 +
				"\n " + " fase= " + fase +
				"\n " + " fecha= " + fecha ;
	}

	public String generarfase() {

		if (cantpartidos <= 4) {
			return "cuartos";
		} else if (cantpartidos > 4 && cantpartidos <= 6) {
			return "semifinal";
		} else {
			return "final";
		}

	}
	public equipo ganador(){
		
		if (goles1>goles2) {
			return equipo1;	
		} else {
			return equipo2;	
		}
		
	}
	

	public LocalDate generarfecha() {

		if (cantpartidos <= 4) {
			return LocalDate.now();
		} else if (cantpartidos > 4 && cantpartidos <= 6) {
			return LocalDate.now().plusDays(3);
		} else {
			return LocalDate.now().plusDays(4);
		}

	}
	public void agregarApuesta(apuesta apuesta) {
        apuestas.add(apuesta);
    }

    public List<apuesta> getApuesta() {
        return apuestas;
    }

    public double calcularGanancias() {
        double totalGanancias = 0;
        for (apuesta apuesta : apuestas) {
            totalGanancias += apuesta.calcularGanancia(ganador());
        }
        return totalGanancias;
    }

}
