package org.numbmvc.tangsi.mvc;

import java.util.HashMap;
import java.util.Map;

/**
 * created by tangsi 2014/9/7
 */
public class UrlMappingManager {

    private UrlMappingManager() {}

    private static UrlMappingManager instance = new UrlMappingManager();

    public static  UrlMappingManager getInstance() {
        return  instance;
    }

    /**
     * key---> url, value--->UrlMapping
     */
    private Map<String, UrlMapping> urlMappings = new HashMap<String, UrlMapping>();

    public Map<String, UrlMapping> getUrlMappings() {
        return urlMappings;
    }

    public void setUrlMappings(Map<String, UrlMapping> urlMappings) {
        this.urlMappings = urlMappings;
    }
}
