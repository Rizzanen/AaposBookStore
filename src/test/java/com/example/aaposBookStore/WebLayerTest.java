package com.example.aaposBookStore;

import static org.hamcrest.Matchers.containsString; // Import for containsString
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; // Import for get()
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print; // Import for print()
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; // Import for content()
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; // Import for status()

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {
 @Autowired
 private MockMvc mockMvc;
 @Test
 public void testDefaultMessage() throws Exception {
 this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk()) .andExpect(content().string(containsString("Password")));
 }
}
