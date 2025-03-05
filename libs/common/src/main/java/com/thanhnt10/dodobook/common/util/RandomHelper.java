package com.thanhnt10.dodobook.common.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomHelper {
    public static String generateTemplateCode(){
        int randomNumber = ThreadLocalRandom.current().nextInt(0, 100000); // Sinh số từ 10000 đến 99999
        return String.format("%05d", randomNumber); // Đảm bảo độ dài là 5 chữ số
    }
}
