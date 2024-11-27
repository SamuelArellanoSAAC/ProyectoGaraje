package vehiculo;

import vehiculo.garajes.*;
import vehiculo.vehiculos.*;
import vehiculo.excepciones.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RedGarajes redGarajes = new RedGarajes();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Crear Garaje");
            System.out.println("2. Eliminar Garaje");
            System.out.println("3. Ingresar Vehículo");
            System.out.println("4. Retirar Vehículo");
            System.out.println("5. Generar Informes");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre del Garaje: ");
                    String nombre = scanner.next();
                    System.out.print("Capacidad Total: ");
                    int capacidad = scanner.nextInt();
                    Garaje garaje = new Garaje(nombre, capacidad);
                    redGarajes.agregarGaraje(garaje);
                    System.out.println("Garaje creado con éxito.");
                }
                case 2 -> {
                    System.out.print("Nombre del Garaje a eliminar: ");
                    String nombre = scanner.next();
                    redGarajes.eliminarGaraje(nombre);
                    System.out.println("Garaje eliminado con éxito.");
                }
                case 3 -> {
                    System.out.print("Nombre del Garaje: ");
                    String garajeNombre = scanner.next();
                    Garaje garaje = redGarajes.buscarGaraje(garajeNombre);
                    if (garaje == null) {
                        System.out.println("Garaje no encontrado.");
                        break;
                    }

                    System.out.print("Tipo de Vehículo (Moto, Auto, Camion, Camioneta): ");
                    String tipo = scanner.next();
                    System.out.print("Marca: ");
                    String marca = scanner.next();
                    System.out.print("Precio: ");
                    double precio = scanner.nextDouble();
                    System.out.print("Cilindraje: ");
                    int cilindraje = scanner.nextInt();

                    try {
                        Vehiculo vehiculo = switch (tipo.toLowerCase()) {
                            case "moto" -> new Moto(marca, precio, cilindraje, false);
                            case "auto" -> new Auto(marca, precio, cilindraje, true, true);
                            case "camion" -> new Camion(marca, precio, cilindraje, 2, "Sencillo", 10);
                            case "camioneta" -> new Camioneta(marca, precio, cilindraje, "SUV", 5, false);
                            default -> throw new IllegalArgumentException("Tipo de vehículo inválido.");
                        };
                        garaje.ingresarVehiculo(vehiculo);
                        System.out.println("Vehículo ingresado con éxito.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.print("Nombre del Garaje: ");
                    String garajeNombre = scanner.next();
                    Garaje garaje = redGarajes.buscarGaraje(garajeNombre);
                    if (garaje == null) {
                        System.out.println("Garaje no encontrado.");
                        break;
                    }

                    System.out.print("Placa del Vehículo: ");
                    String placa = scanner.next();
                    try {
                        garaje.retirarVehiculo(placa);
                        System.out.println("Vehículo retirado con éxito.");
                    } catch (VehiculoNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 5 -> {
                    System.out.println("Recaudo total de la red: " + redGarajes.calcularRecaudoTotal());
                }
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }
}
