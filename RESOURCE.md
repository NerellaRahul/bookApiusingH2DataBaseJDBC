# Get Books SQL Statement

```java
db.query("SELECT * FROM BOOK", new BookRowMapper());
```

## BookRowMapper Class
```java

package com.example.goodreads.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {

    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("imageUrl")
        );
    }
}

```



# Get Book SQL Statement

```java
String sql = "select * from book where id = ?" ;
Book book = db.queryForObject(sql, new BookRowMapper(), bookId);
return book;
```

# Add Book SQL Statement


```java
db.update("insert into book(name,imageUrl) values (?,?)",book.getName(), book.getImageUrl());
return db.queryForObject("select * from book where name = ? and imageUrl = ?", new BookRowMapper(), book.getName(), book.getImageUrl());
```

# Update Book SQL Statement


```java
if(book.getImageUrl()!=null){
   db.update("update book set imageUrl = ? where id =?", book.getImageUrl(),id);
}
if(book.getName()!=null){
   db.update("update book set name = ? where id =?", book.getName(),id);
}
return getBookById(id);
```

# Delete Book SQL Statement


```java
db.update("delete from book where id = ?", bookId);
```



# Import Statements

## Annotations

 ```java
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
 ```

## Exceptions

 ```java
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
 ```