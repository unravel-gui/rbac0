package com.komorebi.rbac0.config;

import com.komorebi.rbac0.filter.AuthFilter;
import com.komorebi.rbac0.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterConfig {
    @Value("${config.auth.headerKey}")
    private String headerKey;
    @Value("${config.auth.prefix}")
    private String authPrefix;
    @Autowired
    private PermissionService permissionService;

    @Bean()
    public FilterRegistrationBean<AuthFilter> authFilterRegistration() {
        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>();
        AuthFilter auth = new AuthFilter();
        auth.setAuthPrefix(authPrefix);
        auth.setHeaderKey(headerKey);
        auth.setPermissionService(permissionService);
        registration.setFilter(auth);
        registration.addUrlPatterns("/*"); // 设置过滤器拦截的URL模式
        registration.setName("authFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE); // 设置过滤器的执行顺序
        return registration;
    }
}