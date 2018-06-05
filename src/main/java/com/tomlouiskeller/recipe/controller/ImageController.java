package com.tomlouiskeller.recipe.controller;


import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
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

    @GetMapping // TODO: ErrorHandling // TODO: Log exceptions
    public void showImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Recipe recipe = recipeService.findById(id);
        if (recipe.getImage() != null) {
            response.setContentType("image/jpeg");
            Byte[] image = recipe.getImage();
            byte[] bytes = ArrayUtils.toPrimitive(image);
            InputStream is = new ByteArrayInputStream(bytes);
            IOUtils.copy(is, response.getOutputStream());
        }
    }

    @PostMapping // TODO: ErrorHandling // TODO: Log exceptions // TODO: Reject non-image files
    public String uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) throws IOException {
        byte[] bytes = image.getBytes();
        Byte[] bytesAsObject = ArrayUtils.toObject(bytes);
        recipeService.saveImage(id, bytesAsObject);
        return "redirect:/recipe/" + id + "/show/";
    }
}
