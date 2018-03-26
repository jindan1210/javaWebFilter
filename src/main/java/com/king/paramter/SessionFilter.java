package com.king.paramter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 金丹
 * @since 2018/3/26.
 */
public class SessionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(request);
        filterChain.doFilter(requestWrapper, response);
    }
}
