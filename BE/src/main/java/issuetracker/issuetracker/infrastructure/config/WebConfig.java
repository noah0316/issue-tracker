package issuetracker.issuetracker.infrastructure.config;

import issuetracker.issuetracker.domain.interceptor.JwtInterceptor;
import issuetracker.issuetracker.domain.user.login.JwtUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// TODO: CORS에 대해 학습 후, allowOrigins,allowMethods 설정 예정
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("*");
        }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new JwtInterceptor(new JwtUtil()))
//                .addPathPatterns("/**")
//                .excludePathPatterns("/githublogin");
//    }
}
