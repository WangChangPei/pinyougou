package cn.itcast.core.service;


import cn.itcast.core.mapper.good.BrandDao;
import cn.itcast.core.mapper.good.GoodsDao;
import cn.itcast.core.mapper.order.OrderDao;
import cn.itcast.core.pojo.good.Brand;
import cn.itcast.core.pojo.good.Goods;
import cn.itcast.core.pojo.order.Order;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("all")
@Service
public class FileServiceImpl implements FileService{


@Autowired
private GoodsDao goodsDao;
@Autowired
private OrderDao orderDao;
@Autowired
private BrandDao brandDao;

    @Override
    public byte[] exportGoods() throws IOException {
      List<Goods> list = goodsDao.selectByExample(null);
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFWorkbook workbook = createGoodsExcel(wb,list);
        ByteArrayOutputStream outBtye = new ByteArrayOutputStream();

        workbook.write(outBtye);
        return outBtye.toByteArray();
    }

    @Override
    public byte[] exportOrder() throws Exception {
        List<Order> List = orderDao.selectByExample(null);
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFWorkbook workbook = createOrderExcel(wb,List);
        ByteArrayOutputStream outBtye = new ByteArrayOutputStream();

        workbook.write(outBtye);
        return outBtye.toByteArray() ;
    }

    @Override
    public void updateFileBrand(List list) throws Exception {
        Brand brand = new Brand();
        brand.setName(list.get(0).toString());
        brand.setFirstChar(list.get(1).toString());


        brandDao.insertSelective(brand);
    }

    //导出的是Order的数据
    public HSSFWorkbook createOrderExcel(HSSFWorkbook wb,List<Order> list) {

        HSSFSheet sheet1 = wb.createSheet("导出订单数据");
        sheet1.setDefaultColumnWidth(20);
        sheet1.setDefaultRowHeightInPoints(12);
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("微软雅黑");

        HSSFCellStyle style = wb.createCellStyle();
        //   style.setAlignment(HorizontalAlignment.CENTER); // 居中
        style.setFont(font);
        HSSFCell cell;
        //    CellRangeAddress callRangeAddress = new CellRangeAddress(0, 0, 0, 5);// 起始行,结束行,起始列,结束列
        //     sheet.addMergedRegion(callRangeAddress);

        HSSFRow row = sheet1.createRow(0);

        row = sheet1.createRow(0);
        cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("订单id");

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("实付金额");

        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("支付类型");

        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("状态");

        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("订单创建时间");

        cell = row.createCell(5);
        cell.setCellStyle(style);
        cell.setCellValue("订单更新时间");

        cell = row.createCell(6);
        cell.setCellStyle(style);
        cell.setCellValue("用户id");

        cell = row.createCell(7);
        cell.setCellStyle(style);
        cell.setCellValue("收货人地区名称(省，市，县)街道");

        cell = row.createCell(8);
        cell.setCellStyle(style);
        cell.setCellValue("收货人手机");

        cell = row.createCell(9);
        cell.setCellStyle(style);
        cell.setCellValue("收货人");

        cell = row.createCell(10);
        cell.setCellStyle(style);
        cell.setCellValue("商家ID");

        for (int i = 0; i < list.size(); i++) {
            Order order = list.get(i);

            row = sheet1.createRow(i+1);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(order.getOrderId().toString());

            cell = row.createCell(1);
            cell.setCellStyle(style);

            cell.setCellValue(order.getPayment().toString());

            cell = row.createCell(2);
            cell.setCellStyle(style);
            cell.setCellValue(order.getPaymentType());

            cell = row.createCell(3);
            cell.setCellStyle(style);
            cell.setCellValue(order.getStatus());

            cell = row.createCell(4);
            cell.setCellStyle(style);
            cell.setCellValue(order.getCreateTime().toString());

            cell = row.createCell(5);
            cell.setCellStyle(style);
            cell.setCellValue(order.getUpdateTime().toString());

            cell = row.createCell(6);
            cell.setCellStyle(style);
            cell.setCellValue(order.getUserId().toString());

            cell = row.createCell(7);
            cell.setCellStyle(style);
            cell.setCellValue(order.getReceiverAreaName());

            cell = row.createCell(8);
            cell.setCellStyle(style);
            cell.setCellValue(order.getReceiverMobile());

            cell = row.createCell(9);
            cell.setCellStyle(style);
            cell.setCellValue(order.getReceiver());

            cell = row.createCell(10);
            cell.setCellStyle(style);
            cell.setCellValue(order.getSellerId().toString());


        }

        return wb;
    }


    //导出的是商品数据
    public HSSFWorkbook createGoodsExcel(HSSFWorkbook wb,List<Goods> list) {

        HSSFSheet sheet = wb.createSheet("导出商品数据");
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(12);
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("微软雅黑");

        HSSFCellStyle style = wb.createCellStyle();
        //   style.setAlignment(HorizontalAlignment.CENTER); // 居中
        style.setFont(font);
        HSSFCell cell;
        //    CellRangeAddress callRangeAddress = new CellRangeAddress(0, 0, 0, 5);// 起始行,结束行,起始列,结束列
        //     sheet.addMergedRegion(callRangeAddress);

        HSSFRow row = sheet.createRow(0);

        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("id");

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("商家ID");

        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("SPU名");

        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("状态");

        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("品牌id");

        cell = row.createCell(5);
        cell.setCellStyle(style);
        cell.setCellValue("副标题");

        cell = row.createCell(6);
        cell.setCellStyle(style);
        cell.setCellValue("category1Id");

        cell = row.createCell(7);
        cell.setCellStyle(style);
        cell.setCellValue("category2Id");

        cell = row.createCell(8);
        cell.setCellStyle(style);
        cell.setCellValue("category3Id");

        cell = row.createCell(9);
        cell.setCellStyle(style);
        cell.setCellValue("price");

        for (int i = 0; i < list.size(); i++) {
            Goods goods = list.get(i);

            row = sheet.createRow(i+1);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(goods.getId().toString());

            cell = row.createCell(1);
            cell.setCellStyle(style);
            cell.setCellValue(goods.getSellerId());

            cell = row.createCell(2);
            cell.setCellStyle(style);
            cell.setCellValue(goods.getGoodsName());

            cell = row.createCell(3);
            cell.setCellStyle(style);
            cell.setCellValue(goods.getAuditStatus());

            cell = row.createCell(4);
            cell.setCellStyle(style);
            cell.setCellValue(goods.getBrandId());

            cell = row.createCell(5);
            cell.setCellStyle(style);
            cell.setCellValue(goods.getCaption());

            cell = row.createCell(6);
            cell.setCellStyle(style);
            cell.setCellValue(goods.getCategory1Id());

            cell = row.createCell(7);
            cell.setCellStyle(style);
            cell.setCellValue(goods.getCategory2Id());

            cell = row.createCell(8);
            cell.setCellStyle(style);
            cell.setCellValue(goods.getCategory3Id());

            cell = row.createCell(9);
            cell.setCellStyle(style);
            cell.setCellValue(goods.getPrice().toString());


        }

        return wb;
    }
}
