package com.snut.book.SERVICE;

import com.snut.book.DAO.BookRepository;
import com.snut.book.POJO.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceimpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAll(String id) {
        return bookRepository.findById(id);
    }
}
