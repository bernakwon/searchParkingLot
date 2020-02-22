package com.berna.global.error;


import java.util.Date;


import com.berna.global.error.exception.APIErrorException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class CustomExceptionHandler {

	@ExceptionHandler(Exception.class)
    public ErrorInfo handleException(Exception _ex,WebRequest _req) {
		ErrorInfo errorInfo =  new ErrorInfo(new Date(),_ex.getMessage(),_req.getDescription(false));
		return errorInfo;
		
    }
	

	@ExceptionHandler(APIErrorException.class)
    public ErrorInfo handleReservationReduplicationException(APIErrorException _ex,WebRequest _req) {

		ErrorInfo errorInfo =  new ErrorInfo(new Date(),_ex.getMessage(),_req.getDescription(false));
		return errorInfo;

    }


}
