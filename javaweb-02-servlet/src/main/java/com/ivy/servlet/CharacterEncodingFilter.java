package com.ivy.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * CharacterEncodingFilter
 *
 * @Author: ivy
 * @CreateTime: 2021-08-12
 */
public class CharacterEncodingFilter implements Filter {

    /**
     * 初始化
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter init...");
    }

    /**
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletContext context = servletRequest.getServletContext();
        String encoding = context.getInitParameter("encoding");
        encoding = "utf-8";
        System.out.println(encoding);
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {

    }
}
