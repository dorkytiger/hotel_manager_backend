package com.dorkytiger.hotel_manager.util;

import java.util.UUID;

public class BillNumberUtil {
    public static String generateBillNumber() {
        // 生成一个UUID作为账单序列号
        UUID uuid = UUID.randomUUID();
        // 去掉UUID中的分隔符，并取前20位作为序列号
        return uuid.toString().replaceAll("-", "").substring(0, 20);
    }
}