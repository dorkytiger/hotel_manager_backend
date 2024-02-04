package com.dorkytiger.hotel_manager;

import com.dorkytiger.hotel_manager.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


class HotelManagerApplicationTests {

    @Test
    void contextLoads() {
        String password = "admin";
        System.out.println(MD5Util.getMD5(password));
    }

}
