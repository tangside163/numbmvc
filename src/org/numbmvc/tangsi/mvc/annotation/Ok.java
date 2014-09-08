package org.numbmvc.tangsi.mvc.annotation;

import java.lang.annotation.*;

/**
 * created by tangsi 2014/9/7
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Ok {

    String value();

}
