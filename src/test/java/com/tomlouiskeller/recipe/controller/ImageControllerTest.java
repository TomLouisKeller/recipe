package com.tomlouiskeller.recipe.controller;

import com.tomlouiskeller.recipe.domain.Recipe;
import com.tomlouiskeller.recipe.service.RecipeService;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    // -- showImage -- //

    @Test
    public void showImageReturns200() throws Exception{
        Long id = 123L;
        Recipe recipe = mock(Recipe.class);
        when(recipeService.findById(id)).thenReturn(recipe);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/" + id + "/image"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void showImageReturnsBytes() throws Exception {
        Long id = 3L;
        Recipe recipe = new Recipe();
        recipe.setId(id);

        Byte[] bytesAsObject = ArrayUtils.toObject("showImage".getBytes());
        recipe.setImage(bytesAsObject);

        when(recipeService.findById(id)).thenReturn(recipe);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(imageController)
//                .setControllerAdvice(new ControllerExceptionHandler()) //TODO: Add this
                .build();

        MockHttpServletResponse response = mockMvc.perform(
                get("/recipe/" + id + "/image"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();
        Byte[] actual = ArrayUtils.toObject(responseBytes);
        assertArrayEquals(bytesAsObject, actual);
    }

    // TODO: Test for IOException


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

    // TODO: Test for IOException
}
