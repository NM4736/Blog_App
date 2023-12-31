package com.blog_Application.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    JWTAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    CustomUserDetailService customUserDetailService;
   protected  void configure(HttpSecurity http) throws Exception {
       http
               .csrf().disable()
               .authorizeHttpRequests()
               .antMatchers("/api/v1/auth/**").permitAll()
               .antMatchers(HttpMethod.GET).permitAll() // ALL GET METHOD DON'T REQUIRE TOKEN NOW
               .anyRequest()
               .authenticated()
               .and()
               .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
              // .httpBasic();
               .and()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

   }

   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
   }

   @Bean
   public PasswordEncoder passwordEncoder()
   {
        return new BCryptPasswordEncoder();
   }

   @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
   {
       return super.authenticationManagerBean();
   }

}



