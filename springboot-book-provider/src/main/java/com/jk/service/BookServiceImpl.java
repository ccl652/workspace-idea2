package com.jk.service;

import com.jk.mapper.BookMapper;
import com.jk.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈BookjServiceImpl〉
 *
 * @author Administrator
 * @create 2019/11/5
 * @since 1.0.0
 */
@Service("bookService")
public class BookServiceImpl implements BookService{

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Books> queryBook() {
        return bookMapper.queryBook();
    }

    @Override
    public void saveBook(Books book) {
        bookMapper.saveBook(book);
    }

    @Override
    public void deleteBooks(String ids) {
        bookMapper.deleteBooks(ids);
    }

    @Override
    public Books selectBookById(Integer id) {
        return bookMapper.selectBookById(id);
    }

    @Override
    public void updateBook(Books book) {
        bookMapper.updateBook(book);
    }

    @Override
    public void addBookList(List<Books> list) {
        bookMapper.addBookList(list);
    }

    @Override
    public List<Map<String, Object>> queryBookCircle() {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryBookGraph() {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryBookCircleByYear() {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryBookGraphByWeek() {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryBookAreaGraphByType() {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryBookBarGraphByType() {
        return null;
    }


}