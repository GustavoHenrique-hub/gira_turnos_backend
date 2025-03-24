package lbty.giraturnos.back.GiraTurnosAPI.application.exception;

public class ObjetivoDaVisitaException extends RuntimeException{


    public ObjetivoDaVisitaException (String message){
        super(message);
    }

    public ObjetivoDaVisitaException (String message, Throwable cause){
        super(message, cause);
    }
}
