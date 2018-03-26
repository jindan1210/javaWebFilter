package com.king.interceptor;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * 防止xss攻击
 *
 * @author 金丹
 * @since 2018/3/26.
 */
@Component
public class HtmlEncodeInterceptor implements HandlerInterceptor {
    private static String loginUrl = "/";

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String parameter = httpServletRequest.getParameter(name);
            httpServletRequest.setAttribute(name, StringEscapeUtils.escapeHtml4(parameter));
        }
        return true;
    }

    public static void setLoginUrl(String loginUrl) {
        HtmlEncodeInterceptor.loginUrl = loginUrl;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

