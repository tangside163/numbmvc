package org.numbmvc.tangsi.mvc;

/**
 * 存储配置文件的路径,必须单例
 * created by tangsi 2014/9/7
 */
public class MvcSettings {

    private MvcSettings() {
    }

    private static MvcSettings instance = new MvcSettings();

    public static MvcSettings getInstance() {
        return instance;
    }


    /**
     * 类扫描路径
     */
    private String scanPackage;

    public String getScanPackage() {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }
}
