package com.codegym.aspect;

import com.codegym.exception.NotFoundException;
import com.codegym.exception.ServerException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView showError404() {
        return new ModelAndView("/error-404");
    }

    @ExceptionHandler(ServerException.class)
    public ModelAndView showError500() {
        return new ModelAndView("/error-500");
    }
}
