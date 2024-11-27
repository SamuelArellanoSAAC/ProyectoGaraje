package vehiculo.vehiculos;

public class Camion extends Vehiculo {
    private int numeroEjes;
    private String tipoCamion; // Sencillo o Doble
    private double capacidadCarga;

    public Camion(String marca, double precio, int cilindraje, int numeroEjes, String tipoCamion, double capacidadCarga) {
        super(marca, precio, cilindraje);
        this.numeroEjes = numeroEjes;
        this.tipoCamion = tipoCamion;
        this.capacidadCarga = capacidadCarga;
        calcularImpuestoCirculacion();
        calcularCuotaMesGaraje();
    }

    @Override
    public void calcularImpuestoCirculacion() {
        double impuestoBase = this.getPrecio() * 0.09 + (10 * Math.floor(capacidadCarga / 5));
        this.setImpuestoCirculacion(impuestoBase);
    }

    private void calcularCuotaMesGaraje() {
        if (tipoCamion.equalsIgnoreCase("Sencillo")) {
            this.setCuotaMesGaraje(this.getCuotaMesGaraje() * 1.75);
        } else if (tipoCamion.equalsIgnoreCase("Doble")) {
            this.setCuotaMesGaraje(this.getCuotaMesGaraje() * 2.25);
        }
    }
}
