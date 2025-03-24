package lbty.giraturnos.back.GiraTurnosAPI.application.exception;

public class VisitaException extends RuntimeException{


    public VisitaException (String message){
        super(message);
    }

    public VisitaException (String message, Throwable cause){
        super(message, cause);
    }
}
