package bg.softuni.final_project.config;

import bg.softuni.final_project.web.interceptor.PopularCoursesInterceptor;
import bg.softuni.final_project.web.interceptor.StatsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private final StatsInterceptor statsInterceptor;
    private final PopularCoursesInterceptor popularCoursesInterceptor;

    public WebConfiguration(StatsInterceptor statsInterceptor, PopularCoursesInterceptor popularCoursesInterceptor) {
        this.statsInterceptor = statsInterceptor;
        this.popularCoursesInterceptor = popularCoursesInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(statsInterceptor);
        registry.addInterceptor(popularCoursesInterceptor);
    }
}
