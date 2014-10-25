package org.numbmvc.tangsi.mvc.annotation;

import java.lang.annotation.*;

/**
 * created by tangsi 2014/10/25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface Param {

    String value();
}
