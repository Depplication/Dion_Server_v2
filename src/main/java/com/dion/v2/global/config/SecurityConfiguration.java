package com.dion.v2.global.config;

import com.dion.v2.global.security.JwtFilter;
import com.dion.v2.global.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
                .csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token으로 인증하므로 세션은 필요없으므로 생성안함.
                .and()
                .authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크

                //Advertising
                .antMatchers(HttpMethod.GET, "/ad/**").permitAll()
                .antMatchers(HttpMethod.POST, "/ad/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/ad/**").authenticated()

                //Auth
                .antMatchers(HttpMethod.GET, "/auth/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/sign-up", "/auth/sign-in").authenticated()
                .antMatchers(HttpMethod.PATCH, "/auth/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/auth/**").authenticated()

                //Owner
                .antMatchers(HttpMethod.GET, "/owner/**").permitAll()
                .antMatchers(HttpMethod.POST, "/owner/sign-up", "/owner/sign-in").authenticated()
                .antMatchers(HttpMethod.PATCH, "/owner/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/owner/**").authenticated()

                //Point
                .antMatchers(HttpMethod.GET, "/user/point/**").authenticated()
                .antMatchers(HttpMethod.POST, "/user/point/**").authenticated()
                .antMatchers(HttpMethod.GET, "/owner/point/**").authenticated()
                .antMatchers(HttpMethod.POST, "/owner/point/**").authenticated()

                //Product
                .antMatchers(HttpMethod.GET, "/product/**").permitAll()
                .antMatchers(HttpMethod.POST, "/product/**").authenticated()

                //Upload
                .antMatchers(HttpMethod.GET, "/upload/product/**").permitAll()
                .antMatchers(HttpMethod.POST, "/upload/product/**").authenticated()
                .antMatchers(HttpMethod.GET, "/upload/ad/**").permitAll()
                .antMatchers(HttpMethod.POST, "/upload/ad/**").authenticated()

                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class); // jwt token 필터를 id/password 인증 필터 전에 넣는다
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}