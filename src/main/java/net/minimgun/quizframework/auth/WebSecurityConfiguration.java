package net.minimgun.quizframework.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll().and().cors().and().csrf().disable();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(@Value("${frontend.url}") String frontend) {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        List<String> exposedHeaders = new ArrayList<>();
        exposedHeaders.add("Authorization");
        exposedHeaders.add("Set-Cookie");
        CorsConfiguration config = new CorsConfiguration();
        config.setExposedHeaders(exposedHeaders);
        config.addAllowedMethod("*");
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedOrigin(frontend);
        config.setAllowCredentials(true);
        config.setMaxAge(1800L);
        config.addAllowedHeader("*");
        config.validateAllowCredentials();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
