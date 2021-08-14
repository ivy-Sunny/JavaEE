package com.ivy.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * ImageServlet
 *
 * @Author: ivy
 * @CreateTime: 2021-08-11
 */
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //让浏览器3秒刷新一次
        resp.setHeader("refresh", "3");
        //在内存中创建一个图片
        BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_3BYTE_BGR);
        //得到图片
        Graphics2D g = (Graphics2D) image.getGraphics();
        //设置图片背景颜色
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 100, 25);
        //给图片写数据
        g.setColor(Color.red);
        g.setFont(new Font(null, Font.BOLD, 20));
        g.drawString(makeNum(), 0, 20);

        //告诉浏览器，这个请求用图片的方式打开
        resp.setContentType("image/*");
        //网站存在缓存，禁止浏览器使用缓存
        resp.setDateHeader("expirse",-1);
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Pargma","no-cache");

        //把图片返回给浏览器
        ImageIO.write(image,"jpg",resp.getOutputStream());
    }

    private String makeNum() {
        Random random = new Random();
        String num = random.nextInt(999999) + "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 7 - num.length(); i++) {
            sb.append(0);
        }
        num = sb.toString() + num;
        return num;
    }
}
