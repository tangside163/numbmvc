package org.numbmvc.tangsi.mvc;

/**
 * 存储配置文件的路径,必须单例
 * created by tangsi 2014/9/7
 */
public class LoadSettings {

    private LoadSettings() {
    }

    private static LoadSettings instance = new LoadSettings();

    public static LoadSettings getInstance() {
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
