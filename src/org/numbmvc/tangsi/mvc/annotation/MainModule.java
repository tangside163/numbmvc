package org.numbmvc.tangsi.mvc.annotation;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages_es;

import java.lang.annotation.*;

/**
 * created by tangsi 2014/9/7
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MainModule {

    String scanPackage() default "";


}
