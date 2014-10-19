package org.numbmvc.tangsi.mvc;

import org.apache.log4j.Logger;
import org.numbmvc.tangsi.mvc.annotation.MainModule;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * created by tangsi 2014/9/7
 */
public class NumbDispatcherServlet extends HttpServlet {

    /**
     * 日志句柄
     */
    private static final Logger logger = Logger.getLogger(NumbDispatcherServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            this.parseMvcConfig();
            this.initNumbMvc();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parseMvcConfig() throws ClassNotFoundException {

        logger.info("获取主模块配置");
        String configModule = this.getInitParameter("configModule");

        Class<?> clazz = Class.forName(configModule);

        if(clazz.isAnnotationPresent(MainModule.class)) {
            MainModule mainModule = clazz.getAnnotation(MainModule.class);
            String scanPackage = mainModule.scanPackage();
            if("".equals(scanPackage)) {
                logger.error("主模块没有设置包扫描路径!");
                throw new RuntimeException("主模块没有设置包扫描路径!");
            }else {
                MvcSettings.getInstance().setScanPackage(scanPackage);
            }

        }else {
            logger.error("web.xml中没有设置主模块参数configModule!");
            throw new RuntimeException("请在web.xml中设置主模块参数configModule!");
        }

    }

    private void initNumbMvc() throws IOException, ClassNotFoundException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = new Date();
        logger.info("开始初始化numbMVC: "+dateFormat.format(start));
        String classesFilePath = this.getServletContext().getRealPath("/")
                + File.separator+"WEB-INF"+File.separator+"classes";

        String jarFilePath = this.getServletContext().getRealPath("/")
                + File.separator+"WEB-INF"+File.separator+"lib";

        File classesFileDirectory = new File(classesFilePath);

        File jarFileDirectory = new File(jarFilePath);

        this.parseFileInDirectory(classesFileDirectory, classesFileDirectory.getAbsolutePath());

        this.parseFileInJarDirectory(jarFileDirectory);
        UrlMappingManager.getInstance();
        Date end = new Date();

        logger.info("完成初始化numbMVC: "+dateFormat.format(start)
                +",一共用时"+(end.getTime() - start.getTime())/1000+"秒");
    }


    private void parseFileInJarDirectory(File jarFileDirectory) throws IOException, ClassNotFoundException {

        File[] files = jarFileDirectory.listFiles();

        if(files != null && files.length > 0) {

            for(File file : files) {
                if( file.isFile() &&  file.getName().endsWith(".jar")) {
                    JarFile jarFile = new JarFile(file);

                    Enumeration<JarEntry> enumeration = jarFile.entries();

                    while(enumeration.hasMoreElements()) {
                        //获取每一个jar文件
                        JarEntry jarEntry = enumeration.nextElement();

                        String jarFileName = jarEntry.getName();

                        if(jarFileName.endsWith(".class")) {
                            jarFileName = jarFileName.replaceAll("[/\\\\]",".");
                            jarFileName = jarFileName.substring(0,jarFileName.lastIndexOf("."));   //去掉类文件的文件后缀.class
                            //只扫描指定的包及其子包中的class字节码文件
                            if(jarFileName.startsWith(MvcSettings.getInstance().getScanPackage())) {
                                MVCLoader.getInstance().parseClass(jarFileName,this.getServletContext());
                            }

                        }

                    }
                }
            }

        }

    }

    private void parseFileInDirectory(File classesFile, String basePath) throws ClassNotFoundException {
        File[] classSubFiles = classesFile.listFiles();

        //查找classes目录与lib目录jar包 中的class文件
        if(classSubFiles != null && classSubFiles.length > 0) {

            for(File file : classSubFiles) {
                if(file.isFile()) {
                    String fileName = file.getAbsolutePath();
                    if(fileName.endsWith(".class")) {
                        //只需留下classes目录下以包名加 类名的格式
                        fileName = fileName.substring(fileName.indexOf(basePath)+basePath.length()+1,fileName.length());
                        fileName = fileName.replaceAll("[/\\\\]",".");
                        //只扫描指定的包及其子包中的class字节码文件
                        if(fileName.startsWith(MvcSettings.getInstance().getScanPackage())) {
                            //例如文件fileName = com.tangsi.auth.controller.AuthController.class
                            //加载class之前去掉.class 变成 com.tangsi.auth.controller.AuthController
                            MVCLoader.getInstance().parseClass(fileName.substring(0,fileName.length() - 6),this.getServletContext());
                        }

                    }
                }else if(file.isDirectory()) {
                    this.parseFileInDirectory(file,basePath);
                }

            }

        }
    }


    @Override
    public void destroy() {
        super.destroy();
    }

}
