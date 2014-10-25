package org.numbmvc.tangsi.mvc.inject;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ServletResponset参数注入器
 * created by tangsi 2014/10/25
 */
public class ServletResponseInjector implements ParamInjector{
    @Override
    public Object get(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        return response;
    }
}
