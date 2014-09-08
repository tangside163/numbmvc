package org.numbmvc.tangsi.mvc.annotation;

import java.lang.annotation.*;

/**
 * created by tangsi 2014/9/7
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface RequestMapping {

    String value() default "";

}
