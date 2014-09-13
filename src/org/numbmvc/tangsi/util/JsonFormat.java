package org.numbmvc.tangsi.util;

/**
 * json格式化规则
 * created by tangsi 2014/9/13
 */
public class JsonFormat {

    /**
     * 缩进
     */
    private int indent;

    /**
     * 缩进用的字符串
     */
    private String indentBy;

    /**
     * 是否忽略null值,默认不忽略
     */
    private boolean ignoreNull;

    /**
     * 是否紧凑模式
     */
    private boolean compact;

    /**
     * 分隔符
     */
    private char sepertor;

    public JsonFormat() {
        this(true);
    }

    public JsonFormat(boolean compcat) {
        this.compact = compcat;
        this.indentBy = "    ";
        this.sepertor = '\"';
    }

    /**
     * 一般模式，但是忽略空值 null
     *
     * @return
     */
    public static JsonFormat pretty() {
        return new JsonFormat(false).setIgnoreNull(true);
    }

    public boolean isIgnoreNull() {
        return ignoreNull;
    }

    public JsonFormat setIgnoreNull(boolean ignoreNull) {
        this.ignoreNull = ignoreNull;
        return this;
    }

    public char getSepertor() {
        return sepertor;
    }

    public void setSepertor(char sepertor) {
        this.sepertor = sepertor;
    }
}
