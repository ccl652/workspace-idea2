package com.jk.service;

import com.jk.model.Books;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Books> queryBook();

    void saveBook(Books book);

    void deleteBooks(String ids);

    Books selectBookById(Integer id);

    void updateBook(Books book);

    void addBookList(List<Books> list);

    List<Map<String, Object>> queryBookCircle();

    List<Map<String, Object>> queryBookGraph();

    List<Map<String, Object>> queryBookCircleByYear();

    List<Map<String, Object>> queryBookGraphByWeek();

    List<Map<String, Object>> queryBookAreaGraphByType();

    List<Map<String, Object>> queryBookBarGraphByType();
}
