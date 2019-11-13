package com.jk.controller;

import com.jk.model.Books;
import com.jk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈BookController〉
 *
 * @author chenchunlan
 * @create 2019/11/5
 * @since 1.0.0
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("toShow")
    public String toShow(){
        return "show";
    }

    @RequestMapping("queryBook")
    @ResponseBody
    public List<Books> queryBook(){
        return bookService.queryBook();
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "addBook";
    }

    @RequestMapping("saveBook")
    @ResponseBody
    public String saveBook(Books book){
        bookService.saveBook(book);
        return  "1";
    }

    @RequestMapping("deleteBooks")
    @ResponseBody
    public String deleteBooks(String ids){
        bookService.deleteBooks(ids);
        return "1";
    }

    @RequestMapping("toEdit")
    public String toEdit(Integer id, Model model){
        Books book = bookService.selectBookById(id);
        model.addAttribute("book",book);
        return "editBook";
    }

    @RequestMapping("updateBook")
    @ResponseBody
    public String updateBook(Books book){
        bookService.updateBook(book);
        return "1";
    }



}