package org.numbmvc.tangsi.mvc.inject;

import org.numbmvc.tangsi.mvc.annotation.Param;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基本类型参数注入器
 * created by tangsi 2014/10/25
 */
public class PrimitiveInjector implements ParamInjector {

    private Class<?> clazz;

    private Param param;

     public PrimitiveInjector(Class<?> clazz, Param param) {
        this.clazz = clazz;
         this.param = param;
    }

    @Override
    public Object get(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {

        String paramName = paramName = param.value();
        String paramValue = request.getParameter(paramName);

        if(this.clazz == int.class) {
            if(paramValue != null) {
                return Integer.parseInt(paramValue);
            }
            return 0;
        }else if(this.clazz == short.class) {
            if(paramValue != null) {
                return Short.parseShort(paramValue);
            }

            return 0;
        }else if(this.clazz == byte.class) {
            if(paramValue != null) {
                return Byte.parseByte(paramValue);
            }
            return 0;
        }else if(this.clazz == char.class) {
            if(paramValue != null) {
                return  paramValue.charAt(0);
            }
            return null;
        }else if(this.clazz == long.class) {
            if(paramValue != null) {
                return Long.parseLong(paramValue);
            }
            return 0L;
        }else if(this.clazz == float.class) {
            if(paramValue != null) {
                return Float.parseFloat(paramValue);
            }
            return 0.0f;
        }else if(this.clazz == double.class) {
            if(paramValue != null) {
                return Double.parseDouble(paramValue);
            }
            return 0.0d;
        }else if(this.clazz == boolean.class) {
            if(paramValue != null) {
                return Boolean.parseBoolean(paramValue);
            }
            return  false;
        }

        return null;
    }
}
