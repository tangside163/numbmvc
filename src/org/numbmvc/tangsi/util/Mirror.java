package org.numbmvc.tangsi.util;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 对class对象的包裹，提供该对象用于反射的常用方法
 * created by tangsi 2014/9/13
 */
public class Mirror {

    private Class clazz;

    public Mirror(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * 是否是数字
     * @return
     */
    public  boolean  isArray() {
        return clazz.isArray();
    }

    /**
     * 是否是数字
     * @return
     */
    public  boolean  isNumber() {
        return Number.class.isAssignableFrom(clazz) && !this.isChar();
    }

    /**
     * 是否是字符
     * @return
     */
    public  boolean isBoolean() {
        return boolean.class == this.clazz || Boolean.class == this.clazz;
    }

    /**
     * 是否是枚举
     * @return
     */
    public boolean isEnum() {
        return this.clazz.isEnum();
    }

    /**
     * 是否是类字符串
     * @return
     */
    public boolean isStringLike() {
        return CharSequence.class.isAssignableFrom(this.clazz);
    }

    public boolean isChar() {
        return char.class == this.clazz
                || Character.class == this.clazz;
    }

    /**
     * 是否是日期类型
     * @return
     */
    public boolean isDateTimeLike() {
        return Calendar.class.isAssignableFrom(this.clazz)
                || Date.class.isAssignableFrom(this.clazz)
                || java.sql.Date.class.isAssignableFrom(this.clazz)
                || Time.class.isAssignableFrom(this.clazz);
    }

    /**
     * 是否是类Map类型
     * @return
     */
    public boolean isMapLike() {
        return Map.class.isAssignableFrom(this.clazz);
    }
}
