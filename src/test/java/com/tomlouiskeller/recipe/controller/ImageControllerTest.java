package com.tomlouiskeller.recipe.controller;

import com.tomlouiskeller.recipe.service.RecipeService;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTest {

    private ImageController imageController;

    @Mock
    RecipeService recipeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        imageController = new ImageController(recipeService);
    }

//    @Test
//    public void showImageReturnsByteArray() {
//        Long id = 123L;
//    }
//
//    @Test
//    public void initCreationFormReturns200() throws Exception {
//        Long id = 482L;
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
//        mockMvc.perform(get("/recipe/" + id + "/image"))
//                .andExpect(status().isOk());
//    }

    // -- uploadImage -- //


    @Test
    public void uploadImage() throws Exception {
        Long id = 323L;

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(imageController)
//                .setControllerAdvice(new ControllerExceptionHandler())
                .build();

        // TODO: Could try it with a real image instead
        byte[] bytes = "Spring Framework Guru".getBytes();
        MockMultipartFile multipartFile = new MockMultipartFile("image", "testing.txt", "text/plain",
                bytes);

        mockMvc.perform(multipart("/recipe/" + id + "/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/" + id + "/show/")) // TODO: take out either header() or view()
                .andExpect(header().string("Location", "/recipe/" + id + "/show/"));

        Byte[] bytesAsObject = ArrayUtils.toObject(bytes);
        verify(recipeService, times(1)).saveImage(id, bytesAsObject);
    }
}
