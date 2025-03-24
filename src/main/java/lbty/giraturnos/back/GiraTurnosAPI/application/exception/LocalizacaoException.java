package lbty.giraturnos.back.GiraTurnosAPI.application.exception;

public class LocalizacaoException extends RuntimeException{


    public LocalizacaoException (String message){
        super(message);
    }

    public LocalizacaoException (String message, Throwable cause){
        super(message, cause);
    }
}
