package com.reggie_takeout.filter;


import com.alibaba.fastjson.JSON;
import com.reggie_takeout.common.R;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class loginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        String[] urls = new String[]{
          "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };
        boolean check = check(requestURI, urls);
        if(check){
            filterChain.doFilter(request, response);
            return;

        }


        if(request.getSession().getAttribute("employee") != null){
            filterChain.doFilter(request, response);
            return;
        }


        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

        

    }

    public boolean check(String requestURI, String[] urls) {
        for (String url : urls) {
            if (PATH_MATCHER.match(url, requestURI)) {
                return true;
            }
        }
        return false;
    }
}
