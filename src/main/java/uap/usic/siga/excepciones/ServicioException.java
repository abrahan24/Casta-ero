package uap.usic.siga.excepciones;

/**
 * Rectorado - USIC
 * Fecha: 2019-03-23
 * @author Freddy Morales
 */
public class ServicioException extends Exception{
    public ServicioException (String message){
    super(message);
    }
    
    public ServicioException(String message, Throwable cause){
    super(message,cause);
    }

}
