package link.yangxin.concurrency;

import link.yangxin.concurrency.filter.HttpFilter;
import link.yangxin.concurrency.interceptor.HttpInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ConcurrencyApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean httpFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new HttpFilter());
        filterRegistrationBean.addUrlPatterns("/threadLocal/*");
        return filterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
    }
}
