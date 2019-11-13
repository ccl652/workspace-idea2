package com.jk.service;

import com.jk.model.Books;

public interface BookService {

    void saveBook(Books book);

    void deleteBook(String id);

    void deleteBooks(String ids);

    void updateBook(Books book);
}
