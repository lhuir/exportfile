package com.zy.exportfile.service.impl;

import com.zy.exportfile.entity.Book;
import com.zy.exportfile.entity.User;
import com.zy.exportfile.service.UserInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserInfoServiceImpl implements UserInfoService{

    public List<User> getUserInfo(){
        // 用户信息列表
        List<User> list = new ArrayList();
        /*上限65536*/
        for (int i = 1; i < 655; i++) {
            User user = new User();
            user.setId(i+"");
            user.setName("小明" + i);
            user.setBirthDate(new Date());
            user.setNumber("1312789271"+i);
            user.setCardNumber("37894719971107273"+i);
            list.add(user);
        }
        return list;
    }
}
