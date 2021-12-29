package com.dayz.config;

import com.dayz.common.aop.JwtAuthenticationArgumentResolver;
import com.dayz.common.filter.LoggingInterceptor;
import com.dayz.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final MemberService memberService;

    private final JwtAuthenticationArgumentResolver addArgumentResolvers;

    private final LoggingInterceptor loggingInterceptor;

    @Bean
    public JwtAuthenticationArgumentResolver authenticationArgumentResolver() {
        return new JwtAuthenticationArgumentResolver(memberService);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(addArgumentResolvers);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor) .addPathPatterns("/**") .excludePathPatterns("/vendor/**", "/css/*", "/img/*");
    }

}
