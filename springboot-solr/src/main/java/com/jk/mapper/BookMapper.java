package com.jk.mapper;

import com.jk.model.Books;
import org.apache.ibatis.annotations.Param;

public interface BookMapper {
    void saveBook(Books book);

    void deleteBook(@Param("id") String id);

    void deleteBooks(@Param("ids") String ids);

    void updateBook(Books book);
}
