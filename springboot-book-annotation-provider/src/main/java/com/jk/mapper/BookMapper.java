package com.jk.mapper;

import com.jk.model.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookMapper {
    List<Books> queryBook();

    void saveBook(Books book);

    void deleteBooks(@Param("ids") String ids);

    Books selectBookById(@Param("id") Integer id);

    void updateBook(Books book);

    void addBookList(List<Books> list);

    List<Map<String, Object>> queryBookCircle();

    List<Map<String, Object>> queryBookGraph();

    List<Map<String, Object>> queryBookCircleByYear();

    List<Map<String, Object>> queryBookGraphByWeek();

    List<Map<String, Object>> queryBookAreaGraphByType();

    List<Map<String, Object>> queryBookBarGraphByType();
}
