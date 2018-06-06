package com.tomlouiskeller.recipe.controller;

import com.tomlouiskeller.recipe.configuration.GeneralConfiguration;
import com.tomlouiskeller.recipe.exception.RecipeNotFoundException;
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


    @ResponseStatus(HttpStatus.NOT_FOUND) // TODO: use variables like in handleNumberFormatException
    @ExceptionHandler(RecipeNotFoundException.class)
    public ModelAndView handleRecipeNotFoundException(Exception exception){
        String view = "error/generic_exception";
        String title = "Recipe has not been found.";
        String text = "Oops, we couldn't find the recipe, you were looking for.";
        String exceptionMessage = getExceptionMessage(exception);

        logException(exception);

        ModelAndView modelAndView = createModelAndView(view, title, text, exceptionMessage);

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception exception){
        String view = "error/generic_exception";
        String title = "NumberFormatException";
        String text = "Couldn't convert letters into numbers. Who would have guessed?";
        String exceptionMessage = getExceptionMessage(exception);

        logException(exception);

        ModelAndView modelAndView = createModelAndView(view, title, text, exceptionMessage);

        return modelAndView;
    }

    private void logException(Exception exception) {
        log.error("Handling " + exception.getClass().getSimpleName());
        log.error(exception.getMessage());
    }

    private String getExceptionMessage(Exception exception) {
        String exceptionMessage;
        if ("development".equals(generalConfiguration.getSpringProfile()))
            exceptionMessage = exception.getMessage();
        else
            exceptionMessage = " ";
        return exceptionMessage;
    }

    private ModelAndView createModelAndView(String view, String title, String text, String exceptionMessage) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", title);
        modelAndView.addObject("text", text);
        modelAndView.addObject("exceptionMessage", exceptionMessage);
        modelAndView.setViewName(view);
        return modelAndView;
    }
}
