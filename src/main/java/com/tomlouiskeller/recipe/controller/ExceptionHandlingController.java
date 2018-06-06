package com.tomlouiskeller.recipe.controller;

import com.tomlouiskeller.recipe.configuration.GeneralConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {

    private GeneralConfiguration generalConfiguration;

    public ExceptionHandlingController(GeneralConfiguration generalConfiguration) {
        this.generalConfiguration = generalConfiguration;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception exception){
        String view = "error/generic_exception";
        String title = "Nope";
        String text = "Couldn't convert letters into numbers. Who would have guessed?";
        String exceptionMessage;
        if ("development".equals(generalConfiguration.getSpringProfile()))
            exceptionMessage = exception.getMessage();
        else
            exceptionMessage = " ";


        log.error(generalConfiguration.getSpringProfile());
        log.error("Handle NumberFormatException");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("title", title);
        modelAndView.addObject("text", text);
        modelAndView.addObject("exceptionMessage", exceptionMessage);
        modelAndView.setViewName(view);

        return modelAndView;
    }



//    java.lang.NumberFormatException

}
