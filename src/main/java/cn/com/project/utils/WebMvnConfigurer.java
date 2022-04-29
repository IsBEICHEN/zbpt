package cn.com.project.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class WebMvnConfigurer extends WebMvcConfigurerAdapter {
    @Autowired
    private WebInterceptor webInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //                registry.addInterceptor(webInterceptor).addPathPatterns("/**")
        //                .excludePathPatterns("/login", "/index", "/resource/**",
        //                    "/plugins/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //        registry.addResourceHandler("/**")
        //                .addResourceLocations("classpath:/resource/")
        //                .addResourceLocations("classpath:/plugins/");//
    }

}
