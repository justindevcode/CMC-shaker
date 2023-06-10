package com.example.Heunduljang.common.config;


import com.example.Heunduljang.common.filter.AuthenticationFilter;
import com.example.Heunduljang.common.filter.ExceptionFilter;
import com.example.Heunduljang.user.service.CustomUserDetailService;
import com.example.Heunduljang.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ExceptionFilter exceptionFilter;
    private final CustomUserDetailService customUserDetailService;
    private final UserService userService;

    @Override
    public void configure(WebSecurity web)  {
        web.ignoring().antMatchers("/v3/api-docs", "/swagger*/**" );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // 세션을 사용하지 않기 때문에 STATELESS 로 설정
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager(),customUserDetailService,userService))
                .authorizeRequests()
                .antMatchers("/v3/api-docs").permitAll()
                .antMatchers("/swagger*/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(exceptionFilter, AuthenticationFilter.class);

    }
}
