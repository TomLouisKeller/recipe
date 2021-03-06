package com.tomlouiskeller.recipe.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class IndexControllerTest {

    private IndexController indexController;

    @Before
    public void setUp() {
        indexController = new IndexController();
    }

    private MockMvc getMockMvc() {
        return MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test
    public void getAllRecipesMockMvcRootPath() throws Exception{
        MockMvc mockMvc = getMockMvc();
        mockMvc.perform(MockMvcRequestBuilders.get(""))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/list/all"));
    }

    @Test
    public void getAllRecipesMockMvcDashPath() throws Exception{
        MockMvc mockMvc = getMockMvc();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/list/all"));
    }

    @Test
    public void getAllRecipesMockMvcIndexPath() throws Exception{
        MockMvc mockMvc = getMockMvc();
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/list/all"));
    }
}