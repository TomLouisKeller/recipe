package com.tomlouiskeller.recipe.controller;

import com.tomlouiskeller.recipe.configuration.GeneralConfiguration;
import com.tomlouiskeller.recipe.exception.RecipeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@ControllerAdvice
public class ErrorHandlingController implements ErrorController {

    private final String genericErrorView = "error/generic";
    private final String errorRequestMapping = "/error";
    private GeneralConfiguration generalConfiguration;

    public ErrorHandlingController(GeneralConfiguration generalConfiguration) {
        this.generalConfiguration = generalConfiguration;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND) // TODO: use variables like in handleNumberFormatException
    @ExceptionHandler(RecipeNotFoundException.class)
    public ModelAndView handleRecipeNotFoundException(Exception exception){
        String title = "Recipe has not been found.";
        String text = "Oops, we couldn't find the recipe, you were looking for.";
        String exceptionMessage = getExceptionMessage(exception);

        logException(exception);

        ModelAndView modelAndView = createModelAndView(genericErrorView, title, text, exceptionMessage);

        return modelAndView;
    }

// TODO: Remove this?
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception exception){
        String title = "NumberFormatException";
        String text = "Couldn't convert letters into numbers. Who would have guessed?";
        String exceptionMessage = getExceptionMessage(exception);

        logException(exception);

        ModelAndView modelAndView = createModelAndView(genericErrorView, title, text, exceptionMessage);

        return modelAndView;
    }

    @RequestMapping(errorRequestMapping)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
        String title;
        String text;
        String exceptionMessage = "";

        Integer httpErrorCode = (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
        switch (httpErrorCode) {
            case 400: {
                title = "400 - Bad Request";
                text  = "You entered something odd.";
                break;
            }
            case 401: {
                title = "401 - Unauthorized";
                text  = "You are not authorized to do that.";
                break;
            }
            case 404: {
                title = "404 - Resource not found";
                text  = "Couldn't find what you were looking for.";
                break;
            }
            case 500: {
                title = "500 - Internal Server Error";
                text  = "Something bad happened. Sorry about that.";
                break;
            } default: {
                title = httpErrorCode.toString();
                text  = "Something odd happened. Sorry about that.";
                break;
            }
        }

        log.error("Error with httpStatusCode " + httpErrorCode + " occurred.");

        ModelAndView modelAndView = createModelAndView(genericErrorView, title, text, exceptionMessage);
        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "errorRequestMapping";
    }


    // -- Private Methods -- //

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
