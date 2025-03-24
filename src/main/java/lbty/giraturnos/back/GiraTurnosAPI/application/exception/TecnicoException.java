package lbty.giraturnos.back.GiraTurnosAPI.application.exception;

public class TecnicoException extends RuntimeException{


    public TecnicoException (String message){
        super(message);
    }

    public TecnicoException (String message, Throwable cause){
        super(message, cause);
    }
}
