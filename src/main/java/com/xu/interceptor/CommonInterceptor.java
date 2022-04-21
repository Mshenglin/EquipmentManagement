package com.xu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 * @author Alkmg
 */
public class CommonInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            // 获取请求的URL
            String url = request.getRequestURI();
            // login.jsp或登录请求放行，不拦截
            if (url.indexOf("/login") >= 0 || url.indexOf("/sign") >= 0||url.indexOf("/signFirst")>=0) {
                return true;
            }
            // 获取 session
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("user");
            if (obj != null){
                return true;
            }
            // 没有登录且不是登录页面，转发到登录页面，并给出提示错误信息
            request.setAttribute("msg", "还没登录，请先登录！");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,
                    response);
            return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
