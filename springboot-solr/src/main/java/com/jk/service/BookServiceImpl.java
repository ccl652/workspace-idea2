package com.jk.service;

import com.jk.mapper.BookMapper;
import com.jk.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2019/11/11
 * @since 1.0.0
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void saveBook(Books book) {
        bookMapper.saveBook(book);
    }

    @Override
    public void deleteBook(String id) {
        bookMapper.deleteBook(id);
    }

    @Override
    public void deleteBooks(String ids) {
        bookMapper.deleteBooks(ids);
    }

    @Override
    public void updateBook(Books book) {
        bookMapper.updateBook(book);
    }
}