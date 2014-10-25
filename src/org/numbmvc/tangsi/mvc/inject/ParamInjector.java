package org.numbmvc.tangsi.mvc.inject;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 为你的控制器上的入口函数调用之前为其每个参数赋值
 * created by tangsi 2014/10/25
 */
public interface ParamInjector {

    Object get(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response);

}
