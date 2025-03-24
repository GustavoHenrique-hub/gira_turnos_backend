package lbty.giraturnos.back.GiraTurnosAPI.application.exception;

public class GestaoPorUnidadeException extends RuntimeException{


    public GestaoPorUnidadeException (String message){
        super(message);
    }

    public GestaoPorUnidadeException (String message, Throwable cause){
        super(message, cause);
    }
}
