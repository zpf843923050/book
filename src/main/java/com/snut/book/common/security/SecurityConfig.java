package com.snut.book.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 注入认证处理类，处理不同用户跳转到不同的页面
     */
    @Autowired
    SuccessHandler successHandler;

    /**
     * 用户授权操作
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("AppSecurityConfigurer configure() HttpSecurity 调用......");
        http.authorizeRequests()
                // spring-security 5.0 之后需要过滤静态资源
                .antMatchers("/img/**","/login/**", "/css/**", "/webjars/**", "/js/**","/data/**","/vendor/**").permitAll()
                .antMatchers( "/index","/getBooks").hasRole("USER")
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "DBA")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").successHandler(successHandler)
                .usernameParameter("loginName").passwordParameter("password")
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
//        System.out.println("AppSecurityConfigurer configure() HttpSecurity 调用......");
//        http.formLogin()                                 // 定义当需要用户登录时候，转到的登录页面。
//                .loginPage("/login.html")                        // 设置登录页面
//                .loginProcessingUrl("/login")            // 自定义的登录接口
//                .successHandler(successHandler).usernameParameter("usernameInput").passwordParameter("userpwdInput")
//                .defaultSuccessUrl("/index.html").permitAll()        // 登录成功之后，默认跳转的页面
//                .and().authorizeRequests()                    // 定义哪些URL需要被保护、哪些不需要被保护
//                .antMatchers("/", "/index").permitAll()        // 设置所有人都可以访问登录页面
//                .anyRequest().authenticated()// 任何请求,登录后可以访问
//                .and().csrf().disable();                    // 关闭csrf防护
    }



//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        //解决静态资源被拦截的问题
//        web.ignoring().antMatchers("/css/**", "/webjars/**");
////        web.ignoring().antMatchers("/webjars/**/*", "/**/*.css", "/**/*.js");
//    }

    /**
     * 用户认证操作
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("AppSecurityConfigurer configureGlobal() 调用......");
        // spring-security 5.0 之后需要密码编码器，否则会抛出异常：There is no PasswordEncoder mapped for the id "null"
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("jack").password("123456").roles("USER");
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("admin").password("admin").roles("ADMIN", "DBA");
    }

}
