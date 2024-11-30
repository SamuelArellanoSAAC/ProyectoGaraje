
package vehiculo;



import vehiculo.garajes.*;
import vehiculo.vehiculos.*;
import vehiculo.excepciones.*;
import vehiculo.vehiculos.Moto;
import vehiculo.vehiculos.Camioneta;
import vehiculo.vehiculos.Camion;


public class Test {

    public static void main(String[] args) {
        try {
            // 1. Crear un objeto de la clase de la empresa para registrar parqueaderos
            RedDeGarajes redDeGarajes = new RedDeGarajes();

            // 2. Crear los dos garajes con los datos especificados
            Garaje g1 = new Garaje("Parqueadero Neiva", 9);
            Garaje g2 = new Garaje("Parqueadero Ibagué", 10);
            redDeGarajes.agregarGaraje(g1);
            redDeGarajes.agregarGaraje(g2);

            System.out.println("Garajes creados:");
            System.out.println("Garaje 1 (Neiva): Dirección: CRA 34 6 65, Teléfono: 3112548792, Email: g1@empresa.co, Admin: MARIA");
            System.out.println("Garaje 2 (Ibagué): Dirección: CLL65 89 87, Teléfono: 3256987412, Email: g2@empresa.co, Admin: PEDRO");

            // 3. Crear 5 objetos por cada tipo de vehículo
            Vehiculo[] autos = {
                new Auto("Toyota", 20000, 1500, true, true),
                new Auto("Honda", 18000, 1400, false, true),
                new Auto("Mazda", 25000, 1600, true, false),
                new Auto("Chevrolet", 22000, 1700, false, false),
                new Auto("Ford", 27000, 1800, true, true)
            };
            Vehiculo[] motos = {
                new Moto("Yamaha", 8000, 250),
                new Moto("Honda", 7500, 200),
                new Moto("Suzuki", 9000, 300),
                new Moto("Kawasaki", 8500, 280),
                new Moto("Ducati", 15000, 400)
            };
            Vehiculo[] camionetas = {
                new Camioneta("Ford", 30000, 2500, true),
                new Camioneta("Chevrolet", 35000, 2600, false),
                new Camioneta("Toyota", 40000, 2700, true),
                new Camioneta("Hyundai", 37000, 2400, false),
                new Camioneta("Kia", 33000, 2300, true)
            };
            Vehiculo[] camiones = {
                new Camion("Volvo", 60000, 5000),
                new Camion("Scania", 70000, 5500),
                new Camion("MAN", 65000, 5300),
                new Camion("Mercedes", 72000, 5800),
                new Camion("DAF", 68000, 5200)
            };

            // 4. Matricular los vehículos
            int matricula = 100000;
            for (Vehiculo v : autos) v.matricular("AUTO" + matricula++);
            for (Vehiculo v : motos) v.matricular("MOTO" + matricula++);
            for (Vehiculo v : camionetas) v.matricular("CMT" + matricula++);
            for (Vehiculo v : camiones) v.matricular("CAM" + matricula++);

            // 5. Agregar los vehículos a los garajes
            for (int i = 0; i < 5; i++) {
                g1.ingresarVehiculo(autos[i]);
                g1.ingresarVehiculo(motos[i]);
                if (i < 3) g2.ingresarVehiculo(camionetas[i]); // Solo 3 en g2
                g2.ingresarVehiculo(camiones[i]);
            }

            // Mostrar vehículos en cada garaje
            System.out.println("\nVehículos en Garaje 1:");
            g1.getVehiculos().forEach(v -> System.out.println(" - " + v.getPlaca()));
            System.out.println("\nVehículos en Garaje 2:");
            g2.getVehiculos().forEach(v -> System.out.println(" - " + v.getPlaca()));

            // 6. Pruebas solicitadas
            System.out.println("\nPruebas:");
            // a. Agregar un vehículo ya registrado
            try {
                g2.ingresarVehiculo(autos[0]); // Debe lanzar excepción
            } catch (EspaciosInsuficientesException | PoliticaExcedidaException e) {
                System.out.println("Error al agregar vehículo ya registrado: " + e.getMessage());
            }

            // b. Manejo de excepciones
            try {
                g1.retirarVehiculo("XYZ999"); // Vehículo inexistente
            } catch (VehiculoNoEncontradoException e) {
                System.out.println("Error: " + e.getMessage());
            }

            // c. Recaudos
            System.out.println("Recaudo mensual Garaje 1: " + g1.calcularIngresosMensuales());
            System.out.println("Recaudo mensual Garaje 2: " + g2.calcularIngresosMensuales());
            System.out.println("Recaudo total red de garajes: " + redDeGarajes.calcularRecaudoTotal());

            // d. Eliminar un garaje con vehículos
            try {
                redDeGarajes.eliminarGaraje("Parqueadero Neiva"); // Debe fallar
            } catch (Exception e) {
                System.out.println("Error al eliminar garaje con vehículos: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
