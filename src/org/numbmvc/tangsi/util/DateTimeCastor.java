package org.numbmvc.tangsi.util;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 将日期如Caledar，Date转换为字符串
 * created by tangsi 2014/9/13
 */
public class DateTimeCastor {

    private DateFormat dateFormat;

    private static DateTimeCastor instance = new DateTimeCastor();


    public static DateTimeCastor getInstance() {
        return instance;
    }

    /**
     * 将日期对象转换成时间字符串
     *
     * @param dateTimeObj
     * @return
     */
    public String castToString(Object dateTimeObj) {


        if (Calendar.class.isAssignableFrom(dateTimeObj.getClass())) {
            Calendar calendar = (Calendar) dateTimeObj;
            return dateFormat.format(calendar.getTime());
        } else if (Date.class == dateTimeObj.getClass()) {
            Date date = (Date) dateTimeObj;
            return dateFormat.format(date);
        } else if (java.sql.Date.class == dateTimeObj.getClass()) {
            Date date = (Date) dateTimeObj;
            return dateFormat.format(date);
        } else if (Time.class == dateTimeObj.getClass()) {  //你不会还用这个过时的api吧
            return null;
        }

        throw new RuntimeException("转换错误！！！");
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public DateTimeCastor setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        return  this;
    }
}
