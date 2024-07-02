public class apuesta {
    private double monto;
    private equipo equipoApostado;

    public void Apuesta(double monto, equipo equipoApostado) {
        this.monto = monto;
        this.equipoApostado = equipoApostado;
    }

    public double getMonto() {
        return monto;
    }

    public equipo getEquipoApostado() {
        return equipoApostado;
    }

    public double calcularGanancia(equipo equipoGanador) {
        if (equipoGanador.equals(equipoApostado)) {
            return monto * 2;
        } else {
            return 0;
        }
    }
}
