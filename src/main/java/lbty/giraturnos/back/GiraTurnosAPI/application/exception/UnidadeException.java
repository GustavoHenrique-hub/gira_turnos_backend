package lbty.giraturnos.back.GiraTurnosAPI.application.exception;

public class UnidadeException extends RuntimeException{


    public UnidadeException (String message){
        super(message);
    }

    public UnidadeException (String message, Throwable cause){
        super(message, cause);
    }
}
