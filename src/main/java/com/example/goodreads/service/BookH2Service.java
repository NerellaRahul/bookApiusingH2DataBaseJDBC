package com.example.goodreads.service;
// we are importing  repository to implement it 
import com.example.goodreads.repository.BookRepository;
// we are importing  book to ude getters and setters 
import com.example.goodreads.model.Book;
import java.util.*;
// we are importing the jdbc templests and autowired injections 
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
// we are importing the sql based commands and to use the rowmapper
import com.example.goodreads.model.BookServiceMapper;



import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
@Service
public class BookH2Service implements BookRepository 
{

  @Autowired
  private JdbcTemplate db;
@Override
 public ArrayList<Book> getBooks() {
  
    List<Book> bookList = db.query("select * from book", new BookServiceMapper());
     
        ArrayList<Book> books = new ArrayList<>(bookList);
    return books;
  }


  @Override
  public Book getBookById(int bookId) {
    try{
    Book book = db.queryForObject("select * from book where id = ?", 
new BookServiceMapper(), bookId); 
    return book;
    }
    catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }


  @Override
  public Book addBook(Book book) {
     db.update("insert into book(name, imageUrl) values (?,?)", 
									book.getName(), book.getImageUrl());
                  Book savedBook = db.queryForObject(
"select * from book where name = ? and imageUrl = ?", 
new BookServiceMapper(), book.getName(), book.getImageUrl());
    return savedBook;

  }


  @Override
  public Book updateBook(int bookId, Book book) {
   if(book.getName() != null) {
      db.update("update book set name = ? where id = ?",   
       					book.getName(), bookId);
    }
if(book.getImageUrl() != null){
 db.update("update book set imageUrl = ? where id = ?", 
					book.getImageUrl(), bookId);
    }
    return getBookById(bookId);
  }

 @Override
  public void deleteBook(int bookId) {
     db.update("delete from book where id = ?", bookId);

      
  }







}