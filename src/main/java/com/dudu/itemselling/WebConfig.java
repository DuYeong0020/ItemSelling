package com.dudu.itemselling;

import com.dudu.itemselling.argumentresolver.SignInUserArgumentResolver;
import com.dudu.itemselling.interceptor.SignInCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final SignInCheckInterceptor signInCheckInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SignInUserArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(signInCheckInterceptor)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/selling/signin", "/selling/signup","/index.html", "/css/**");
    }
}
