package com.example.goodreads.model;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookServiceMapper implements RowMapper<Book> {
   public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Book(
         rs.getInt("id"),
         rs.getString("name"),
         rs.getString("imageUrl"));
   }
}
