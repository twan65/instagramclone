package com.anveloper.instagramclone.common.config;

import com.anveloper.instagramclone.common.config.auth.JwtAuthFilter;
import com.anveloper.instagramclone.common.config.auth.JwtAuthProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  JwtAuthProcessor jwtAuthProcessor;

  @Autowired
  RestAuthenticationFailureEntryPoint restAuthenticationEntryPoint;

  @Override
  public void configure(HttpSecurity http) throws Exception {

    http.csrf().disable().rememberMe();
    http.logout().disable();
    http.cors(Customizer.withDefaults());

    // filter
    http.addFilterBefore(new JwtAuthFilter(jwtAuthProcessor),
        UsernamePasswordAuthenticationFilter.class);

    http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);

    // session
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // public
    http.authorizeRequests()
        .antMatchers("/api/users/**").permitAll();

    // private
    http.authorizeRequests()
        .antMatchers("/api/private").authenticated();
  }


}
