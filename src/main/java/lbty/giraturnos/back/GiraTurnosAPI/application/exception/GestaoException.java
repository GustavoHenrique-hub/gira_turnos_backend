package lbty.giraturnos.back.GiraTurnosAPI.application.exception;

public class GestaoException extends RuntimeException{


    public GestaoException (String message){
        super(message);
    }

    public GestaoException (String message, Throwable cause){
        super(message, cause);
    }
}
