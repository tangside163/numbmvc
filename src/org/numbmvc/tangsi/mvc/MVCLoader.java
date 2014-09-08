package org.numbmvc.tangsi.mvc;

import org.numbmvc.tangsi.mvc.annotation.Controller;
import org.numbmvc.tangsi.mvc.annotation.Ok;
import org.numbmvc.tangsi.mvc.annotation.RequestMapping;

import javax.servlet.ServletContext;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URLClassLoader;

/**
 * created by tangsi 2014/9/7
 */
public class MVCLoader {

    private MVCLoader(){}

    private static  MVCLoader instance = new MVCLoader();

    public static  MVCLoader getInstance() {
        return instance;
    }

    /**
     *  利用反射处理字节码文件，
     * @param fileName  字节码文件
     */
    public void parseClass(String fileName,ServletContext servletContext) throws ClassNotFoundException {

        Class<?> clazz = Class.forName(fileName);

        if(clazz.isAnnotationPresent(Controller.class)) {

            //类级别上的RequestMapping注解
            RequestMapping classMapping = clazz.getAnnotation(RequestMapping.class);
            String classUrl = null;  //类级别上的url映射
            if(classMapping != null) {
                String value = classMapping.value();
                if("".equals(value)) {
                    classUrl = "/"+clazz.getSimpleName();
                }else {
                    classUrl = value;
                }
            }

            Method[] methods = clazz.getDeclaredMethods();

            if(methods != null && methods.length > 0) {

                for (Method method : methods) {
                    UrlMapping urlMapping = new UrlMapping();
                    String url = this.evalUrl(urlMapping,method,classUrl);

                    if(url == null) {
                        continue;
                    }else {
                        urlMapping.setMethod(method);
                        UrlMappingManager.getInstance().getUrlMappings().put(url, urlMapping);
                        this.evalOk(urlMapping,method,servletContext);
                    }
                }

            }

        }

    }

    /**
     * 解析入口函数上的Ok注解，得到web访问响应方式,如：跳转jsp，转发路径，重定向,下载
     * @param urlMapping
     * @param method
     * @param servletContext
     */
    private void evalOk(UrlMapping urlMapping, Method method, ServletContext servletContext) {
        Ok ok = method.getAnnotation(Ok.class);
        String value = ok.value();
        if(!"".equals(value)) {
            if(value.startsWith("jsp:")) {  //jsp视图
                String forwardJspUrl = value.substring(value.indexOf(":")+1);
                if(forwardJspUrl.startsWith("/")) {  //转发到webroot根目录,即配置成@Ok("jsp:/auth.login")转发到webroot/auth/login.jsp
                    forwardJspUrl = forwardJspUrl.replace("/", "").replaceAll(".", "/");
                    forwardJspUrl = servletContext.getRealPath("/")+forwardJspUrl+".jsp";
                }else {  //转发到WEB-INF/JSP目录,契约,即配置成@Ok("jsp:jsp.auth.login") 转发到webroot/WEB-INF/jsp/auth/login.jsp
                    forwardJspUrl = servletContext.getRealPath("/")+"WEB-INF"+"/"
                            +forwardJspUrl.replaceAll("\\.", "/")+".jsp";
                }

                urlMapping.setForwardJsp(forwardJspUrl);
            }else if(value.startsWith("forward:")) {  //转发到另一个请求路径
                String forwardUrl = value.substring(value.indexOf(":")+1);  //转发路径支持字符串模板,例如：@Ok("forward:/auth/registe?id=${obj.userId}")
                urlMapping.setForwardUrl(forwardUrl);
            }else if(value.startsWith("redirect:")) {  //重定向
                String redirectUrl = value.substring(value.indexOf(":")+1); //同样，重定向路径支持字符串模板,例如：@Ok("redirect:/auth/registe?id=${obj.userId}")
                //重定向需要考虑到定位到外站地址的可能,在这里我们认为如果你配置的地址以http开头(契约)，
                //即认为是重定向到外站地址,例如 @Ok("forward:http://www.baidu.com"),
                //否则只是重定向到被部署项目的一个相对地址,例如@Ok("forward:/user/list")

                if(redirectUrl.startsWith("http")) {
                    // do nothing here
                }else {
                   redirectUrl = servletContext.getContextPath()
                           +redirectUrl;
                }
                urlMapping.setRedirectUrl(redirectUrl);
            }else if(value.startsWith("download")) {  //文件下载视图
                urlMapping.setDownLoadView(value);
             }
        }
    }


    /**
     * 解析入口函数上的RequestMapping注解，得到请求路径
     * @param urlMapping
     * @param method
     * @param classUrl
     * @return
     */
    private String evalUrl(UrlMapping urlMapping, Method method, String classUrl) {
        //只对public方法添加RequestMapping注解,实际上你要对private 等方法添加映射也是可以的，
        // 只是在请求过来，反射调用对应方法时将method.setAccessible(true)即可
        if(Modifier.isPublic(method.getModifiers())
                && method.isAnnotationPresent(RequestMapping.class)) {
            //方法上的RequestMapping注解
            RequestMapping methodMapping = method.getAnnotation(RequestMapping.class);
            String methodUrl = null;
            String value = methodMapping.value();
            if("".equals(value)) {
                methodUrl = "/" + method.getName();
            }else {
                methodUrl = value;
            }
            urlMapping.setUrl(classUrl + methodUrl);
            return classUrl + methodUrl;
        }
        return null;
    }


}
