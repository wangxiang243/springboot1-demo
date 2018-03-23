package com.wx.springbootdemo.config;

import com.wx.springbootdemo.exception.MyHandlerExceptionResolver;
import com.wx.springbootdemo.shiro.filter.CaptchaAuthenticationFilter;
import com.wx.springbootdemo.shiro.realm.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        LOGGER.info("===ShiroConfig.shiroFilterFactoryBean===");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "captchaAuthenticationFilter");

        //未授权功能
//        shiroFilterFactoryBean.setUnauthorizedUrl("/noPermission");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        //自定义filter
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("captchaAuthenticationFilter", captchaAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filters);

        return shiroFilterFactoryBean;

    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * ）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {

        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;

    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCachingEnabled(false);
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myShiroRealm());
        return defaultWebSecurityManager;
    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public MyHandlerExceptionResolver
    myHandlerExceptionResolver() {
        MyHandlerExceptionResolver r = new MyHandlerExceptionResolver();
        return r;
    }

    @Bean(name = "captchaAuthenticationFilter")
    public CaptchaAuthenticationFilter captchaAuthenticationFilter() {
        CaptchaAuthenticationFilter captchaAuthenticationFilter = new CaptchaAuthenticationFilter();
        return captchaAuthenticationFilter;
    }

}
