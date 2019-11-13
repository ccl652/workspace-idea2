package com.jk.model;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    private Double priceend;

    private Date booktime;

    private  String bookshow;

    private Date dateend;

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

    @JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8",locale="zh")
    public Date getBooktime() {
        return booktime;
    }

    public void setBooktime(Date booktime) {
        this.booktime = booktime;
    }

    public Date getDateend() {
        return dateend;
    }

    public void setDateend(Date dateend) {
        this.dateend = dateend;
    }

    public String getBookshow() {
        return bookshow;
    }

    public void setBookshow(String bookshow) {
        this.bookshow = bookshow;
    }

    public Double getPriceend() {
        return priceend;
    }

    public void setPriceend(Double priceend) {
        this.priceend = priceend;
    }

    @Override
    public String toString() {
        return "Books{" +
                "bookid=" + bookid +
                ", bookname='" + bookname + '\'' +
                ", bookprice=" + bookprice +
                ", booktype=" + booktype +
                ", priceend='" + priceend + '\'' +
                ", booktime=" + booktime +
                ", bookshow='" + bookshow + '\'' +
                ", dateend=" + dateend +
                '}';
    }
}