package com.tomlouiskeller.recipe.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// TODO: Add exception handling
@Controller
public class IndexController {

    @GetMapping({"", "/", "/index", "/recipe", "/recipe/list"})
    public String index(){
        return "redirect:/recipe/list/all";
    }

}
