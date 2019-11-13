package com.jk.controller;

import com.jk.model.Books;
import com.jk.service.BookService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 〈一句话功能简述〉<br>
 * 〈BookController〉
 *
 * @author chenchunlan
 * @create 2019/11/11
 * @since 1.0.0
 */
@Controller
public class BookController {

    @Autowired
    private SolrClient client;

    @Autowired
    private BookService bookService;

    @RequestMapping("toShow")
    public String toShow(){
        return "index";
    }


    /**
     * 综合查询: 在综合查询中, 有按条件查询, 条件过滤, 排序, 分页, 高亮显示, 获取部分域信息
     * @return
     */
    @RequestMapping("queryBook")
    @ResponseBody
    public Map<String, Object> search(Books book,Integer page,Integer rows){

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/DD");


        //返回到前台
        Map<String, Object> map1=new HashMap<>();

        try {
            //存放所有的查询条件
            SolrQuery params = new SolrQuery();

            //查询条件, 这里的 q 对应 下面图片标红的地方
            if(book.getBookname()!=null && !"".equals(book.getBookname())){
                params.set("q", book.getBookname());
            }else {
                params.set("q", "*:*");
            }

            //过滤条件
            if(book.getBookprice()!=null && book.getBookprice()>0 && book.getPriceend()!=null && book.getPriceend()>0){
                params.set("fq", "bookprice:["+book.getBookprice()+" TO "+book.getPriceend()+"]");
            }else {
                params.set("q", "*:*");
            }


            //排序
            params.addSort("bookprice", SolrQuery.ORDER.desc);

            //分页
            if(page==null){
                params.setStart(0);
            }else {
                params.setStart((page-1)*rows);
            }
            if(rows==null){
                params.setRows(5);
            }else {
                params.setRows(rows);
            }


            //默认域
            params.set("df", "bookname");

            //只查询指定域
            //params.set("fl", "id,product_title,product_price");

            //高亮
            //打开开关
            params.setHighlight(true);
            //指定高亮域
            params.addHighlightField("bookname");
            //设置前缀
            params.setHighlightSimplePre("<span style='color:red'>");
            //设置后缀
            params.setHighlightSimplePost("</span>");

            //查询后返回的对象
            QueryResponse queryResponse = client.query("core1",params);
            //查询后返回的对象 获得真正的查询结果
            SolrDocumentList results = queryResponse.getResults();
            //查询的总条数
            long numFound = results.getNumFound();

            System.out.println(numFound);

            //获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
            Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();

            //创建list集合接收我们循环的参数
            List<Books> list1 =new ArrayList<>();
            for (SolrDocument result : results) {

                Books book2=new Books();
                String highFile="";

                Map<String, List<String>> map = highlight.get(result.get("id"));
                List<String> list = map.get("bookname");
                if(list==null){
                    highFile= result.get("bookname").toString();
                }else {
                    highFile=list.get(0);
                }

                book2.setBookid(Integer.parseInt(result.get("id").toString()));
                book2.setBooktype(Integer.parseInt(result.get("booktype").toString()));
              //  book2.setBooktime(new Date(result.get("booktime").toString()));
                //book2.setBookshow(result.get("bookshow").toString());
                book2.setBookprice(Double.parseDouble(result.get("bookprice").toString()));
                book2.setBookname(highFile);

                list1.add(book2);
            }
            map1.put("total",numFound);
            map1.put("rows",list1);
            return map1;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "addBook";
    }

    @RequestMapping("saveBook")
    @ResponseBody
    public String saveBook(Books book){

        if(book.getBookid()!=null && book.getBookid()>0){
            //修改
            bookService.updateBook(book);
        }else{
            //执行数据库新增
            bookService.saveBook(book);
        }



        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id",book.getBookid().toString());
            doc.setField("bookname", book.getBookname());
            doc.setField("bookprice", book.getBookprice());
            doc.setField("booktype", book.getBooktype());
          //  doc.setField("booktime", book.getBooktime());

            /* 如果spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 collection1 这个参数
             * 下面都是一样的
             */

            client.add("core1", doc);
            //client.commit();
            client.commit("core1");
            return uuid;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }

    @RequestMapping("deleteBook")
    @ResponseBody
    public String deleteBook(String id)  {

        bookService.deleteBook(id);

        try {
            client.deleteById("core1",id);
            client.commit("core1");

            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "error";
    }

    @RequestMapping("deleteBooks")
    @ResponseBody
    public String deleteBooks(String ids)  {

        bookService.deleteBooks(ids);

        String[] idsArr = ids.split(",");

        try {
            for (int i=0;i<idsArr.length;i++){

                client.deleteById("core1",idsArr[i]);
                client.commit("core1");
                return idsArr[i];

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return "error";
    }

    @RequestMapping("toEdit")
    public String getById(Integer id, HttpServletRequest request) throws Exception {
        SolrDocument doc = client.getById("core1", String.valueOf(id));
       /* document.toString();
        System.out.println(document);*/
        Books book = new Books();
        book.setBookid(Integer.parseInt(doc.get("id").toString()));
        book.setBookname(doc.get("bookname").toString());
        book.setBookprice(Double.parseDouble(doc.get("bookprice").toString()));
        book.setBooktype(Integer.parseInt(doc.get("booktype").toString()));
        request.getSession().setAttribute("book",book);
        return "editBook";
    }






}