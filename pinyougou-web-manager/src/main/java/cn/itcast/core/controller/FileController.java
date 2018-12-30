package cn.itcast.core.controller;


import cn.itcast.core.service.FileService;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.Result;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/file")
public class FileController {


   @Reference
    private FileService fileService;



    @RequestMapping(value = "/exportGoods")
    public Result exportGoods(HttpServletRequest request, HttpServletResponse response) throws Exception {


        try {
            byte[] byteArray = fileService.exportGoods();

            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("商品数据导出.xlsx", "UTF-8"));

            InputStream in = null;
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(byteArray);//向客户端输出，实际是把数据存放在response中，然后web服务器再去response中读取
            return new Result(true,"导出到Excel成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false,"导出到Excel失败");


    }

    @RequestMapping(value = "/exportOrder")
    public void exportOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {


        byte[] bytes =  fileService.exportOrder();
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("订单数据导出.xlsx", "UTF-8"));

        InputStream in = null;
        ServletOutputStream outputStream = response.getOutputStream();


        try {
            outputStream.write(bytes);//向客户端输出，实际是把数据存放在response中，然后web服务器再去response中读取
        }catch (Exception e){
        }

    }


    @RequestMapping(value = "/uploadClientStock")
    public Result updateFile(MultipartFile file) throws Exception {

        try {

            InputStream inputStream = file.getInputStream();

            HSSFWorkbook book = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = book.getSheetAt(0);

            int rowNum = sheet.getLastRowNum();

            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                HSSFRow row = sheet.getRow(i+1);
                //   row.getLastCellNum()
                double value = row.getCell(0).getNumericCellValue();
                String  value1 = row.getCell(1).getStringCellValue();
//                String stringCellValue1 = row.getCell(2).getStringCellValue();

                List list = new ArrayList();
                list.add(value);
                list.add(value1);
//                list.add(stringCellValue1);
                fileService.updateFileBrand(list);
            }
          //fileService.updateFile();
            return  new Result(true,"导入成功");

       }    catch (Exception e) {
            e.printStackTrace();
       }

       return  new Result(false,"导入失败");

   }

}