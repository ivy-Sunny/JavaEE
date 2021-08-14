package com.ivy.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * HelloServlet
 *
 * @Author: ivy
 * @CreateTime: 2021-08-11
 */
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取下载文件的路径
        ServletContext context = this.getServletContext();
        String realPath = context.getRealPath("/WEB-INF/classes/4927.JPG");
        System.out.println("下载文件的路径：" + realPath);
        //2、下载的文件名

        //3、想办法让浏览器能够支持下载的文件
        resp.setHeader("Content-Disposition", "attchment:filename=" + "4927.JPG");
        //4、获取下载文件的输入流
        FileInputStream inputStream = new FileInputStream(realPath);
        //5、创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];
        //6、获取OutputStream对象流
        ServletOutputStream outputStream = resp.getOutputStream();
        //7、将FileOutputStream写入到buffer缓冲区
        while ((len = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
        outputStream.close();
    }
}
