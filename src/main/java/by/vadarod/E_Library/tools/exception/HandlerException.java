package by.vadarod.E_Library.tools.exception;

import by.vadarod.E_Library.tools.exception.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity accessDeniedException(AuthorizationDeniedException e) throws AuthorizationDeniedException {
        throw e;
    }

    @ExceptionHandler(RefreshTokenException.class)
    ResponseEntity<ErrorResponse> handleRefreshTokenException(RefreshTokenException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setCode(4);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }


    @ExceptionHandler(FileLoadingException.class)
    ResponseEntity<ErrorResponse> handleFileLoadingException(FileLoadingException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setCode(3);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(RoleUseWithUsersException.class)
    ResponseEntity<ErrorResponse> roleUser(RoleUseWithUsersException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setCode(1);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(UserLoginException.class)
    ResponseEntity<ErrorResponse> userLoginErr(UserLoginException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setCode(2);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ErrorResponse validateDto(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ObjectError> objectErrorList = methodArgumentNotValidException.getBindingResult().getAllErrors();
        ErrorResponse errorResponse = new ErrorResponse();
        for (ObjectError objectError : objectErrorList) {
            errorResponse.getMessages().add(objectError.getDefaultMessage());
        }
        errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseEntity<ErrorResponse> otherException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}


