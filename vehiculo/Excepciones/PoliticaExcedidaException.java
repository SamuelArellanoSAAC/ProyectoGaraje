package vehiculo.excepciones;

public class PoliticaExcedidaException extends Exception {
    public PoliticaExcedidaException(String mensaje) {
        super(mensaje);
    }
}