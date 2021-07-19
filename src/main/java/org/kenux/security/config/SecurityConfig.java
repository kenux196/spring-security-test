package org.kenux.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import sun.security.util.ManifestDigester;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        // 인가 설정
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/admin/pay").hasRole("ADMIN")
                .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('SYS')")
                .anyRequest().authenticated();
        
        // 로그인(인증) 설정
        http
                .formLogin()
//                .loginPage("/loginPage") // 누구나 접근 가능해야 하는 페이지 / 인증없이 접근 가능해야 함.
//                .defaultSuccessUrl("/home")
//                .failureUrl("/login")
//                .usernameParameter("userId")
//                .passwordParameter("password")
//                .loginProcessingUrl("/login_pro")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        log.debug("인증 성공 => authentication: " + authentication.getName());
//                        response.sendRedirect("/");
                        RequestCache requestCache = new HttpSessionRequestCache();
                        final SavedRequest savedRequest = requestCache.getRequest(request, response);
                        final String redirectUrl = savedRequest.getRedirectUrl();
                        log.debug("redirectUrl = " + redirectUrl);
                        response.sendRedirect(redirectUrl);
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        log.debug("exception: " + exception.getMessage());
                        response.sendRedirect("/login");
                    }
                })
                .permitAll(); // 위 경로들은 모두 접근 가능

        // 로그 아웃 설정
//        http
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
//                .addLogoutHandler(new LogoutHandler() {
//                    @Override
//                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//                        log.debug("로그아웃 핸들러 => 세션 삭제");
//                        HttpSession session = request.getSession();
//                        session.invalidate(); // 세션 삭제
//                    }
//                })
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                        log.debug("로그아웃 성공 => send redirect('/login')");
//                        response.sendRedirect("/login"); // 로그아웃 성공 후 로그인 페이지로 이동
//                    }
//                })
//                .deleteCookies("remember-me"); // 쿠키 삭제

        // remember-me 쿠키 설정
//        http
//                .rememberMe()
//                .rememberMeParameter("remember") // 기본 파라미터명은 remember-me
//                .tokenValiditySeconds(3600) // 쿠키 만료 시간 설정
//                .alwaysRemember(false) // 기본값으로 false 할 것.
//                .userDetailsService(userDetailsService);

        //  동시 세션 제어 전략 설정
//        http
//               .sessionManagement() // 세션 관리 기능
//               .maximumSessions(1) // 최대 동시 세션 허용 갯수
//               .maxSessionsPreventsLogin(true) // 동시 로그인 전략 설정 true: 신규 세션 차단 전략, false(default): 기존 세션 만료 전략
//               .expiredUrl("/expired")
//               .and()
//               .invalidSessionUrl("/invalid");

        // 세션 고정 보호
//        http
//               .sessionManagement()
//               .sessionFixation().changeSessionId();

        // ExceptionTranslationFilter 예외 처리 기능
        http
                .exceptionHandling()
//                .authenticationEntryPoint(new AuthenticationEntryPoint() {
//                    @Override
//                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//                        log.debug("인증 예외 발생 : " + request.getRequestURL() + " : "+ authException.getMessage());
//                        response.sendRedirect("/login");
//                    }
//                })
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        log.debug("인가 예외 발생: " + request.getMethod() + " : " + accessDeniedException.getMessage());
                        response.sendRedirect("/denied");
                    }
                });
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}1111")
                .roles("USER");
        auth.inMemoryAuthentication()
                .withUser("sys").password("{noop}1111")
                .roles("SYS", "USER");
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}1111")
                .roles("ADMIN", "SYS", "USER");
    }
}
