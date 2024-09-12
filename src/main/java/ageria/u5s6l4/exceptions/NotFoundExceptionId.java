package ageria.u5s6l4.exceptions;

import java.util.UUID;

public class NotFoundExceptionId extends RuntimeException{
    public NotFoundExceptionId(UUID id){
        super("L'oggetto con id: " + id + " non e' stato trovato");
    }


}
