package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/imageview").setViewName("hello");
        registry.addViewController("/imageview2").setViewName("hello2");
        registry.addViewController("/viewall").setViewName("view");
        registry.addViewController("/login").setViewName("login");
       registry.addViewController("/register").setViewName("register");
        registry.addViewController("/success").setViewName("success");
    }

}
