package vehiculo.garajes;

import vehiculo.vehiculos.*;
import vehiculo.excepciones.*;
import java.util.ArrayList;

public class RedDeGarajes {
    private ArrayList<Garaje> garajes;

    public RedDeGarajes() {
        this.garajes = new ArrayList<>();
    }

    public void agregarGaraje(Garaje garaje) {
        garajes.add(garaje);
    }

    public void eliminarGaraje(String nombre) {
        garajes.removeIf(garaje -> garaje.getNombre().equalsIgnoreCase(nombre));
    }

    public Garaje buscarGaraje(String nombre) {
        return garajes.stream()
                .filter(garaje -> garaje.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public Vehiculo buscarVehiculoGlobal(String placa) throws VehiculoYaRegistradoException {
        for (Garaje garaje : garajes) {
            for (Vehiculo vehiculo : garaje.getVehiculos()) {
                if (vehiculo.getPlaca().equals(placa)) {
                    throw new VehiculoYaRegistradoException("Vehículo ya registrado en otro garaje.");
                }
            }
        }
        return null;
    }

    public double calcularRecaudoTotal() {
        return garajes.stream().mapToDouble(Garaje::calcularIngresosMensuales).sum();
    }

    public ArrayList<Garaje> getGarajes() {
        return garajes;
    }
}
