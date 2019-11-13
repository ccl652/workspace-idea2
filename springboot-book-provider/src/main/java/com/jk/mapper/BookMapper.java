package com.jk.mapper;

import com.jk.model.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    List<Books> queryBook();

    void saveBook(Books book);

    void deleteBooks(@Param("ids")String ids);

    Books selectBookById(@Param("id") Integer id);

    void updateBook(Books book);

    void addBookList(List<Books> list);
}
