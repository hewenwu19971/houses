package com.hww.houserent.config;

import com.hww.houserent.userrealm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShrioConfig {
    //注入Realm
    @Bean
    public UserRealm getUserRealm() {
        return new UserRealm();
    }

    //创建ShiroFilterFactoryBean过滤器
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
       /* anon: 无需认证即可访问
        authc: 需要认证才可访问
        user: 点击“记住我”功能可访问
        perms: 拥有权限才可以访问
        role: 拥有某个角色权限才能访问*/
        //创建ShiroFilterFactoryBean对象
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置回话管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //创建map集合保存各种处理的请求
        Map<String, String> map = new LinkedHashMap<>();
        /*************设置需要放行aono请求**************/
        map.put("/login", "anon");//去到登录页面的请求
        map.put("/login.html","anon");
        /*************设置需要身份验证authc请求**************/
        map.put("/index", "authc");
        map.put("/*.html", "authc");
        /***********************设置角色验证roles**************************/
        map.put("/delFangyuan","roles[admin]");



        //设置登录页面请求（设置不去默认的login.jsp页面）
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        //设置过滤器
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //设置权限不足的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuthc");
        return shiroFilterFactoryBean;
    }

    //创建DefaultWebSecurityManager对象，关联reaml
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm) {
        //创建DefaultWebSecurityManager对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //管理reaml
        defaultWebSecurityManager.setRealm(userRealm);


        return defaultWebSecurityManager;
    }
}
