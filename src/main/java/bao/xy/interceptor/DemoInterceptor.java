package bao.xy.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @CreateTime: 2020-10-13-08-06
 */
public class DemoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        Object user = request.getSession().getAttribute("user");
//        System.out.println(uri + "->" +user);

        if (user != null) {
            return true;

        } else {
            response.sendRedirect("index.html");
            return false;
        }
    }
}
