package com.zy.exportfile.control;

import com.zy.exportfile.constants.SheetInfo;
import com.zy.exportfile.entity.Book;
import com.zy.exportfile.entity.User;
import com.zy.exportfile.service.impl.BookInfoServiceImpl;
import com.zy.exportfile.service.impl.UserInfoServiceImpl;
import com.zy.exportfile.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

@Controller
public class ReportFormController {

    @Autowired
    UserInfoServiceImpl userInfoService;

    @Autowired
    BookInfoServiceImpl bookInfoService;

    @RequestMapping(value = "/index")
    @ResponseBody
    public String index(){
        return "test success11";
    }
    /**
     * 导出报表
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // excel文件名
        String fileName = SheetInfo.FILE_NAME;
        // excel标题
        String[] title = SheetInfo.USER_TITLE;
        // sheet名
        String sheetName = SheetInfo.USER_SHEET_NAME;
        // 用户信息列表
        List<User> users = userInfoService.getUserInfo();

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName,title,users,null);

        title = SheetInfo.BOOK_TITLE;
        sheetName = SheetInfo.BOOK_SHEET_NAME;
        // 书籍信息列表
        List<Book> books = bookInfoService.getBookInfo();
        HSSFWorkbook wb2 = ExcelUtil.getHSSFWorkbook(sheetName,title,books,wb);

        // 响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            wb2.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 发送响应流方法
    public void setResponseHeader(HttpServletResponse response,String fileName) {
        try {
            fileName = new String(fileName.getBytes(),"ISO8859-1");
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
































