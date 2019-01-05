package com.zy.exportfile.service.impl;

import com.zy.exportfile.entity.Book;
import com.zy.exportfile.service.BookInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookInfoServiceImpl implements BookInfoService{

    public List<Book> getBookInfo(){
        // 书籍信息列表
        List<Book> list = new ArrayList();
        /*上限65536*/
        for (int i = 1; i < 655; i++) {
            Book book = new Book();
            book.setId("book"+i);
            book.setName("书籍" + i);
            list.add(book);
        }
        return list;
    }
}
