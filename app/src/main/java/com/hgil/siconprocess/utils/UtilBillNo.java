package com.hgil.siconprocess.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by mohan.giri on 24-03-2017.
 */

public class UtilBillNo {

    public static String generateBillNo(int recId, String oldBillNo) {
        String str_start = return5DigitStr(recId);
        String str_mid = new SimpleDateFormat("MMyy").format(new Timestamp(System.currentTimeMillis()));

        String str_end;
        if (oldBillNo != null && !oldBillNo.isEmpty() && oldBillNo.length() == 14) {
            String str2 = oldBillNo.substring(9);
            int int1 = Integer.valueOf(str2) + 1;
            str_end = return5DigitStr(int1);
        } else {
            str_end = "00001";
        }

        return (str_start + str_mid + str_end);
    }

    private static String return5DigitStr(int value) {
        String str5 = String.valueOf(value);
        if (str5.length() == 1) {
            str5 = "0000" + str5;
        } else if (str5.length() == 2) {
            str5 = "000" + str5;
        } else if (str5.length() == 3) {
            str5 = "00" + str5;
        } else if (str5.length() == 4) {
            str5 = "0" + str5;
        } else {
            return str5;
        }
        return str5;
    }
}
