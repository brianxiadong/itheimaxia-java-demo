package com.brianxia.threadlocalfeignconsumer.filter;

import com.brianxia.threadlocalfeignconsumer.UserThreadLocal;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/7/3 18:31
 */
@WebFilter(filterName = "userFilter",urlPatterns = "/*")
public class UserFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        UserThreadLocal.userId.set(request.getHeader("userId"));
        filterChain.doFilter(request, servletResponse);    }
}
