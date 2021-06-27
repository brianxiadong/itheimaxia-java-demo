package com.brianxia.threadlocaldemo.filter;

import com.brianxia.threadlocaldemo.threadlocal.MyThreadLocal;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/6/28 0:27
 */
@WebFilter(filterName = "myfilter",urlPatterns = "/*")
public class MyFilter extends GenericFilter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try{
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String a = request.getParameter("a");
            if(a == null || !request.getParameter("a").equals("1")){
                MyThreadLocal.myThreadLocal.set("123");
            }
            filterChain.doFilter(servletRequest,servletResponse);
        }finally {
            //调用remove方法溢出threadLocal中的变量
            MyThreadLocal.myThreadLocal.remove();
        }

    }
}
