package bao.xy.init;

import bao.xy.interceptor.DemoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @CreateTime: 2020-10-13-08-10
 */

@Configuration
public class WebMvcConf implements WebMvcConfigurer {

    @Bean
    public DemoInterceptor getDemoInterceptor() {
        return new DemoInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getDemoInterceptor()).addPathPatterns("/**").excludePathPatterns("/plugins/**", "/js/**", "/css/**", "/img/**", "/error/**", "/index.html", "/login.ajax");


    }

}
