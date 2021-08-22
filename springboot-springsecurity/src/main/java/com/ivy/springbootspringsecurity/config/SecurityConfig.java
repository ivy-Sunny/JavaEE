package com.ivy.springbootspringsecurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Http权限授权（aythorization）
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问，功能页只有对应有权限的人才能访问
        http.authorizeRequests()
                .antMatchers("/","/toLogin","/login").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        /**
         * 没有认证默认到登录页面
         * 源码中默认（会去请求/login）
         * loginPage 定制登录页面（点击登录后的接口）
         */
        http.formLogin()
                .loginPage("/toLogin")  //用户未登录时，访问除 "/" 外的资源，均跳转到该路径
                .loginProcessingUrl("/login") //登录表单处理的地址，也就是 action中的地址
                .defaultSuccessUrl("/") //登录成功后跳转的页面
        ;

        /**
         * 开启注销功能,
         */
        http.logout().logoutSuccessUrl("/index");

        /**
         * 防止网站攻击:  get, post
         * 关闭csrf功能，登出失败的可能原因
         */
        http.csrf().disable();

        /**
         * 开启记住我, 默认保存两周
         */
        http.rememberMe();
    }

    /**
     * Auth权限认证（authentication）
     * 在 springboot 2.2.0前 可以直接使用～ PasswordEncoder:密码编码
     * Spring Security 5.0+ 新增了很多的加密方法
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("ivySunny").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2", "vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
    }
}
