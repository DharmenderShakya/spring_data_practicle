package com.spring_data_jdbc_practicle.service;

import java.util.List;

import com.spring_data_jdbc_practicle.entity.Book;

public interface BookService {
	
    public void addBook(Book book) ;

    public List<Book> getAllBooks();

    public Book getBook(Long id);

    public void updateBook(Book book);

    public void deleteBook(Long id);
}
