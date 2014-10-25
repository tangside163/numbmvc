package org.numbmvc.tangsi.mvc.inject;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ServletContext参数注入器
 * created by tangsi 2014/10/25
 */
public class ServletContextInjector implements  ParamInjector{
    @Override
    public Object get(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        return servletContext;
    }
}
