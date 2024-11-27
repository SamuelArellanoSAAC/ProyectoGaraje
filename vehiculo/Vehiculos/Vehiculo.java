package vehiculo.vehiculos;

import vehiculo.excepciones.MatriculaInvalidaException;

public abstract class Vehiculo {
    private String placa;
    private String marca;
    private double precio;
    private int cilindraje;
    private double impuestoCirculacion;
    private double cuotaMesGaraje;
    private static final double CUOTA_BASE = 100.0;

    public Vehiculo(String marca, double precio, int cilindraje) {
        this.placa = null;
        this.marca = marca;
        this.precio = precio;
        this.cilindraje = cilindraje;
        this.cuotaMesGaraje = CUOTA_BASE;
        calcularImpuestoCirculacion();
    }

    public abstract void calcularImpuestoCirculacion();

    public boolean matricular(String matricula) throws MatriculaInvalidaException {
        if (matricula != null && matricula.length() == 6) {
            this.placa = matricula;
            return true;
        } else {
            throw new MatriculaInvalidaException("La matrícula debe tener 6 caracteres.");
        }
    }

    // Getters y Setters
    public String getPlaca() {
        return placa;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public double getCuotaMesGaraje() {
        return cuotaMesGaraje;
    }

    public void setCuotaMesGaraje(double cuotaMesGaraje) {
        this.cuotaMesGaraje = cuotaMesGaraje;
    }

    public void setImpuestoCirculacion(double impuestoCirculacion) {
        this.impuestoCirculacion = impuestoCirculacion;
    }

    public double getImpuestoCirculacion() {
        return impuestoCirculacion;
    }
}
