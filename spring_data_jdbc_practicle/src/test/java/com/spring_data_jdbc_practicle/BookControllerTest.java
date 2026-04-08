package com.spring_data_jdbc_practicle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring_data_jdbc_practicle.controlller.BookController;
import com.spring_data_jdbc_practicle.entity.Book;
import com.spring_data_jdbc_practicle.service.BookService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService service;

    @Autowired
    private ObjectMapper objectMapper;

    //  CREATE TEST
    @Test
    void testAddBook() throws Exception {

        Book book = new Book(null, "Java", "James", 2020);

        doNothing().when(service).addBook(book);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Book added successfully"));
    }

    //  GET ALL TEST
    @Test
    void testGetAllBooks() throws Exception {

        Book book = new Book(1L, "Java", "James", 2020);

        when(service.getAllBooks()).thenReturn(List.of(book));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Java"))
                .andExpect(jsonPath("$[0].author").value("James"));
    }

    //  GET BY ID TEST (SUCCESS)
    @Test
    void testGetBookById() throws Exception {

        Book book = new Book(1L, "Java", "James", 2020);

        when(service.getBook(1L)).thenReturn(book);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Java"));
    }

    //  GET BY ID TEST (NOT FOUND)
    @Test
    void testGetBookById_NotFound() throws Exception {

        when(service.getBook(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Book not found"));
    }

    //  UPDATE TEST
    @Test
    void testUpdateBook() throws Exception {

        Book book = new Book(null, "Spring", "Rod", 2021);

        doNothing().when(service).updateBook(book);

        mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(content().string("Book updated successfully"));
    }

    //  DELETE TEST
    @Test
    void testDeleteBook() throws Exception {

        doNothing().when(service).deleteBook(1L);

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book deleted successfully"));
    }
}
