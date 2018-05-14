package com.wx.springbootdemo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.*;

@Configuration
public class SpringMvcConfig {

    @Bean
    public FastJsonConfig fastJsonConfig() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        return fastJsonConfig;
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        //1、需要先定义一个 convert 转换消息对象；
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //2、添加 fastJson 的配置信息，比如: 是否要格式化返回的Json数据；
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

        //3、在 Convert 中添加配置信息;
        fastConverter.setFastJsonConfig(fastJsonConfig());

        //4、
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }

    /*@Bean
    public FastJsonJsonView fastJsonJsonView() {
        FastJsonJsonView fastJsonJsonView = new FastJsonJsonView();
        fastJsonJsonView.setFastJsonConfig(fastJsonConfig());
        return fastJsonJsonView;
    }

    @Bean
    public ContentNegotiationManager contentNegotiationManager() {
        List<ContentNegotiationStrategy> strategies = new ArrayList();
        Map<String, MediaType> mediaTypes = new HashMap<>();
        mediaTypes.put("json", MediaType.APPLICATION_JSON_UTF8);
        ContentNegotiationStrategy contentNegotiationStrategy = new PathExtensionContentNegotiationStrategy(mediaTypes);
        strategies.add(contentNegotiationStrategy);
        ContentNegotiationManager contentNegotiationManager = new ContentNegotiationManager(strategies);
        return contentNegotiationManager;
    }

    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
        ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
        contentNegotiatingViewResolver.setOrder(1);
        contentNegotiatingViewResolver.setContentNegotiationManager(contentNegotiationManager());
        List<View> viewList = new ArrayList<>();
        viewList.add(fastJsonJsonView());
        contentNegotiatingViewResolver.setDefaultViews(viewList);
        return contentNegotiatingViewResolver;
    }*/

}
