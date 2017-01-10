package com.jit.ota4.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(new MediaType("text","plain",Charset.forName("UTF-8")));
        list.add(new MediaType("*","*",Charset.forName("UTF-8")));
        stringConverter.setSupportedMediaTypes(list);

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> jsonList = new ArrayList<MediaType>();
        jsonList.add(MediaType.valueOf("application/json;charset=UTF-8"));
        jsonList.add(MediaType.valueOf("text/plain;charset=utf-8"));
        jsonList.add(MediaType.valueOf("text/html;charset=utf-8"));
        jsonConverter.setSupportedMediaTypes(jsonList);
        //jsonConverter.setFeatures(new SerializerFeature[]{SerializerFeature.WriteDateUseDateFormat});

        converters.add(stringConverter);
        converters.add(jsonConverter);
    }

}
