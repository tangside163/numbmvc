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

    /**
     * 包名，也就是类扫描路径
     * @return
     */
    String scanPackage() default "";


    /**
     * 静态资源，配置的静态资源将不会被numbmvc给拦截
     * @return
     */
    String[] staticResource();



}
