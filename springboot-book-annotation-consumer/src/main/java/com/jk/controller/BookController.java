package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.Books;
import com.jk.service.BookService;
import com.jk.util.ExportExcel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Reference
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

    @RequestMapping("exportFile")
    public void exportFile(HttpServletResponse response,String[] rowName){

        List<Books> list= new ArrayList<Books>();
        try {
            list = bookService.queryBook();

            //定义表格的标题
            String title ="图书信息列表";
            //定义列名
           // String[] rowName={"编号","图书名字","价格","类型","日期"};
            //定义工具类要的数据
            List<Object[]>  dataList = new ArrayList<Object[]>();

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");


            //循环数据把数据存放到 List<Object[]>
            for (Books book:list) {
                Object[] obj=new Object[rowName.length];
                for (int i=0;i<rowName.length;i++){
                    if(rowName[i].contains("图书编号")){
                        obj[i]=book.getBookid();
                    }
                    if(rowName[i].contains("图书名称")){
                        obj[i]=book.getBookname();
                    }
                    if(rowName[i].contains("图书价格")){
                        obj[i]=book.getBookprice();
                    }
                    if(rowName[i].contains("图书类型")){
                        if(book.getBooktype()==1){
                            obj[i]="言情";
                        }
                        if(book.getBooktype()==2){
                            obj[i]="动作";
                        }
                        if(book.getBooktype()==3){
                            obj[i]="穿越";
                        }
                    }
                    if(rowName[i].contains("出版日期")){
                        obj[i]=sdf.format(book.getBooktime());
                    }


                }

                dataList.add(obj);
            }
            ExportExcel exportExcel=new ExportExcel(title,rowName,dataList,response);
            exportExcel.export();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("importFile")
    public String importExcel(MultipartFile file, HttpServletResponse response){
        //上传文件的名称
        String fileName = file.getOriginalFilename();
        //生成新的文件名称
        String filePath = "./src/main/resources/templates/fileupload/";

        //创建list集合接收传递的参数
        List<Books> list= new ArrayList<Books>();

        //上传文件
        try {
            uploadFile(file.getBytes(), filePath, fileName);

            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            //通过文件创建流
            FileInputStream fileInputStream = new FileInputStream(filePath+fileName);
            //创建操作excel的对象   因为xls的文件需要HSSFWorkbook  xlsx需要的XSSFWorkbook 因此直接使用workBook对象就行了
            Workbook workbook= WorkbookFactory.create(fileInputStream) ;
            //通过workbook获得sheet页  sheet有可能有多个
            for(int i=0;i<workbook.getNumberOfSheets();i++){
                //创建sheet对象
                Sheet sheetAt = workbook.getSheetAt(i);
                //根绝sheet创建行  row
                for(int j=3;j<sheetAt.getPhysicalNumberOfRows();j++){
                    //创建row对象
                    Row row = sheetAt.getRow(j);

                    //创建对象接收从文件读取的内容
                    Books book=new Books();
                    if( !"".equals(row.getCell(1)) && row.getCell(1)!=null){
                        book.setBookname(row.getCell(1).toString());
                    }
                    if( !"".equals(row.getCell(2)) && row.getCell(2)!=null){
                        book.setBookprice(Double.parseDouble(row.getCell(2).toString()));
                    }

                    if( !"".equals(row.getCell(3)) && row.getCell(3)!=null){
                        if(row.getCell(3).toString().equals("言情")){
                            book.setBooktype(1);
                        }
                        if(row.getCell(3).toString().equals("动作")){
                            book.setBooktype(2);
                        }
                        if(row.getCell(3).toString().equals("穿越")){
                            book.setBooktype(3);
                        }

                    }
                     if( !"".equals(row.getCell(4)) && row.getCell(4)!=null){
                        book.setBooktime(sdf.parse(row.getCell(4).toString()));
                    }

                    list.add(book);
                }

            }
            bookService.addBookList(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "show";
    }



    //上传文件的方法
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    @RequestMapping("toShowCircle")
    public String toShowCircle(){
        return "showCircle";
    }

    @RequestMapping("queryBookCircle")
    @ResponseBody
    public List<Map<String,Object>> queryBookCircle(){
        List<Map<String,Object>> map1 = bookService.queryBookCircle();
        List<Map<String,Object>> map2 = new ArrayList<Map<String,Object>>();
        for(Map<String,Object> map:map1){
            Map<String,Object> map3 = new HashMap<>();
            map3.put("y",map.get("数量"));
            if(map.get("类型").toString().equals("1")){
                map3.put("name","言情");
            }else if(map.get("类型").toString().equals("2")){
                map3.put("name","动作");
            }else {
                map3.put("name","穿越");
            }
            map2.add(map3);
        }

        return map2;
    }

    @RequestMapping("queryBookCircleByYear")
    @ResponseBody
    public List<Map<String,Object>>  queryBookCircleByYear(){
        List<Map<String,Object>> map1 = bookService.queryBookCircleByYear();
        List<Map<String,Object>> map2 = new ArrayList<Map<String,Object>>();
        for(Map<String,Object> map:map1){
            Map<String,Object> map3 = new HashMap<>();
            map3.put("y",map.get("数量"));
            map3.put("name",map.get("月"));
            map2.add(map3);
        }

        return map2;
    }

    @RequestMapping("toShowGraph")
    public String toShowGraph(){
        return "showGraph";
    }


    @RequestMapping("queryBookGraph")
    @ResponseBody
    public List<Map<String,Object>> queryBookGraph(){
        List<Map<String,Object>> map1 = bookService.queryBookGraph();
        List<Map<String,Object>> map2 = new ArrayList<Map<String,Object>>();
        for (Map<String,Object> map:map1) {
            Map<String,Object> map3=new HashMap<>();
            map3.put("name",map.get("time"));
            map3.put("data",map.get("count"));
            map2.add(map3);

        }
        return map2;
    }

    //使用折线图展示每一周的销量 ??
    @RequestMapping("queryBookGraphByWeek")
    @ResponseBody
    public List<Map<String,Object>>  queryBookGraphByWeek(){
             //查询数据库数据
            List<Map<String,Object>> list1 = bookService.queryBookGraphByWeek();

            //后台返回的数据
            List<Map<String,Object>> list2 = new ArrayList<>();

            //判断类型--将对应类型的值放到一个map里
     /*       for(int i=1;i<=3;i++) {
                if (i == 1) {
                    Map<String, Object> map3 = new HashMap<>();
                    map3.


                            put("name", "言情");

                    Integer[] yArr = new Integer[53];
                    for (Map<String, Object> map : list1) {
                        for (int p=2014;p<2020;p++){
                            if(Integer.parseInt(map.get("年").toString())==p){


                            }
                        }

                        if (map.get("类型").toString().equals("1")) {

                            for (int j = 0; j < 53; j++) {
                                Integer a = j + 1;
                                if (j < 10) {
                                    if (map.get("周").toString().equals("0" + a.toString())) {
                                        yArr[j] = Integer.parseInt(map.get("y").toString());
                                    }
                                } else if (j >= 10) {
                                    if (map.get("周").toString().equals(a.toString())) {
                                        yArr[j] = Integer.parseInt(map.get("y").toString());
                                    }
                                }
                            }
                        }
                    }
                    map3.put("data", yArr);
                    list2.add(map3);
                }
            }
*/
        return list1;
    }

    @RequestMapping("toShowAreaGraph")
    public String toShowAreaGraph(){
        return "showAreaGraph";
    }

   //使用面积图展示每个品牌的销量 //销量 ？？
    @RequestMapping("queryBookAreaGraphByType")
    @ResponseBody
    public List<Map<String,Object>>  queryBookAreaGraphByType(){
        List<Map<String,Object>> map1 = bookService.queryBookAreaGraphByType();
        List<Map<String,Object>> map2 = new ArrayList<Map<String,Object>>();
        for (Map<String,Object> map:map1) {
            Map<String,Object> map3=new HashMap<>();
            map3.put("name",map.get("count"));
            map3.put("data",map.get("booktype"));
            map2.add(map3);

        }
        return map2;
    }

    @RequestMapping("toBarGraph")
    public String toBarGraph(){
        return "showBarGraph";
    }

   //4.使用柱状图展示一个月内每一天的销量
    @RequestMapping("queryBookBarGraphByType")
    @ResponseBody
    public List<Map<String,Object>>  queryBookBarGraphByType(){
        List<Map<String,Object>> map1 = bookService.queryBookBarGraphByType();
        List<Map<String,Object>> map2 = new ArrayList<Map<String,Object>>();
        for (Map<String,Object> map:map1) {
            Map<String,Object> map3=new HashMap<>();
            map3.put("name",map.get("count"));
            map3.put("data",map.get("booktype"));
            map2.add(map3);

        }
        return map2;
    }



    /**
     * 1.使用饼状图展示2019年每一个月的销量
     * 2.使用折线图展示每一周的销量
     * 3.使用面积图展示每个品牌的销量
     * 4.使用柱状图展示一个月内每一天的销量
     */




}