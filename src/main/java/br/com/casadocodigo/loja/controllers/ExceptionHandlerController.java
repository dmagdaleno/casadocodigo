package br.com.casadocodigo.loja.controllers;

import javax.persistence.NoResultException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(NoResultException.class)
    public String trataProdutoNaoEcontrado(){
        return "erroProdutoNaoEncontrado";
    }
    
    @ExceptionHandler(Exception.class)
    public ModelAndView trataExceptionGenerica(Exception exception){
        exception.printStackTrace();

        ModelAndView modelAndView = new ModelAndView("erro");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
