package com.xu.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 日期工具类
 * @author Alkmg
 */
public class DateUtil {
    /**
     * 将时间戳转化为日期格式
     * @param time
     * @return
     */
    public static String  getFormatTime(Long time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //注意这里返回的是string类型
        String date = format.format(time);
        return date;
    }
}
