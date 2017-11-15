package com.jinshun.contact.configuration;

import com.jinshun.contact.auth.AuthInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@ServletComponentScan
public class SampleConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/login");
        super.addInterceptors(registry);
    }

    @Bean
    public List<String> buildActions() {
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        List<String> list = new ArrayList<String>(map.size());

        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            HandlerMethod method = m.getValue();
            String action = method.getMethod().getDeclaringClass().getName() + "." + method.getMethod().getName();
            list.add(String.format("('%s', '%s')", action, action));
        }

        System.out.println("insert into t_action (name, method) values " + StringUtils.join(list, ",\n"));
        return list;
    }
}
