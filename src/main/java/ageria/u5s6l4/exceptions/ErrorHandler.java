package ageria.u5s6l4.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundExceptionId.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFoundExId(NotFoundExceptionId ex){
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    };

    @ExceptionHandler(NotFoundExceptionName.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFoundExName(NotFoundExceptionName ex){
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    };

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleBadRequest(BadRequestException ex){
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    };

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleInternalServerError(Exception ex){
        ex.printStackTrace();
        return new ErrorsPayload("Internal Server Error", LocalDateTime.now());
    };

}
