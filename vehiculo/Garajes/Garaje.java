package vehiculo.garajes;

import vehiculo.vehiculos.*;
import vehiculo.excepciones.*;
import java.util.ArrayList;

public class Garaje {
    private String nombre;
    private int capacidadTotal;
    private ArrayList<Vehiculo> vehiculos;

    public Garaje(String nombre, int capacidadTotal) {
        this.nombre = nombre;
        this.capacidadTotal = capacidadTotal;
        this.vehiculos = new ArrayList<>();
    }

    public void ingresarVehiculo(Vehiculo vehiculo) throws EspaciosInsuficientesException, PoliticaExcedidaException {
        if (vehiculos.size() >= capacidadTotal) {
            throw new EspaciosInsuficientesException("El garaje está lleno.");
        }

        long motos = vehiculos.stream().filter(v -> v instanceof Moto).count();
        long camiones = vehiculos.stream().filter(v -> v instanceof Camion).count();

        if (vehiculo instanceof Moto && motos >= capacidadTotal * 0.2) {
            throw new PoliticaExcedidaException("No se permite más del 20% de motos.");
        }

        if (vehiculo instanceof Camion) {
            int maxCamiones = capacidadTotal < 100 ? 10 : 20;
            if (camiones >= maxCamiones) {
                throw new PoliticaExcedidaException("No se permite más de " + maxCamiones + " camiones.");
            }
        }

        vehiculos.add(vehiculo);
    }

    public Vehiculo retirarVehiculo(String placa) throws VehiculoNoEncontradoException {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPlaca().equals(placa)) {
                vehiculos.remove(vehiculo);
                return vehiculo;
            }
        }
        throw new VehiculoNoEncontradoException("Vehículo no encontrado.");
    }

    public int calcularOcupacionPorTipo(Class<? extends Vehiculo> tipo) {
        return (int) vehiculos.stream().filter(tipo::isInstance).count();
    }

    public double calcularIngresosMensuales() {
        return vehiculos.stream().mapToDouble(Vehiculo::getCuotaMesGaraje).sum();
    }

    public int plazasDisponibles() {
        return capacidadTotal - vehiculos.size();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }
}
