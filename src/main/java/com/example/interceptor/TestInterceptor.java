package com.example.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * Created by arahansa on 2015-05-18.
 */
public class TestInterceptor  extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestInterceptor.class);
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.debug("끝");
        super.afterCompletion(request, response, handler, ex);
        LOGGER.debug("끝말고 이건?");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug("시작");
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        Map<String, Object> model = modelAndView.getModel();

        System.out.println("투스트릴ㅇ :"+model.toString());


    }
}
