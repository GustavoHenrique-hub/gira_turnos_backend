package lbty.giraturnos.back.GiraTurnosAPI.application.exception;

public class UsuarioException extends RuntimeException{


    public UsuarioException (String message){
        super(message);
    }

    public UsuarioException (String message, Throwable cause){
        super(message, cause);
    }
}