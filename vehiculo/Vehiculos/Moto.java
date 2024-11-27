package vehiculo.vehiculos;

public class Moto extends Vehiculo {
    private boolean tieneSidecar;

    public Moto(String marca, double precio, int cilindraje, boolean tieneSidecar) {
        super(marca, precio, cilindraje);
        this.tieneSidecar = tieneSidecar;
        calcularImpuestoCirculacion();
    }

    @Override
    public void calcularImpuestoCirculacion() {
        double impuestoBase = this.getPrecio() * 0.02;
        if (tieneSidecar) {
            impuestoBase *= 1.10; // Incremento del 10%
        }
        this.setImpuestoCirculacion(impuestoBase);
    }
}
