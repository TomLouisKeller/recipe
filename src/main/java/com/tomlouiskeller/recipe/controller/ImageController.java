package com.tomlouiskeller.recipe.controller;


import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
@RequestMapping("/recipe/{id}/image")
public class ImageController {

    RecipeService recipeService;

    public ImageController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping // TODO: Write tests for this // TODO: ErrorHandling // TODO: Log exceptions
    public void showImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Recipe recipe = recipeService.findById(id);
        if (recipe.getImage() != null) {
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(recipe.getImage());
            IOUtils.copy(is, response.getOutputStream());
        }
    }

    @PostMapping // TODO: ErrorHandling // TODO: Log exceptions // TODO: Reject non-image files
    public String uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) throws IOException {
        log.info("uploadImage");
        recipeService.saveImage(id, image.getBytes());
        return "redirect:/recipe/" + id + "/show/";
    }
}
