package org.numbmvc.tangsi.util.castor;

import java.util.HashMap;
import java.util.Map;

/**
 * created by tangsi 2014/9/16
 */
public class CastorManager {

    private CastorManager (){}

    /**
     * key --> 源类型与目标类型class的全路径名拼接,例如java.lang.String_java.util.Map
     * value --> 转换器对象
     */
    private Map<String, Castor> castors = new HashMap<String, Castor>();

    private  static   CastorManager instance = new CastorManager();

    public static  CastorManager getInstance() {
        return instance;
    }

}
