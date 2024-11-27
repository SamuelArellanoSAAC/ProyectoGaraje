package vehiculo.vehiculos;

public class Auto extends Vehiculo {
    private boolean tieneRadio;
    private boolean tieneNavegador;

    public Auto(String marca, double precio, int cilindraje, boolean tieneRadio, boolean tieneNavegador) {
        super(marca, precio, cilindraje);
        this.tieneRadio = tieneRadio;
        this.tieneNavegador = tieneNavegador;
        calcularImpuestoCirculacion();
        calcularCuotaMesGaraje();
    }

    @Override
    public void calcularImpuestoCirculacion() {
        double impuestoBase = this.getPrecio() * 0.02;
        if (tieneRadio) {
            impuestoBase += this.getPrecio() * 0.01;
        }
        if (tieneNavegador) {
            impuestoBase += this.getPrecio() * 0.02;
        }
        this.setImpuestoCirculacion(impuestoBase);
    }

    private void calcularCuotaMesGaraje() {
        if (this.getCilindraje() > 2499) {
            this.setCuotaMesGaraje(this.getCuotaMesGaraje() * 1.20);
        }
    }
}
