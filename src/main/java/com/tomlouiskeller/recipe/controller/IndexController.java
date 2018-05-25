package com.tomlouiskeller.recipe.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO: Add exception handling

@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index"})
    public String index(){
        return "redirect:/list";
    }

}
