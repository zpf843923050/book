package com.snut.book.SERVICE;

import com.snut.book.POJO.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BookService {
    List<Book> getAll(String id);
}
