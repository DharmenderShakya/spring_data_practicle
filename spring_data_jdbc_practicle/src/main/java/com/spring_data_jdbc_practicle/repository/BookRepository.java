package com.spring_data_jdbc_practicle.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring_data_jdbc_practicle.entity.Book;

import java.util.List;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CREATE
    public int save(Book book) {
        String sql = "INSERT INTO book (title, author, publication_year) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear());
    }

    // READ ALL
    public List<Book> findAll() {
        String sql = "SELECT * FROM book";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Book(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("publication_year")
                )
        );
    }

    // READ BY ID
    public Book findById(Long id) {
        String sql = "SELECT * FROM book WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new Book(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("publication_year")
                ), id);
    }

    // UPDATE
    public int update(Book book) {
        String sql = "UPDATE book SET title=?, author=?, publication_year=? WHERE id=?";
        return jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear(),
                book.getId());
    }

    // DELETE
    public int delete(Long id) {
        String sql = "DELETE FROM book WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }
}
