package com.spring_data_jdbc_practicle.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring_data_jdbc_practicle.entity.Book;
import com.spring_data_jdbc_practicle.repository.BookRepository;
import com.spring_data_jdbc_practicle.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addBook(Book book) {
        repository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Book getBook(Long id) {
        return repository.findById(id);
    }

    @Override
    public void updateBook(Book book) {
        repository.update(book);
    }

    @Override
    public void deleteBook(Long id) {
        repository.delete(id);
    }
}
