package vehiculo.vehiculos;

public class Camioneta extends Vehiculo {
    private String tipoServicio; // SUV, Pickup, etc.
    private int numeroPasajeros;
    private boolean tieneRemolque;

    public Camioneta(String marca, double precio, int cilindraje, String tipoServicio, int numeroPasajeros, boolean tieneRemolque) {
        super(marca, precio, cilindraje);
        this.tipoServicio = tipoServicio;
        this.numeroPasajeros = numeroPasajeros;
        this.tieneRemolque = tieneRemolque;
        calcularImpuestoCirculacion();
        calcularCuotaMesGaraje();
    }

    @Override
    public void calcularImpuestoCirculacion() {
        this.setImpuestoCirculacion(this.getPrecio() * 0.05);
    }

    private void calcularCuotaMesGaraje() {
        double incremento = 1.0;

        switch (tipoServicio.toLowerCase()) {
            case "pickup":
            case "carga":
                incremento += 0.45;
                break;
            case "suv":
                incremento += 0.10;
                break;
        }

        if (tieneRemolque) {
            incremento += 0.10;
        }

        this.setCuotaMesGaraje(this.getCuotaMesGaraje() * incremento);
    }
}
