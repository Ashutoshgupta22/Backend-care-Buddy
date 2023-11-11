package com.aspark.carebuddy.security;

import lombok.AllArgsConstructor;
import com.aspark.carebuddy.model.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Properties;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig  {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //TODO for production add csrf token and enable csrf

        //Enable CORS for web service to consume APIs
        httpSecurity.cors();

        httpSecurity.csrf().disable()
                .authorizeHttpRequests().antMatchers("/api/user/registration/**",
                        "/api/user/login","/api/user/save-location","/api/user/**","/api/user/book-service/**",
                        "/api/nurse/**", "/care-buddy-websocket")
                .permitAll()
                .anyRequest().authenticated().and().httpBasic();

        return httpSecurity.build();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    //TODO What does this method do?
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("asparkofficial@gmail.com");
        mailSender.setPassword("kaspftrlzkqdmdkk");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
