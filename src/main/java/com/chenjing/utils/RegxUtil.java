package com.chenjing.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chenjing on 2017/3/5.
 */
public class RegxUtil {
    /**
     * 判断是不是手机号码
     *
     * @param mobiles
     * @return true or false
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 判断是否为合法IP
     *
     * @return the ip
     */
    public static boolean isIP(String ip) {
        if (ip.length() < 7 || ip.length() > 15 || "".equals(ip)) {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(ip);
        boolean ipAddress = mat.find();
        return ipAddress;
    }

    public static boolean isIdCard(String id){
        String rgx = "^\\d{15}|^\\d{17}([0-9]|X|x)$";
        Pattern p = Pattern.compile(rgx);
        Matcher m = p.matcher(id);
        return m.matches();
    }

    public static boolean isWeather(String str){
        if (str.contains("天气")){
            return true;
        }else {
            return false;
        }
    }

}
