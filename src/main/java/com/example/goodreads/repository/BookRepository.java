package com.example.goodreads.repository;

import java.util.ArrayList;

import com.example.goodreads.model.Book;

public interface BookRepository {
    ArrayList<Book> getBooks();

    Book getBookById(int bookId);
    
    Book addBook(Book book);
    
    Book updateBook(int bookId, Book book);
    
    void deleteBook(int bookId);

}
