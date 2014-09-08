package org.numbmvc.tangsi.mvc;

import java.lang.reflect.Method;

/**
 * created by tangsi 2014/9/7
 */
public class UrlMapping {

    /**
     * 请求url路径
     */
    private String url;

    /**
     * 请求路径对应的Method
     */
    private Method method;

    /**
     * 转发jsp路径
     */
    private String forwardJsp;

    /**
     * 转发url
     */
    private String forwardUrl;

    /**
     * 重定向url地址
     */
    private String redirectUrl;

    /**
     * 下载配置
     */
    private String downLoadView;

    public String getDownLoadView() {
        return downLoadView;
    }

    public void setDownLoadView(String downLoadView) {
        this.downLoadView = downLoadView;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getForwardJsp() {
        return forwardJsp;
    }

    public void setForwardJsp(String forwardJsp) {
        this.forwardJsp = forwardJsp;
    }

    public String getForwardUrl() {
        return forwardUrl;
    }

    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
