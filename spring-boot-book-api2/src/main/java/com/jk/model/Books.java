package com.jk.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈Books〉
 *
 * @author chenchunlan
 * @create 2019/11/5
 * @since 1.0.0
 */
public class Books implements Serializable {

    private static final long serialVersionUID = 8506191501202450805L;

    private Integer bookid;

    private String bookname;

    private Double bookprice;

    private Integer booktype;

    private Date booktime;

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Double getBookprice() {
        return bookprice;
    }

    public void setBookprice(Double bookprice) {
        this.bookprice = bookprice;
    }

    public Integer getBooktype() {
        return booktype;
    }

    public void setBooktype(Integer booktype) {
        this.booktype = booktype;
    }

    public Date getBooktime() {
        return booktime;
    }

    public void setBooktime(Date booktime) {
        this.booktime = booktime;
    }

    @Override
    public String toString() {
        return "Books{" +
                "bookid=" + bookid +
                ", bookname='" + bookname + '\'' +
                ", bookprice=" + bookprice +
                ", booktype=" + booktype +
                ", booktime=" + booktime +
                '}';
    }
}