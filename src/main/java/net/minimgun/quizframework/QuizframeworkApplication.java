package net.minimgun.quizframework;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class QuizframeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizframeworkApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder init() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OpenAPI apiInfo(@Value("${application-description}") String appDesciption,
            @Value("${application-version}") String appVersion) {
        return new OpenAPI().info(new Info().title("Quiz Framework API")
            .version(appVersion)
            .description(appDesciption)
            .license(new License().name("GNU General Public License v3.0")
                .url("https://github.com/MINIMgun/quiz-framework/blob/main/LICENSE")));
    }
}
