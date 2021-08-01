//package org.kenux.security.config;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Slf4j
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//@Order(0)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .antMatcher("/admin/**")
////                .authorizeRequests()
////                .anyRequest().authenticated()
////                .and()
////                .httpBasic();
////    }
//}
//
//@Configuration
//@Order(1)
//class SecurityConfig2 extends WebSecurityConfigurerAdapter {
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .authorizeRequests()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin();
////    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder()
//    {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//}
