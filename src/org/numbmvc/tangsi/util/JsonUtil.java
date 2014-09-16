package org.numbmvc.tangsi.util;

import org.numbmvc.tangsi.util.castor.DateTimeCastor;

import java.text.SimpleDateFormat;

/**
 * json工具类
 * created by tangsi 2014/9/13
 */
public class JsonUtil {


    public static String toJson(Object obj) {
        return toJson(obj,null);
    }

    public static String toJson(Object obj,JsonFormat format) {

        if (obj == null) return "null";

        if(format == null) {
            format = JsonFormat.pretty();
        }

        Mirror mirror = new Mirror(obj.getClass());
        StringBuilder sb = new StringBuilder();
        //枚举
        if(mirror.isEnum()) {
            string2Json(((Enum) obj).name(), format, sb);
        }else if (mirror.isNumber() || mirror.isStringLike()  //数字，类字符串，字符,布尔型
                || mirror.isChar() || mirror.isBoolean()) {
            string2Json(obj.toString(), format, sb);
        }else if(mirror.isDateTimeLike()) {  //日期类型
            DateTimeCastor castor = DateTimeCastor.getInstance().setDateFormat(new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss"));
            string2Json(castor.castToString(obj), format, sb);
        }


        return sb.toString();

    }

    private static void string2Json(String str, JsonFormat format, StringBuilder sb) {
        sb.append(format.getSepertor()).append(str).append(format.getSepertor());
    }

}
