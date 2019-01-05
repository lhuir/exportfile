package com.zy.exportfile.util;

import com.zy.exportfile.constants.SheetInfo;
import com.zy.exportfile.entity.Book;
import com.zy.exportfile.entity.User;
import org.apache.poi.hssf.usermodel.*;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExcelUtil {

    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String []title, Object object, HSSFWorkbook wb) {

        // 创建HSSWorkbook，对应一个Excel文件
        if (wb == null) {
           wb = new HSSFWorkbook();
        }

        //在workbook中添加一个sheet，对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 在sheet中添加表头第0行
        HSSFRow row = sheet.createRow(0);

        // 设置Excel样式 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 声明列对象
        HSSFCell cell = null;

        // 创建标题
        for (int i = 0;i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        if (SheetInfo.USER_SHEET_NAME.equals(sheetName)) {
            List<User> users = (List<User>) object;
            // 创建内容
            for (int i = 0; i < users.size(); i++) {
                row = sheet.createRow(i + 1);
                User user = users.get(i);
                // 将内容按顺序赋给对应的列对象
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getName());
                row.createCell(2).setCellValue(user.getNumber());
                row.createCell(3).setCellValue(user.getCardNumber());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String birthDate = simpleDateFormat.format(user.getBirthDate());
                row.createCell(4).setCellValue(birthDate);
            }
        }
        if (SheetInfo.BOOK_SHEET_NAME.equals(sheetName)){
            List<Book> books = (List<Book>) object;
            // 创建内容
            for (int i = 0; i < books.size(); i++) {
                row = sheet.createRow(i + 1);
                Book book = books.get(i);
                // 将内容按顺序赋给对应的列对象
                row.createCell(0).setCellValue(book.getId());
                row.createCell(1).setCellValue(book.getName());
            }
        }
        return wb;
    }


}


























