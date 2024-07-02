
public class jugador {

	private String nombre;
	private String posicion;
	private int edad;
	private int numcamiseta;

	public jugador(String nombre, String posicion, int edad, int numcamiseta) {
		super();
		this.nombre = nombre;
		this.posicion = posicion;
		this.edad = edad;
		this.numcamiseta = numcamiseta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getNumcamiseta() {
		return numcamiseta;
	}

	public void setNumcamiseta(int numcamiseta) {
		this.numcamiseta = numcamiseta;
	}

	@Override
	public String toString() {
		return "jugador [nombre=" + nombre + ", posicion=" + posicion + ", edad=" + edad + ", numcamiseta="
				+ numcamiseta + "]";
	}

}
