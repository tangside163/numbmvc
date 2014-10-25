package org.numbmvc.tangsi.mvc.castor;

/**
 * created by tangsi 2014/10/25
 */
public class String2IntCastor  implements  Castor{
    @Override
    public Object cast(Object fromObj) {

        String from =  (String)fromObj;

        return Integer.parseInt(from);

    }
}
