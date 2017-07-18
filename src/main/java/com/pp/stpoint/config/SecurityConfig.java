package com.pp.stpoint.config;

import com.pp.stpoint.config.security.filters.CsrfHeaderFilter;
import com.pp.stpoint.constants.Authorities;
import com.pp.stpoint.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */

//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableRedisHttpSession
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    CsrfHeaderFilter csrfHeaderFilter;
    
    @Autowired
    AuthService authService;
    
    @Autowired
    UserDetailsService userDetailsService;
    
    @Value("${app.xsrf.header}")
    String xsrfTokenHeader;
    
    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }
    @Bean
    public BCryptPasswordEncoder bcryptEncoder(){
        return new BCryptPasswordEncoder();
    }
  
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                //.inMemoryAuthentication()
                //.withUser("superuser").password("password").roles(Authorities.ADMIN.authority);
                //.and()
                .userDetailsService(authService)
                .passwordEncoder(bcryptEncoder());
                
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
      web
        .ignoring()
           .antMatchers("/js/**", "/css/**", "/vendor/**", "/assets/**");//, 
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/rest/admin/**").hasAuthority(Authorities.ADMIN.authority)
                .antMatchers("/rest/authenticated/**").authenticated()
                .antMatchers("/rest/public/**").permitAll()
                .antMatchers(
                        "/resetPassword/**", 
                        "/confirmPassword/**").permitAll()
                //.antMatchers("/*.js", "/*.css", "/*.html").permitAll()
                //.anyRequest().authenticated()
                .and()
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                //inpedir amazernamento em sessao no browser
                .requestCache()
                .requestCache(new NullRequestCache())
                //.and()
                //.csrf().csrfTokenRepository(csrfTokenRepository())
                .and()
                .addFilterAfter(csrfHeaderFilter, CsrfFilter.class);
    }
    
    // muda o mecanismo de amazernamento dos tokens para o header
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName(xsrfTokenHeader);
        return repository;
    }
}
