package lbty.giraturnos.back.GiraTurnosAPI.application.exception;

public class TurnoException extends RuntimeException{


    public TurnoException (String message){
        super(message);
    }

    public TurnoException (String message, Throwable cause){
        super(message, cause);
    }
}

